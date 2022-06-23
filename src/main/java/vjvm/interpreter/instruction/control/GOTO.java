package vjvm.interpreter.instruction.control;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GOTO extends Instruction {

    short offset;   // 16 bit signed.
    private final String name;

    public static final GOTO GOTO(ProgramCounter pc, MethodInfo method) {
        byte branchByte1 = pc.byte_();
        byte branchByte2 = pc.byte_();
        return new GOTO((short) ((branchByte1 << 8) | branchByte2), "goto");
    }
    @Override
    public void run(JThread thread) {
        System.err.println(name + " " + offset);
        offset -= 3;    // reason in ifcond.
        thread.pc().move(offset);
    }

    @Override
    public String toString() {
        return name;
    }
}
