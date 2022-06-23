package vjvm.interpreter.instruction.math;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.OperandStack;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;
import vjvm.utils.UnimplementedError;

import java.util.function.BiConsumer;
import java.util.function.Function;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class XNEG<N> extends Instruction {

    private final Function<OperandStack, N> popFunc;
    private final BiConsumer<OperandStack, N> pushFunc;
    private String name;

    /* op on integer */
    public static final XNEG<Integer> INEG(ProgramCounter pc, MethodInfo method) {
        return new XNEG<Integer>(OperandStack::popInt, OperandStack::pushInt, "ineg");
    }

    public static final XNEG<Long> LNEG(ProgramCounter pc, MethodInfo method) {
        return new XNEG<Long>(OperandStack::popLong, OperandStack::pushLong, "lneg");
    }

    public static final XNEG<Float> FNEG(ProgramCounter pc, MethodInfo method) {
        return new XNEG<Float>(OperandStack::popFloat, OperandStack::pushFloat, "fneg");
    }


    public static final XNEG<Double> DNEG(ProgramCounter pc, MethodInfo method) {
        return new XNEG<Double>(OperandStack::popDouble, OperandStack::pushDouble, "dneg");
    }


    @Override
    public void run(JThread thread) {
        var stack = thread.top().stack();
        N rawValue1 = popFunc.apply(stack);
        Number value;
        if (rawValue1 instanceof Integer) {
            int value1 = (Integer) rawValue1;
            value = -value1;
            System.err.println(name + ": " + value1);
            pushFunc.accept(stack, (N) value);
        } else if (rawValue1 instanceof Float) {
            float value1 = (Float) rawValue1;
            value = -value1;
            System.err.println(name + ": " + value1);
            pushFunc.accept(stack, (N) value);
        } else if (rawValue1 instanceof Long) {
            long value1 = (Long) rawValue1;
            value = -value1;
            System.err.println(name + ": " + value1);
            pushFunc.accept(stack, (N) value);
        } else if (rawValue1 instanceof Double) {
            double value1 = (Double) rawValue1;
            value = -value1;
            System.err.println(name + ": " + value1);
            pushFunc.accept(stack, (N) value);
        } else {
            throw new UnimplementedError();
        }
    }


    @Override
    public String toString() {
        return name;
    }
}
