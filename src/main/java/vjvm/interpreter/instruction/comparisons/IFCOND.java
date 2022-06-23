package vjvm.interpreter.instruction.comparisons;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.OperandStack;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;
import vjvm.utils.UnimplementedError;

import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class IFCOND extends Instruction {
    enum Success {
        EQ, NE, LT, LE, GT, GE
    }
    private final Success success;
    private int startPosition;
    private int offset;
    private String name;

    public static final IFCOND IFEQ(ProgramCounter pc, MethodInfo method) {
        return new IFCOND(Success.EQ, getPosition(pc), getOffset(pc), "ifeq");
    }

    public static final IFCOND IFNE(ProgramCounter pc, MethodInfo method) {
        return new IFCOND(Success.NE, getPosition(pc), getOffset(pc), "ifne");
    }

    public static final IFCOND IFLT(ProgramCounter pc, MethodInfo method) {
        return new IFCOND(Success.LT, getPosition(pc), getOffset(pc), "iflt");
    }

    public static final IFCOND IFLE(ProgramCounter pc, MethodInfo method) {
        return new IFCOND(Success.LE, getPosition(pc), getOffset(pc), "ifle");
    }

    public static final IFCOND IFGT(ProgramCounter pc, MethodInfo method) {
        return new IFCOND(Success.GT, getPosition(pc), getOffset(pc), "ifgt");
    }

    public static final IFCOND IFGE(ProgramCounter pc, MethodInfo method) {
        return new IFCOND(Success.GE, getPosition(pc), getOffset(pc), "ifge");
    }

    private static final int getPosition(ProgramCounter pc) {   // where the instrument is called.
        return pc.position() - 1;
    }

    private static final short getOffset(ProgramCounter pc) {
        byte branchByte1 = pc.byte_();
        byte branchByte2 = pc.byte_();
        return (short) ((branchByte1 << 8) | branchByte2);
    }

    @Override
    public void run(JThread thread) {
        var stack = thread.top().stack();
        int value = stack.popInt();
        offset -= thread.pc().position() - startPosition;

    /*
        CAUTION! the offset is from where the ifcond is called, and the pc position has changed since 3 bytes read.
        So as if_xcmpcond and goto.
    */

        System.err.println(name + " at " + value + " from " + startPosition + " by " + offset + " to " + (thread.pc().position() + offset));
        switch (success) {
            case EQ:
                if (value == 0) {
                    thread.pc().move(offset);
                }
                break;
            case NE:
                if (value != 0) {
                    thread.pc().move(offset);
                }
                break;
            case LT:
                if (value < 0) {
                    thread.pc().move(offset);
                }
                break;
            case LE:
                if (value <= 0) {
                    thread.pc().move(offset);
                }
                break;
            case GT:
                if (value > 0) {
                    thread.pc().move(offset);
                }
                break;
            case GE:
                if (value >= 0) {
                    thread.pc().move(offset);
                }
                break;
            default:
                throw new UnimplementedError();
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
