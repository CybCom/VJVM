package vjvm.runtime.classdata.constant;

import lombok.Getter;
import lombok.SneakyThrows;
import vjvm.runtime.JClass;
import vjvm.runtime.classdata.ConstantPool;

import java.io.DataInput;

public class ClassConstant extends Constant {
    // All index to pool pre-referenced.
    @Getter
    private final short nameIndex;

    private final ConstantPool pool;

    @SneakyThrows
    ClassConstant(DataInput input, ConstantPool pool) {
        nameIndex = (short) input.readUnsignedShort();
        this.pool = pool;

        // DO NOT do like this, since the pool was not full here.
        // name = ((UTF8Constant)
        //     jClass.constantPool().constant(input.readUnsignedShort())).value();
    }

    @Override
    public String toString() {
        return String.format("Class: " + ((UTF8Constant)
            pool.constant(nameIndex)).value());
    }
}
