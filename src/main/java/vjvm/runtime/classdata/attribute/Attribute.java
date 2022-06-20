package vjvm.runtime.classdata.attribute;

import lombok.SneakyThrows;
import lombok.var;
import vjvm.runtime.classdata.ConstantPool;

import java.io.DataInput;
import java.io.IOException;

public abstract class Attribute {
    // yes, we do not need to reference to pool now, but is this perfect?
    // private final String name;
    // private final int length;
    // private final info[];

    // no need to finish it here. wait for next labs.
    @SneakyThrows
    public static Attribute constructFromData(DataInput input, ConstantPool constantPool) {
        var nameIndex = input.readUnsignedShort();
        var attrLength = Integer.toUnsignedLong(input.readInt());

        return new UnknownAttribute(input, attrLength);
    }

    /**
     * Construct an array of attributes, find the num automatically.
     *
     * @param dataInput    the input of class file.
     * @param constantPool the constantPool in current JClass.
     * @return an array of Attribute obj.
     */
    public static Attribute[] buildAttributes(DataInput dataInput, ConstantPool constantPool) throws IOException {
        var count = dataInput.readUnsignedShort();
        Attribute[] attributes = new Attribute[count];
        for (int i = 0; i < count; ++i) {
            attributes[i] = constructFromData(dataInput, constantPool);
        }
        return attributes;
    }
}
