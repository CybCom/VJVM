package vjvm.interpreter.instruction.conversions;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.OperandStack;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

import java.util.function.BiConsumer;
import java.util.function.Function;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class X2Y<T, R> extends Instruction {
    private final Function<OperandStack, T> popFunc;
    private final Function<T, R> convertFunc;
    private final BiConsumer<OperandStack, R> pushFunc;
    private String name;

    public static final X2Y<Integer, Long> I2L(ProgramCounter pc, MethodInfo method) {
        return new X2Y<Integer, Long>(OperandStack::popInt, Integer::longValue, OperandStack::pushLong, "i2l");
    }

    public static final X2Y<Integer, Float> I2F(ProgramCounter pc, MethodInfo method) {
        return new X2Y<Integer, Float>(OperandStack::popInt, Integer::floatValue, OperandStack::pushFloat, "i2f");
    }

    public static final X2Y<Integer, Double> I2D(ProgramCounter pc, MethodInfo method) {
        return new X2Y<Integer, Double>(OperandStack::popInt, Integer::doubleValue, OperandStack::pushDouble, "i2d");
    }

    public static final X2Y<Long, Integer> L2I(ProgramCounter pc, MethodInfo method) {
        return new X2Y<Long, Integer>(OperandStack::popLong, Long::intValue, OperandStack::pushInt, "l2i");
    }

    public static final X2Y<Long, Float> L2F(ProgramCounter pc, MethodInfo method) {
        return new X2Y<Long, Float>(OperandStack::popLong, Long::floatValue, OperandStack::pushFloat, "l2f");
    }

    public static final X2Y<Long, Double> L2D(ProgramCounter pc, MethodInfo method) {
        return new X2Y<Long, Double>(OperandStack::popLong, Long::doubleValue, OperandStack::pushDouble, "l2d");
    }

    public static final X2Y<Float, Integer> F2I(ProgramCounter pc, MethodInfo method) {
        return new X2Y<Float, Integer>(OperandStack::popFloat, Float::intValue, OperandStack::pushInt, "f2i");
    }

    public static final X2Y<Float, Long> F2L(ProgramCounter pc, MethodInfo method) {
        return new X2Y<Float, Long>(OperandStack::popFloat, Float::longValue, OperandStack::pushLong, "f2l");
    }

    public static final X2Y<Float, Double> F2D(ProgramCounter pc, MethodInfo method) {
        return new X2Y<Float, Double>(OperandStack::popFloat, Float::doubleValue, OperandStack::pushDouble, "f2d");
    }

    public static final X2Y<Double, Integer> D2I(ProgramCounter pc, MethodInfo method) {
        return new X2Y<Double, Integer>(OperandStack::popDouble, Double::intValue, OperandStack::pushInt, "d2i");
    }

    public static final X2Y<Double, Long> D2L(ProgramCounter pc, MethodInfo method) {
        return new X2Y<Double, Long>(OperandStack::popDouble, Double::longValue, OperandStack::pushLong, "d2l");
    }

    public static final X2Y<Double, Float> D2F(ProgramCounter pc, MethodInfo method) {
        return new X2Y<Double, Float>(OperandStack::popDouble, Double::floatValue, OperandStack::pushFloat, "d2f");
    }

    public static final X2Y<Integer, Byte> I2B(ProgramCounter pc, MethodInfo method) {
        return new X2Y<Integer, Byte>(OperandStack::popInt, Integer::byteValue, OperandStack::pushByte, "i2b");
    }

    /** in fact its int to int, just narrowed */
    public static final X2Y<Integer, Integer> I2C(ProgramCounter pc, MethodInfo method) {
        return new X2Y<Integer, Integer>(OperandStack::popInt, c -> (int) (c & 0xff), OperandStack::pushInt, "i2c");
    }

    public static final X2Y<Integer, Short> I2S(ProgramCounter pc, MethodInfo method) {
        return new X2Y<Integer, Short>(OperandStack::popInt, Integer::shortValue, OperandStack::pushShort, "i2s");
    }


    @Override
    public void run(JThread thread) {
        var stack = thread.top().stack();
        var oldValue = popFunc.apply(stack);
        R newValue = convertFunc.apply(oldValue);
        System.err.println(name + " from " + oldValue + " to " + newValue);
        pushFunc.accept(stack, newValue);
    }

    @Override
    public String toString() {
        return name;
    }
}
