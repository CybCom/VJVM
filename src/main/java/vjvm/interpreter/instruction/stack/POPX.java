package vjvm.interpreter.instruction.stack;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.OperandStack;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

import java.util.function.BiConsumer;

/**
 * pop 1 or 2 slots from top of op stack.
 * */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class POPX extends Instruction {
    private final int popNum;
    private final BiConsumer<OperandStack, Integer> pushFunc;
    private String name;

    public static final POPX POP(ProgramCounter pc, MethodInfo methodInfo) {
        return new POPX(1, OperandStack::popSlots, "pop");
    }

    public static final POPX POP2(ProgramCounter pc, MethodInfo methodInfo) {
        return new POPX(2, OperandStack::popSlots, "pop2");
    }

    @Override
    public void run(JThread thread) {
        var stack = thread.top().stack();
        System.err.println(name + ": " + popNum);
        pushFunc.accept(stack, popNum); // get value from curr vars.
    }

    @Override
    public String toString() {
        return name;
    }
}
