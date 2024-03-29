package vjvm.runtime.classdata.constant;

import lombok.Getter;
import lombok.SneakyThrows;

import java.io.DataInput;

public class DoubleConstant extends Constant {
    @Getter
    private final double aDouble;

    @SneakyThrows
    DoubleConstant(DataInput input) {
        aDouble = input.readDouble();
    }

    @Override
    public String toString() {
        return String.format("Double: %a", aDouble);
    }
}
