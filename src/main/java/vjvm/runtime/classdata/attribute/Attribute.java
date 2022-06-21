package vjvm.runtime.classdata.attribute;

import lombok.SneakyThrows;
import lombok.var;
import vjvm.runtime.classdata.ConstantPool;
import vjvm.runtime.classdata.constant.UTF8Constant;

import java.io.DataInput;

import static vjvm.classfiledefs.AttributeTags.ATTR_Code;

public abstract class Attribute {
    // yes, we do not need to reference to pool now, but is this perfect?
    // private final String name;
    // private final int length;
    // private final info[];

    @SneakyThrows
    public static Attribute constructFromData(DataInput input, ConstantPool constantPool) {
        var nameIndex = input.readUnsignedShort();
        var attrLength = input.readInt();

        switch (((UTF8Constant) constantPool.constant(nameIndex)).value()) {
            case ATTR_Code:
                return new Code(input, attrLength, constantPool);
            default:
                return new UnknownAttribute(input, attrLength);
        }
    }

    /**
     * Construct an array of attributes, find the num automatically.
     *
     * @param dataInput    the input of class file.
     * @param constantPool the constantPool in current JClass.
     * @return an array of Attribute obj.
     */
    @SneakyThrows
    public static Attribute[] buildAttributes(DataInput dataInput, ConstantPool constantPool) {
        var count = dataInput.readUnsignedShort();
        Attribute[] attributes = new Attribute[count];
        for (int i = 0; i < count; ++i) {
            attributes[i] = constructFromData(dataInput, constantPool);
        }
        return attributes;
    }
}
