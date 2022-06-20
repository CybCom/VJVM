package vjvm.runtime.classdata.constant;

import lombok.Getter;
import lombok.SneakyThrows;

import java.io.DataInput;

public class FloatConstant extends Constant {
    @Getter
    private final float bytes;

    @SneakyThrows
    FloatConstant(DataInput input) {
        bytes = input.readFloat();
    }

    @Override
    public String toString() {
        return String.format("Float: %a", bytes);
    }
}
