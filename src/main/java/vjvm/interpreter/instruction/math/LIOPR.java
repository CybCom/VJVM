package vjvm.interpreter.instruction.math;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.interpreter.instruction.constants.XCONST_Y;
import vjvm.runtime.JThread;
import vjvm.runtime.OperandStack;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;
import vjvm.utils.UnimplementedError;

import javax.lang.model.type.PrimitiveType;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;


/**
 * class for binary operations
 * */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LIOPR<N extends Number> extends Instruction {

    private enum Operation {ADD, SUB, MUL, DIV, REM, SHL, SHR, USHR, AND, OR, XOR}

    private final Operation operation;
    private final Function<OperandStack, N> popFunc;
    private final BiConsumer<OperandStack, N> pushFunc;
    private String name;

    /* op on integer */
    public static final LIOPR<Integer> IADD(ProgramCounter pc, MethodInfo method) {
        return new LIOPR<Integer>(Operation.ADD, OperandStack::popInt, OperandStack::pushInt, "iadd");
    }

    public static final LIOPR<Integer> ISUB(ProgramCounter pc, MethodInfo method) {
        return new LIOPR<Integer>(Operation.SUB, OperandStack::popInt, OperandStack::pushInt, "isub");
    }

    public static final LIOPR<Integer> IMUL(ProgramCounter pc, MethodInfo method) {
        return new LIOPR<Integer>(Operation.MUL, OperandStack::popInt, OperandStack::pushInt, "imul");
    }

    public static final LIOPR<Integer> IDIV(ProgramCounter pc, MethodInfo method) {
        return new LIOPR<Integer>(Operation.DIV, OperandStack::popInt, OperandStack::pushInt, "idiv");
    }

    public static final LIOPR<Integer> IREM(ProgramCounter pc, MethodInfo method) {
        return new LIOPR<Integer>(Operation.REM, OperandStack::popInt, OperandStack::pushInt, "irem");
    }

    public static final LIOPR<Integer> ISHL(ProgramCounter pc, MethodInfo method) {
        return new LIOPR<Integer>(Operation.SHL, OperandStack::popInt, OperandStack::pushInt, "ishl");
    }

    public static final LIOPR<Integer> ISHR(ProgramCounter pc, MethodInfo method) {
        return new LIOPR<Integer>(Operation.SHR, OperandStack::popInt, OperandStack::pushInt, "ishr");
    }

    public static final LIOPR<Integer> IUSHR(ProgramCounter pc, MethodInfo method) {
        return new LIOPR<Integer>(Operation.USHR, OperandStack::popInt, OperandStack::pushInt, "iushr");
    }

    public static final LIOPR<Integer> IAND(ProgramCounter pc, MethodInfo method) {
        return new LIOPR<Integer>(Operation.AND, OperandStack::popInt, OperandStack::pushInt, "iand");
    }

    public static final LIOPR<Integer> IOR(ProgramCounter pc, MethodInfo method) {
        return new LIOPR<Integer>(Operation.OR, OperandStack::popInt, OperandStack::pushInt, "ior");
    }

    public static final LIOPR<Integer> IXOR(ProgramCounter pc, MethodInfo method) {
        return new LIOPR<Integer>(Operation.XOR, OperandStack::popInt, OperandStack::pushInt, "ixor");
    }


    public static final LIOPR<Long> LADD(ProgramCounter pc, MethodInfo method) {
        return new LIOPR<Long>(Operation.ADD, OperandStack::popLong, OperandStack::pushLong, "ladd");
    }

    public static final LIOPR<Long> LSUB(ProgramCounter pc, MethodInfo method) {
        return new LIOPR<Long>(Operation.SUB, OperandStack::popLong, OperandStack::pushLong, "lsub");
    }

    public static final LIOPR<Long> LMUL(ProgramCounter pc, MethodInfo method) {
        return new LIOPR<Long>(Operation.MUL, OperandStack::popLong, OperandStack::pushLong, "lmul");
    }

    public static final LIOPR<Long> LDIV(ProgramCounter pc, MethodInfo method) {
        return new LIOPR<Long>(Operation.DIV, OperandStack::popLong, OperandStack::pushLong, "ldiv");
    }

    public static final LIOPR<Long> LREM(ProgramCounter pc, MethodInfo method) {
        return new LIOPR<Long>(Operation.REM, OperandStack::popLong, OperandStack::pushLong, "lrem");
    }

    public static final LIOPR<Long> LSHL(ProgramCounter pc, MethodInfo method) {
        return new LIOPR<Long>(Operation.SHL, OperandStack::popLong, OperandStack::pushLong, "lshl");
    }

    public static final LIOPR<Long> LSHR(ProgramCounter pc, MethodInfo method) {
        return new LIOPR<Long>(Operation.SHR, OperandStack::popLong, OperandStack::pushLong, "lshr");
    }

    public static final LIOPR<Long> LUSHR(ProgramCounter pc, MethodInfo method) {
        return new LIOPR<Long>(Operation.USHR, OperandStack::popLong, OperandStack::pushLong, "lushr");
    }

    public static final LIOPR<Long> LAND(ProgramCounter pc, MethodInfo method) {
        return new LIOPR<Long>(Operation.AND, OperandStack::popLong, OperandStack::pushLong, "land");
    }

    public static final LIOPR<Long> LOR(ProgramCounter pc, MethodInfo method) {
        return new LIOPR<Long>(Operation.OR, OperandStack::popLong, OperandStack::pushLong, "lor");
    }

    public static final LIOPR<Long> LXOR(ProgramCounter pc, MethodInfo method) {
        return new LIOPR<Long>(Operation.XOR, OperandStack::popLong, OperandStack::pushLong, "lxor");
    }


    public static final LIOPR<Float> FADD(ProgramCounter pc, MethodInfo method) {
        return new LIOPR<Float>(Operation.ADD, OperandStack::popFloat, OperandStack::pushFloat, "fadd");
    }

    public static final LIOPR<Float> FSUB(ProgramCounter pc, MethodInfo method) {
        return new LIOPR<Float>(Operation.SUB, OperandStack::popFloat, OperandStack::pushFloat, "fsub");
    }

    public static final LIOPR<Float> FMUL(ProgramCounter pc, MethodInfo method) {
        return new LIOPR<Float>(Operation.MUL, OperandStack::popFloat, OperandStack::pushFloat, "fmul");
    }

    public static final LIOPR<Float> FDIV(ProgramCounter pc, MethodInfo method) {
        return new LIOPR<Float>(Operation.DIV, OperandStack::popFloat, OperandStack::pushFloat, "fdiv");
    }

    public static final LIOPR<Float> FREM(ProgramCounter pc, MethodInfo method) {
        return new LIOPR<Float>(Operation.REM, OperandStack::popFloat, OperandStack::pushFloat, "frem");
    }


    public static final LIOPR<Double> DADD(ProgramCounter pc, MethodInfo method) {
        return new LIOPR<Double>(Operation.ADD, OperandStack::popDouble, OperandStack::pushDouble, "dadd");
    }

    public static final LIOPR<Double> DSUB(ProgramCounter pc, MethodInfo method) {
        return new LIOPR<Double>(Operation.SUB, OperandStack::popDouble, OperandStack::pushDouble, "dsub");
    }

    public static final LIOPR<Double> DMUL(ProgramCounter pc, MethodInfo method) {
        return new LIOPR<Double>(Operation.MUL, OperandStack::popDouble, OperandStack::pushDouble, "dmul");
    }

    public static final LIOPR<Double> DDIV(ProgramCounter pc, MethodInfo method) {
        return new LIOPR<Double>(Operation.DIV, OperandStack::popDouble, OperandStack::pushDouble, "ddiv");
    }

    public static final LIOPR<Double> DREM(ProgramCounter pc, MethodInfo method) {
        return new LIOPR<Double>(Operation.REM, OperandStack::popDouble, OperandStack::pushDouble, "drem");
    }


    @Override
    public void run(JThread thread) {
        var stack = thread.top().stack();
        N rawValue2 = popFunc.apply(stack);
        N rawValue1 = popFunc.apply(stack);
        Number value;
        if (rawValue1 instanceof Integer) {
            int value1 = (Integer) rawValue1;
            int value2 = (Integer) rawValue2;
            switch (operation) {
                case ADD:
                    value = value1 + value2;
                    break;
                case SUB:
                    value = value1 - value2;
                    break;
                case MUL:
                    value = value1 * value2;
                    break;
                case DIV:
                    value = value1 / value2;
                    break;
                case REM:
                    value = value1 % value2;
                    break;
                case SHL:
                    value = value1 << (value2 & 0x1f);  // use the low 5 bits.
                    break;
                case SHR:
                    value = value1 >> (value2 & 0x1f);
                    break;
                case USHR:
                    if (value1 > 0) {
                        value = value1 >> (value2 & 0x1f);;
                    } else {
                        value = (value1 >> (value2 & 0x1f)) + (2 << ~(value2 & 0x1f));
                    }
                    break;
                case AND:
                    value = value1 & value2;
                    break;
                case OR:
                    value = value1 | value2;
                    break;
                case XOR:
                    value = value1 ^ value2;
                    break;
                default:
                    throw new UnimplementedError();
            }
            System.err.println(name + ": " + value1 + " " + operation + " " + value2 + " = " + value);
            pushFunc.accept(stack, (N) value);
        } else if (rawValue1 instanceof Float) {
            float value1 = (Float) rawValue1;
            float value2 = (Float) rawValue2;
            switch (operation) {
                case ADD:
                    value = value1 + value2;
                    break;
                case SUB:
                    value = value1 - value2;
                    break;
                case MUL:
                    value = value1 * value2;
                    break;
                case DIV:
                    value = value1 / value2;
                    break;
                case REM:
                    value = value1 % value2;
                    break;
                default:
                    throw new UnimplementedError();
            }
            System.err.println(name + ": " + value1 + " " + operation + " " + value2 + " = " + value);
            pushFunc.accept(stack, (N) value);
        } else if (rawValue1 instanceof Long) {
            long value1 = (Long) rawValue1;
            long value2 = (Long) rawValue2;
            switch (operation) {
                case ADD:
                    value = value1 + value2;
                    break;
                case SUB:
                    value = value1 - value2;
                    break;
                case MUL:
                    value = value1 * value2;
                    break;
                case DIV:
                    value = value1 / value2;
                    break;
                case REM:
                    value = value1 % value2;
                    break;
                case SHL:
                    value = value1 << (value2 & 0x1f);  // use the low 5 bits.
                    break;
                case SHR:
                    value = value1 >> (value2 & 0x1f);
                    break;
                case USHR:
                    if (value1 > 0) {
                        value = value1 >> (value2 & 0x1f);;
                    } else {
                        value = (value1 >> (value2 & 0x1f)) + (2L << ~(value2 & 0x1f));
                    }
                    break;
                case AND:
                    value = value1 & value2;
                    break;
                case OR:
                    value = value1 | value2;
                    break;
                case XOR:
                    value = value1 ^ value2;
                    break;
                default:
                    throw new UnimplementedError();
            }
            System.err.println(name + ": " + value1 + " " + operation + " " + value2 + " = " + value);
            pushFunc.accept(stack, (N) value);
        } else if (rawValue1 instanceof Double) {
            double value1 = (Double) rawValue1;
            double value2 = (Double) rawValue2;
            switch (operation) {
                case ADD:
                    value = value1 + value2;
                    break;
                case SUB:
                    value = value1 - value2;
                    break;
                case MUL:
                    value = value1 * value2;
                    break;
                case DIV:
                    value = value1 / value2;
                    break;
                case REM:
                    value = value1 % value2;
                    break;
                default:
                    throw new UnimplementedError();
            }
            System.err.println(name + ": " + value1 + " " + operation + " " + value2 + " = " + value);
            pushFunc.accept(stack, (N) value);
        } else {
            throw new UnimplementedError();
        }
    }


    @Override
    public String toString() {
        return name;
    }
}
