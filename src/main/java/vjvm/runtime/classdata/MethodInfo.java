package vjvm.runtime.classdata;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.var;
import vjvm.classfiledefs.MethodDescriptors;
import vjvm.runtime.JClass;
import vjvm.runtime.classdata.attribute.Attribute;
import vjvm.runtime.classdata.constant.UTF8Constant;
import vjvm.runtime.classdata.attribute.Code;
import vjvm.utils.UnimplementedError;

import java.io.DataInput;
import java.io.IOException;

import static vjvm.classfiledefs.MethodAccessFlags.*;

public class MethodInfo {
    @Getter
    private final short accessFlags;
    @Getter
    private final String name;
    @Getter
    private final String descriptor;
    private final Attribute[] attributes;
    @Getter
    private final JClass jClass; // why here IDE think it might be final, instead of in FieldInfo?


  // if this method doesn't hava code attribute
  // (which is the case of native methods), then code is null.
  @Getter
  private Code code;

  @SneakyThrows
  public MethodInfo(DataInput dataInput, JClass jClass) {
    throw new UnimplementedError("TODO: Get method information from constant pool");
  }
    @SneakyThrows
    public MethodInfo(DataInput dataInput, JClass jClass) {
        // throw new UnimplementedError("TODO: Get method information from constant pool");
        this.jClass = jClass;

        // from here start to read.
        this.accessFlags = (short) dataInput.readUnsignedShort();

        var target = jClass.constantPool().constant(dataInput.readUnsignedShort());
        assert target instanceof UTF8Constant;
        this.name = ((UTF8Constant) target).value();

        target = jClass.constantPool().constant(dataInput.readUnsignedShort());
        assert target instanceof UTF8Constant;
        this.descriptor = ((UTF8Constant) target).value();

        this.attributes = Attribute.buildAttributes(dataInput, jClass.constantPool());
    }


    /**
     * Build an array of method info, find the num automatically.
     * @param dataInput the input of class file.
     * @param jClass the jClass conclude this fieldInfos.
     * @return an array of MethodInfo obj.
     * */
    public static MethodInfo[] buildMethods(DataInput dataInput, JClass jClass) throws IOException {
        var count = dataInput.readUnsignedShort();
        MethodInfo[] methods = new MethodInfo[count];
        for (int i = 0; i < count; ++i) {
            methods[i] = new MethodInfo(dataInput, jClass);
        }
        return methods;
    }

  public int argc() {
    return MethodDescriptors.argc(descriptor);
  }


    public boolean public_() {
        return (accessFlags & ACC_PUBLIC) != 0;
    }

    public boolean private_() {
        return (accessFlags & ACC_PRIVATE) != 0;
    }

    public boolean protected_() {
        return (accessFlags & ACC_PROTECTED) != 0;
    }

    public boolean static_() {
        return (accessFlags & ACC_STATIC) != 0;
    }

    public boolean final_() {
        return (accessFlags & ACC_FINAL) != 0;
    }

    public boolean synchronized_() {
        return (accessFlags & ACC_SYNCHRONIZED) != 0;
    }

    public boolean bridge() {
        return (accessFlags & ACC_BRIDGE) != 0;
    }

    public boolean vaargs() {
        return (accessFlags & ACC_VARARGS) != 0;
    }

    public boolean native_() {
        return (accessFlags & ACC_NATIVE) != 0;
    }

    public boolean abstract_() {
        return (accessFlags & ACC_ABSTRACT) != 0;
    }

    public boolean strict() {
        return (accessFlags & ACC_STRICT) != 0;
    }

    public boolean synthetic() {
        return (accessFlags & ACC_SYNTHETIC) != 0;
    }
}
