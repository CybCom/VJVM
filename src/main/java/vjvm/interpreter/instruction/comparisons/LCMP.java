package vjvm.interpreter.instruction.comparisons;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LCMP extends Instruction {
    private String name;

    public static final LCMP LCMP(ProgramCounter pc, MethodInfo method) {
        return new LCMP("lcmp");
    }

    @Override
    public void run(JThread thread) {
        var stack = thread.top().stack();
        long value2 = stack.popLong();
        long value1 = stack.popLong();
        System.err.println(name + " between " + value1 + " and " + value2);
        if (value1 > value2) {
            stack.pushInt(1);
        } else if (value1 < value2) {
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
