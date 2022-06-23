package vjvm.interpreter.instruction.constants;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.OperandStack;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;
import vjvm.runtime.classdata.constant.DoubleConstant;
import vjvm.runtime.classdata.constant.FloatConstant;
import vjvm.runtime.classdata.constant.IntegerConstant;
import vjvm.runtime.classdata.constant.LongConstant;
import vjvm.utils.UnimplementedError;

import java.util.function.BiConsumer;

/**
 * load value from constant pool at index to op stack.
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LDCX<T> extends Instruction {
    private final T value;
    private final BiConsumer<OperandStack, T> pushFunc;
    private String name;

    public static final LDCX LDC(ProgramCounter pc, MethodInfo method) {
        int index = pc.byte_();
        return getInstance(index, method, true, "ldc");
    }

    public static LDCX LDC_W(ProgramCounter pc, MethodInfo method) {
        int indexByte1 = pc.byte_();
        int indexByte2 = pc.byte_();
        int index = (indexByte1 << 8) | indexByte2;
        return getInstance(index, method, true, "ldc_w");
    }

    public static LDCX LDC2_W(ProgramCounter pc, MethodInfo method) {
        int indexByte1 = pc.byte_();
        int indexByte2 = pc.byte_();
        int index = (indexByte1 << 8) | indexByte2;
        return getInstance(index, method, false, "ldc2_w");
    }

    private static LDCX getInstance(int index, MethodInfo method, boolean isItem, String name) { // if the passed value is an item or long/double.
        Object value = method.jClass().constantPool().constant(index);
        if (isItem) {
            assert !(value instanceof LongConstant || value instanceof DoubleConstant);
        } else {
            assert value instanceof LongConstant || value instanceof DoubleConstant;
        }
        if (value instanceof IntegerConstant) {
            return new LDCX<>(((IntegerConstant) value).bytes(), OperandStack::pushInt, name);
        } else if (value instanceof FloatConstant) {
            return new LDCX<>(((FloatConstant) value).bytes(), OperandStack::pushFloat, name);
        } else if (value instanceof LongConstant) {
            return new LDCX<>(((LongConstant) value).aLong(), OperandStack::pushLong, name);
        } else if (value instanceof DoubleConstant) {
            return new LDCX<>(((DoubleConstant) value).aDouble(), OperandStack::pushDouble, name);
        } else {
            throw new UnimplementedError("ldc value not supported.");
        }
    }


    @Override
    public void run(JThread thread) {
        var stack = thread.top().stack();
        System.err.println(name + ": " + value + value.getClass());
        pushFunc.accept(stack, value);
    }

    @Override
    public String toString() {
        return name;
    }
}
