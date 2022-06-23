package vjvm.interpreter.instruction.math;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class XOPR extends Instruction {
    @Override
    public void run(JThread thread) {

    }

    @Override
    public String toString() {
        return null;
    }
}
