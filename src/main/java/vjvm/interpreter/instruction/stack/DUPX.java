package vjvm.interpreter.instruction.stack;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.OperandStack;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.Slots;
import vjvm.runtime.classdata.MethodInfo;

import java.util.function.BiConsumer;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DUPX extends Instruction {

    private final int popNum;
    private String name;

    public static final DUPX DUP(ProgramCounter pc, MethodInfo methodInfo) {
        return new DUPX(0,"dup");
    }

    public static final DUPX DUP_X1(ProgramCounter pc, MethodInfo methodInfo) {
        return new DUPX(1, "dup_x1");
    }

    public static final DUPX DUP_X2(ProgramCounter pc, MethodInfo methodInfo) {
        return new DUPX(2, "dup_x2");
    }

    @Override
    public void run(JThread thread) {
        var stack = thread.top().stack();
        System.err.println(name + " on " + popNum);
        Slots top = stack.popSlots(1);
        Slots underTop = stack.popSlots(popNum);
        stack.pushSlots(top);
        stack.pushSlots(underTop);
        stack.pushSlots(top);
    }

    @Override
    public String toString() {
        return name;
    }
}
