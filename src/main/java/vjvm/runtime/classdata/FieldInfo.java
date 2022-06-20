package vjvm.runtime.classdata;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.var;
import vjvm.runtime.JClass;
import vjvm.runtime.classdata.attribute.Attribute;
import vjvm.runtime.classdata.constant.UTF8Constant;

import java.io.DataInput;
import java.io.IOException;

import static vjvm.classfiledefs.FieldAccessFlags.*;

@RequiredArgsConstructor
public class FieldInfo {
    // Is this really useful to break the specification?
    @Getter
    private final short accessFlags;
    @Getter
    private final String name;
    @Getter
    private final String descriptor;
    private final Attribute[] attributes;
    @Getter
    private JClass jClass;

    @SneakyThrows
    public FieldInfo(DataInput dataInput, JClass jClass) {
        // throw new UnimplementedError("TODO: get field info from constant pool");
        this.jClass = jClass;

        // from here start to read.
        this.accessFlags = (short) dataInput.readUnsignedShort();

        // confirm it is a UTF-8 obj.
        var target = jClass.constantPool().constant(dataInput.readUnsignedShort());
        this.name = ((UTF8Constant) target).value();

        target = jClass.constantPool().constant(dataInput.readUnsignedShort());
        this.descriptor = ((UTF8Constant) target).value();

        this.attributes = Attribute.buildAttributes(dataInput, jClass.constantPool());
    }

    /**
     * Build an array of field info, find the num automatically.
     *
     * @param dataInput the input of class file.
     * @param jClass    the jClass conclude this fieldInfos.
     * @return an array of FieldInfo obj.
     */
    public static FieldInfo[] buildFields(DataInput dataInput, JClass jClass) throws IOException {
        var count = dataInput.readUnsignedShort();
        FieldInfo[] fields = new FieldInfo[count];
        for (int i = 0; i < count; ++i) {
            fields[i] = new FieldInfo(dataInput, jClass);
        }
        return fields;
    }

    public int attributeCount() {
        return attributes.length;
    }

    public Attribute attribute(int index) {
        return attributes[index];
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

    public boolean transient_() {
        return (accessFlags & ACC_TRANSIENT) != 0;
    }

    public boolean synthetic() {
        return (accessFlags & ACC_SYNTHETIC) != 0;
    }

    public boolean enum_() {
        return (accessFlags & ACC_ENUM) != 0;
    }
}
