package vjvm.interpreter.instruction.math;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class IINC extends Instruction {
    private final int index;
    private final int constant;
    private final String name;

    public static final IINC IINC(ProgramCounter pc, MethodInfo method) {
        int index = pc.byte_();
        int constant = pc.byte_();
        return new IINC(index, constant, "iinc");
    }

    @Override
    public void run(JThread thread) {
        System.err.println(name + " at " + index + " by " + constant);
        thread.top().vars().int_(index, thread.top().vars().int_(index) + constant);
    }

    @Override
    public String toString() {
        return name;
    }
}
