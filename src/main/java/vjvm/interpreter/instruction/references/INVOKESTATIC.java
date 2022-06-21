package vjvm.interpreter.instruction.references;

import lombok.var;
import vjvm.classfiledefs.Descriptors;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JClass;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;
import vjvm.runtime.classdata.constant.ClassConstant;
import vjvm.runtime.classdata.constant.MethodrefConstant;
import vjvm.runtime.classdata.constant.NameAndTypeConstant;

public class INVOKESTATIC extends Instruction {
    private final MethodInfo method;
    private final int index;
    private final MethodInfo targetMethodInfo;

    public INVOKESTATIC(ProgramCounter pc, MethodInfo method) {
        // TODO: decode invokestatic
        // throw new UnimplementedError();
        this.method = method;
        int indexByte1 = pc.byte_();
        int indexByte2 = pc.byte_();
        index =  (indexByte1 << 8) | indexByte2;

        // load the target class.
        JClass parentClass = method.jClass();
        MethodrefConstant targetMethordref = (MethodrefConstant) parentClass.constantPool().constant(index);

        ClassConstant targetClassConstant = (ClassConstant) parentClass.constantPool().constant(targetMethordref.classIndex());

        JClass targetClass = targetClassConstant.klass();

        NameAndTypeConstant targetNameAndType = (NameAndTypeConstant) parentClass.constantPool().constant(targetMethordref.nameAndTypeIndex());
        String targetMethodName = targetNameAndType.name();
        String targetMethodDescriptor = targetNameAndType.descriptor();

        targetMethodInfo = targetClass.findMethod(targetMethodName, targetMethodDescriptor);
        System.err.println("prepare to invoke: " + index + " for " + targetMethodName + " described as " + targetMethodDescriptor);
        System.err.println("find info " + targetMethodInfo);
    }

    @Override
    public void run(JThread thread) {
        var stack = thread.top().stack();
        var args = stack.popSlots(method.argc());

        System.err.println("invoking static: operand stack pop " + args.int_(0));

        thread.context().interpreter().invoke(targetMethodInfo, thread, args);
    }

    @Override
    public String toString() {
        return String.format("invokestatic %s:%s:%s", method.jClass().name(), method.name(), method.descriptor());
    }
}
