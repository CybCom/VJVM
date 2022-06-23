package vjvm.interpreter.instruction.stores;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.OperandStack;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

import java.util.Optional;
import java.util.function.Function;

/**
 * store value from op stack to frame vars at index of bytes or specific value.
 * */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class XSTORE<T> extends Instruction {
    private int index;
    private final Function<OperandStack, T> popFunc;
    private String name;

    public static final XSTORE ISTORE(ProgramCounter pc, MethodInfo method) {
        return new XSTORE<Integer>(pc.byte_(), OperandStack::popInt, "istore");
    }

    public static final XSTORE LSTORE(ProgramCounter pc, MethodInfo method) {
        return new XSTORE<Long>(pc.byte_(), OperandStack::popLong, "lstore");
    }

    public static final XSTORE FSTORE(ProgramCounter pc, MethodInfo method) {
        return new XSTORE<Float>(pc.byte_(), OperandStack::popFloat, "fstore");
    }

    public static final XSTORE DSTORE(ProgramCounter pc, MethodInfo method) {
        return new XSTORE<Double>(pc.byte_(), OperandStack::popDouble, "dstore");
    }

    public static final XSTORE ISTORE_0(ProgramCounter pc, MethodInfo method) {
        return new XSTORE<Integer>(0, OperandStack::popInt, "istore_0");
    }

    public static final XSTORE ISTORE_1(ProgramCounter pc, MethodInfo method) {
        return new XSTORE<Integer>(1, OperandStack::popInt, "istore_1");
    }

    public static final XSTORE ISTORE_2(ProgramCounter pc, MethodInfo method) {
        return new XSTORE<Integer>(2, OperandStack::popInt, "istore_2");
    }

    public static final XSTORE ISTORE_3(ProgramCounter pc, MethodInfo method) {
        return new XSTORE<Integer>(3, OperandStack::popInt, "istore_3");
    }

    public static final XSTORE LSTORE_0(ProgramCounter pc, MethodInfo method) {
        return new XSTORE<Long>(0, OperandStack::popLong, "lstore_0");
    }

    public static final XSTORE LSTORE_1(ProgramCounter pc, MethodInfo method) {
        return new XSTORE<Long>(1, OperandStack::popLong, "lstore_1");
    }

    public static final XSTORE LSTORE_2(ProgramCounter pc, MethodInfo method) {
        return new XSTORE<Long>(2, OperandStack::popLong, "lstore_2");
    }

    public static final XSTORE LSTORE_3(ProgramCounter pc, MethodInfo method) {
        return new XSTORE<Long>(3, OperandStack::popLong, "lstore_3");
    }

    public static final XSTORE FSTORE_0(ProgramCounter pc, MethodInfo method) {
        return new XSTORE<Float>(0, OperandStack::popFloat, "fstore_0");
    }

    public static final XSTORE FSTORE_1(ProgramCounter pc, MethodInfo method) {
        return new XSTORE<Float>(1, OperandStack::popFloat, "fstore_1");
    }

    public static final XSTORE FSTORE_2(ProgramCounter pc, MethodInfo method) {
        return new XSTORE<Float>(2, OperandStack::popFloat, "fstore_2");
    }

    public static final XSTORE FSTORE_3(ProgramCounter pc, MethodInfo method) {
        return new XSTORE<Float>(3, OperandStack::popFloat, "fstore_3");
    }

    public static final XSTORE DSTORE_0(ProgramCounter pc, MethodInfo method) {
        return new XSTORE<Double>(0, OperandStack::popDouble, "dstore_0");
    }

    public static final XSTORE DSTORE_1(ProgramCounter pc, MethodInfo method) {
        return new XSTORE<Double>(1, OperandStack::popDouble, "dstore_1");
    }

    public static final XSTORE DSTORE_2(ProgramCounter pc, MethodInfo method) {
        return new XSTORE<Double>(2, OperandStack::popDouble, "dstore_2");
    }

    public static final XSTORE DSTORE_3(ProgramCounter pc, MethodInfo method) {
        return new XSTORE<Double>(3, OperandStack::popDouble, "dstore_3");
    }

    @Override
    public void run(JThread thread) {
        var stack = thread.top().stack();
        System.err.println(name + " at " + index);
        // T val = ; // pop value from curr stack.
        // System.err.println("store value " + val.toString());
        thread.top().vars().value(index, Optional.of(popFunc.apply(stack)));
    }

    @Override
    public String toString() {
        return name;
    }
}

