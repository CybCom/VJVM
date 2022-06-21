package vjvm.runtime.classdata.attribute;

import lombok.Getter;
import lombok.SneakyThrows;
import vjvm.runtime.classdata.ConstantPool;

import java.io.DataInput;

@Getter
public class Code extends Attribute {
    private final int maxStack;
    private final int maxLocals;
    private final byte[] code; // the bytecode represented as raw bytes
    private final Attribute[] attributes;

    @SneakyThrows
    Code(DataInput input, int attrLength, ConstantPool constantPool) {
        // TODO: construct code
        // throw new UnimplementedError();
        maxStack = input.readShort();
        maxLocals = input.readShort();

        int code_length = input.readInt();
        code = new byte[code_length];
        for (int i = 0; i < code_length; i++) {
            code[i] = input.readByte();
        }

        short exception_table_length = input.readShort();
        for (int i = 0; i < exception_table_length; i++) {
            input.readLong();   // 4 * u2
        }

        attributes = Attribute.buildAttributes(input, constantPool);
    }
}
