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
public class DUPX_XY extends Instruction {
    private final int popNum;
    private String name;

    public static final DUPX_XY DUP2(ProgramCounter pc, MethodInfo methodInfo) {
        return new DUPX_XY(0,"dup");
    }

    public static final DUPX_XY DUP2_X1(ProgramCounter pc, MethodInfo methodInfo) {
        return new DUPX_XY(1, "dup_x1");
    }

    public static final DUPX_XY DUP2_X2(ProgramCounter pc, MethodInfo methodInfo) {
        return new DUPX_XY(2, "dup_x2");
    }

    @Override
    public void run(JThread thread) {
        var stack = thread.top().stack();
        System.err.println(name + " on " + popNum);
        Slots top = stack.popSlots(2);
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
