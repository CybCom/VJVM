package vjvm.interpreter.instruction.constants;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

public class NOP extends Instruction {
    public static Instruction NOP(ProgramCounter programCounter, MethodInfo methodInfo) {
        return new NOP();
    }

    @Override
    public void run(JThread thread) {
        System.err.println("nop at thread " + thread);
    }

    @Override
    public String toString() {
        return "nop";
    }
}
