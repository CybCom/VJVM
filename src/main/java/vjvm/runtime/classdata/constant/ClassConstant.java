package vjvm.runtime.classdata.constant;

import lombok.Getter;
import lombok.SneakyThrows;
import vjvm.classfiledefs.Descriptors;
import vjvm.runtime.JClass;
import vjvm.runtime.classdata.ConstantPool;

import java.io.DataInput;

public class ClassConstant extends Constant {
    // All index to pool pre-referenced.
    @Getter
    private final short nameIndex;
    private final JClass jClass;
    private String name;
    private JClass klass;
    @SneakyThrows
    ClassConstant(DataInput input, JClass jClass) {
        nameIndex = (short) input.readUnsignedShort();
        this.jClass = jClass;

        // DO NOT do like this, since the pool was not full here.
        // name = ((UTF8Constant)
        //     jClass.constantPool().constant(input.readUnsignedShort())).value();
    }

    public JClass klass() {
        if (klass == null) {    // use the class loader of parent class to load new class by descriptor.
            klass = jClass.classLoader().loadClass(Descriptors.of(name()));
        }
        return klass;
    }

    public String name() {
        if (name == null) {
            name = ((UTF8Constant) jClass.constantPool().constant(nameIndex)).value();
        }
        return name;
    }

    @Override
    public String toString() {
        ConstantPool pool = jClass.constantPool();
        return String.format("Class: " + ((UTF8Constant)
            pool.constant(nameIndex)).value());
    }
}
