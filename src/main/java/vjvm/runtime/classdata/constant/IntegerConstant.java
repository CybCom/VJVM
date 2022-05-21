package vjvm.runtime.classdata.constant;

import lombok.Getter;
import lombok.SneakyThrows;

import java.io.DataInput;

public class IntegerConstant extends Constant {
    @Getter
    private final int bytes;

    @SneakyThrows
    IntegerConstant(DataInput input) {
        bytes = input.readInt();
    }

    @Override
    public String toString() {
        return String.format("Integer: %d", bytes);
    }
}
