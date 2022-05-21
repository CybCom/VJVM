package vjvm.runtime.classdata.constant;

import lombok.Getter;
import lombok.SneakyThrows;
import vjvm.runtime.JClass;
import vjvm.runtime.classdata.ConstantPool;

import java.io.DataInput;

public class StringConstant extends Constant {
    @Getter
    private final short stringIndex;

    private final JClass jClass;

    @SneakyThrows
    StringConstant(DataInput input, JClass jClass) {
        this.jClass = jClass;
        stringIndex = (short) input.readUnsignedShort();
    }

    @Override
    public String toString() {
        ConstantPool pool = jClass.constantPool();
        return String.format("String: %s", ((UTF8Constant)
            pool.constant(stringIndex)).value());
    }
}
