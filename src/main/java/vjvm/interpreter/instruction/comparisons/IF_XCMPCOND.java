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
import java.util.function.Function;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class IF_XCMPCOND extends Instruction {
    private enum Success {
        EQ, NE, LT, LE, GT, GE
    }
    private final Success success;
    private int startPosition;

    private int offset;
    private String name;

    public static final IF_XCMPCOND IF_ICMPEQ(ProgramCounter pc, MethodInfo method) {
        return new IF_XCMPCOND(Success.EQ, getPosition(pc), getOffset(pc), "if_icmpeq");
    }

    public static final IF_XCMPCOND IF_ICMPNE(ProgramCounter pc, MethodInfo method) {
        return new IF_XCMPCOND(Success.NE, getPosition(pc), getOffset(pc), "if_icmpne");
    }

    public static final IF_XCMPCOND IF_ICMPLT(ProgramCounter pc, MethodInfo method) {
        return new IF_XCMPCOND(Success.LT, getPosition(pc), getOffset(pc), "if_icmplt");
    }

    public static final IF_XCMPCOND IF_ICMPLE(ProgramCounter pc, MethodInfo method) {
        return new IF_XCMPCOND(Success.LE, getPosition(pc), getOffset(pc), "if_icmple");
    }

    public static final IF_XCMPCOND IF_ICMPGT(ProgramCounter pc, MethodInfo method) {
        return new IF_XCMPCOND(Success.GT, getPosition(pc), getOffset(pc), "if_icmpgt");
    }

    public static final IF_XCMPCOND IF_ICMPGE(ProgramCounter pc, MethodInfo method) {
        return new IF_XCMPCOND(Success.GE, getPosition(pc), getOffset(pc), "if_icmpge");
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
        int value2 = stack.popInt();
        int value1 = stack.popInt();
        offset -= thread.pc().position() - startPosition;
        System.err.println(name + " between " + value1 + " and " + value2 + " by " + offset);
        switch (success) {
            case EQ:
                if (value1 == value2) {
                    thread.pc().move(offset);
                }
                break;
            case NE:
                if (value1 != value2) {
                    thread.pc().move(offset);
                }
                break;
            case LT:
                if (value1 < value2) {
                    thread.pc().move(offset);
                }
                break;
            case LE:
                if (value1 <= value2) {
                    thread.pc().move(offset);
                }
                break;
            case GT:
                if (value1 > value2) {
                    thread.pc().move(offset);
                }
                break;
            case GE:
                if (value1 >= value2) {
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
