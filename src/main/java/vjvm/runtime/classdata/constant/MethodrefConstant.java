package vjvm.runtime.classdata.constant;

import lombok.Getter;
import lombok.SneakyThrows;
import vjvm.runtime.classdata.ConstantPool;

import java.io.DataInput;

public class MethodrefConstant extends Constant {
    @Getter
    private final short classIndex;
    @Getter
    private final short nameAndTypeIndex;
    private final ConstantPool pool;


    @SneakyThrows
    MethodrefConstant(DataInput input, ConstantPool pool) {
        this.pool = pool;
        classIndex = (short) input.readUnsignedShort();
        nameAndTypeIndex = (short) input.readUnsignedShort();
    }

    @Override
    public String toString() {
        short nameIndex = ((ClassConstant) pool.constant(classIndex)).nameIndex();
        System.err.println("??????????" + nameIndex);
        return String.format("Methodref: " +
            ((UTF8Constant) pool.constant(nameIndex)).value() +
            "." +
            pool.constant(nameAndTypeIndex).toString());
    }
}
