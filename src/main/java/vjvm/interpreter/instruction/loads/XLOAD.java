package vjvm.interpreter.instruction.loads;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.OperandStack;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

import java.util.function.BiConsumer;

/**
 * load value to op stack from frame vars at index of bytes or specific value.
 * */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class XLOAD<T> extends Instruction {
    private int index;
    private final BiConsumer<OperandStack, T> pushFunc;
    private String name;

    public static final XLOAD ILOAD(ProgramCounter pc, MethodInfo method) {
        return new XLOAD<Integer>(pc.byte_(), OperandStack::pushInt, "iload");
    }

    public static final XLOAD LLOAD(ProgramCounter pc, MethodInfo method) {
        return new XLOAD<Long>(pc.byte_(), OperandStack::pushLong, "lload");
    }

    public static final XLOAD FLOAD(ProgramCounter pc, MethodInfo method) {
        return new XLOAD<Float>(pc.byte_(), OperandStack::pushFloat, "fload");
    }

    public static final XLOAD DLOAD(ProgramCounter pc, MethodInfo method) {
        return new XLOAD<Double>(pc.byte_(), OperandStack::pushDouble, "dload");
    }

    public static final XLOAD ILOAD_0(ProgramCounter pc, MethodInfo method) {
        return new XLOAD<Integer>(0, OperandStack::pushInt, "iload_0");
    }

    public static final XLOAD ILOAD_1(ProgramCounter pc, MethodInfo method) {
        return new XLOAD<Integer>(1, OperandStack::pushInt, "iload_1");
    }

    public static final XLOAD ILOAD_2(ProgramCounter pc, MethodInfo method) {
        return new XLOAD<Integer>(2, OperandStack::pushInt, "iload_2");
    }

    public static final XLOAD ILOAD_3(ProgramCounter pc, MethodInfo method) {
        return new XLOAD<Integer>(3, OperandStack::pushInt, "iload_3");
    }

    public static final XLOAD LLOAD_0(ProgramCounter pc, MethodInfo method) {
        return new XLOAD<Long>(0, OperandStack::pushLong, "lload_0");
    }

    public static final XLOAD LLOAD_1(ProgramCounter pc, MethodInfo method) {
        return new XLOAD<Long>(1, OperandStack::pushLong, "lload_1");
    }

    public static final XLOAD LLOAD_2(ProgramCounter pc, MethodInfo method) {
        return new XLOAD<Long>(2, OperandStack::pushLong, "lload_2");
    }

    public static final XLOAD LLOAD_3(ProgramCounter pc, MethodInfo method) {
        return new XLOAD<Long>(3, OperandStack::pushLong, "lload_3");
    }

    public static final XLOAD FLOAD_0(ProgramCounter pc, MethodInfo method) {
        return new XLOAD<Float>(0, OperandStack::pushFloat, "fload_0");
    }

    public static final XLOAD FLOAD_1(ProgramCounter pc, MethodInfo method) {
        return new XLOAD<Float>(1, OperandStack::pushFloat, "fload_1");
    }

    public static final XLOAD FLOAD_2(ProgramCounter pc, MethodInfo method) {
        return new XLOAD<Float>(2, OperandStack::pushFloat, "fload_2");
    }

    public static final XLOAD FLOAD_3(ProgramCounter pc, MethodInfo method) {
        return new XLOAD<Float>(3, OperandStack::pushFloat, "fload_3");
    }

    public static final XLOAD DLOAD_0(ProgramCounter pc, MethodInfo method) {
        return new XLOAD<Double>(0, OperandStack::pushDouble, "dload_0");
    }

    public static final XLOAD DLOAD_1(ProgramCounter pc, MethodInfo method) {
        return new XLOAD<Double>(1, OperandStack::pushDouble, "dload_1");
    }

    public static final XLOAD DLOAD_2(ProgramCounter pc, MethodInfo method) {
        return new XLOAD<Double>(2, OperandStack::pushDouble, "dload_2");
    }

    public static final XLOAD DLOAD_3(ProgramCounter pc, MethodInfo method) {
        return new XLOAD<Double>(3, OperandStack::pushDouble, "dload_3");
    }

    @Override
    public void run(JThread thread) {
        var stack = thread.top().stack();
        System.err.println(name + ": " + index);
        pushFunc.accept(stack, (T) thread.top().vars().value(index).get()); // get value from curr vars.
    }

    @Override
    public String toString() {
        return name;
    }
}
