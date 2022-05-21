package vjvm.runtime.classdata.constant;

import lombok.Getter;
import lombok.SneakyThrows;
import vjvm.runtime.classdata.ConstantPool;

import java.io.DataInput;

public class StringConstant extends Constant {
    @Getter
    private final short stringIndex;

    private final ConstantPool pool;

    @SneakyThrows
    StringConstant(DataInput input, ConstantPool pool) {
        this.pool = pool;
        stringIndex = (short) input.readUnsignedShort();
    }

    @Override
    public String toString() {
        return String.format("String: %s", ((UTF8Constant)
            pool.constant(stringIndex)).value());
    }
}
