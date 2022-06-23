package vjvm.interpreter.instruction.comparisons;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.OperandStack;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

import java.util.Optional;
import java.util.function.Function;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class XCMPCOND<T> extends Instruction {
    private final Function<OperandStack, T> popFunc;
    private final int nanValue;
    private String name;

    public static final XCMPCOND<Float> FCMPL(ProgramCounter pc, MethodInfo method) {
        return new XCMPCOND<Float>(OperandStack::popFloat, -1, "fcmpl");
    }

    public static final XCMPCOND<Float> FCMPG(ProgramCounter pc, MethodInfo method) {
        return new XCMPCOND<Float>(OperandStack::popFloat, 1, "fcmpg");
    }

    public static final XCMPCOND<Double> DCMPL(ProgramCounter pc, MethodInfo method) {
        return new XCMPCOND<Double>(OperandStack::popDouble, -1, "dcmpl");
    }

    public static final XCMPCOND<Double> DCMPG(ProgramCounter pc, MethodInfo method) {
        return new XCMPCOND<Double>(OperandStack::popDouble, 1, "dcmpg");
    }

    @Override
    public void run(JThread thread) {
        var stack = thread.top().stack();
        T value2 = popFunc.apply(stack);
        T value1 = popFunc.apply(stack);
        System.err.println(name + " between " + value1.toString() + " and " + value2.toString());
        if (value1 == Optional.empty() || value2 == Optional.empty()) {
            stack.pushInt(nanValue);
        } else if (Double.parseDouble(value1.toString()) > Double.parseDouble(value2.toString())) {
            stack.pushInt(1);
        } else if (Double.parseDouble(value1.toString()) < Double.parseDouble(value2.toString())) {
            stack.pushInt(-1);
        } else {
            stack.pushInt(0);
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
