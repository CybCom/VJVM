package vjvm.interpreter.instruction.references;

import lombok.var;
import vjvm.classfiledefs.Descriptors;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.*;
import vjvm.runtime.classdata.MethodInfo;
import vjvm.runtime.classdata.constant.ClassConstant;
import vjvm.runtime.classdata.constant.MethodrefConstant;
import vjvm.runtime.classdata.constant.NameAndTypeConstant;

public class INVOKESTATIC extends Instruction {
    private final MethodInfo method;
    private final MethodInfo targetMethodInfo;

    public INVOKESTATIC(ProgramCounter pc, MethodInfo method) {
        // TODO: decode invokestatic
        // throw new UnimplementedError();
        this.method = method;
        int indexByte1 = pc.byte_();
        int indexByte2 = pc.byte_();
        int index = (indexByte1 << 8) | indexByte2;

        // load the target class.
        JClass parentClass = method.jClass();
        MethodrefConstant targetMethordref = (MethodrefConstant) parentClass.constantPool().constant(index);

        ClassConstant targetClassConstant = (ClassConstant) parentClass.constantPool().constant(targetMethordref.classIndex());

        JClass targetClass = targetClassConstant.klass();

        NameAndTypeConstant targetNameAndType = (NameAndTypeConstant) parentClass.constantPool().constant(targetMethordref.nameAndTypeIndex());
        String targetMethodName = targetNameAndType.name();
        String targetMethodDescriptor = targetNameAndType.descriptor();

        targetMethodInfo = targetClass.findMethod(targetMethodName, targetMethodDescriptor);

        /*
         * pre exec output
         * */
        System.err.println("prepare invoke static: " + index + " for " + targetMethodName +
            " described as " + targetMethodDescriptor + " from " + targetClass);
    }

    @Override
    public void run(JThread thread) {
        var stack = thread.top().stack();
        int numArg = targetMethodInfo.argc();   // REMEMBER: targetMethod is NOT method
        var args = stack.popSlots(targetMethodInfo.argc());

        OperandStack reverseStack = new OperandStack(numArg);
        reverseStack.pushSlots(args);
        /*
        * exec output
        * */
        System.err.println("invoke static: " + targetMethodInfo + " with args " + args + " have arg " + numArg);
        thread.context().interpreter().invoke(targetMethodInfo, thread, reverseStack.slots());
    }

    @Override
    public String toString() {
        return String.format("invokestatic %s:%s:%s", targetMethodInfo.jClass().name(), targetMethodInfo.name(), targetMethodInfo.descriptor());
    }
}
