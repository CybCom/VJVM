package vjvm.runtime.classdata.constant;

import lombok.Getter;
import lombok.SneakyThrows;
import vjvm.runtime.JClass;
import vjvm.runtime.classdata.ConstantPool;

import java.io.DataInput;

public class MethodrefConstant extends Constant {
    @Getter
    private final short classIndex;
    @Getter
    private final short nameAndTypeIndex;
    private final JClass jClass;


    @SneakyThrows
    MethodrefConstant(DataInput input, JClass jClass) {
        this.jClass = jClass;
        classIndex = (short) input.readUnsignedShort();
        nameAndTypeIndex = (short) input.readUnsignedShort();
    }

    @Override
    public String toString() {
        ConstantPool pool = jClass.constantPool();
        short nameIndex = ((ClassConstant) pool.constant(classIndex)).nameIndex();
        return String.format("Methodref: " +
            ((UTF8Constant) pool.constant(nameIndex)).value() +
            "." +
            pool.constant(nameAndTypeIndex).toString());
    }
}
