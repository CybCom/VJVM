package vjvm.interpreter.instruction.stack;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.Slots;
import vjvm.runtime.classdata.MethodInfo;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SWAP extends Instruction {
    private final String name;
    public static final SWAP SWAP(ProgramCounter pc, MethodInfo methodInfo) {
        return new SWAP("dup_x2");
    }

    @Override
    public void run(JThread thread) {
        var stack = thread.top().stack();
        System.err.println(name);
        Slots top = stack.popSlots(1);
        Slots under = stack.popSlots(1);
        stack.pushSlots(top);
        stack.pushSlots(under);
    }

    @Override
    public String toString() {
        return name;
    }
}
