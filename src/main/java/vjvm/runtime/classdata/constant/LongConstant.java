package vjvm.runtime.classdata.constant;

import lombok.Getter;
import lombok.SneakyThrows;

import java.io.DataInput;

public class LongConstant extends Constant {
    @Getter
    private final long aLong;

    @SneakyThrows
    LongConstant(DataInput input) {
        aLong = input.readLong();
    }

    @Override
    public String toString() {
        return String.format("Long: %d", aLong);
    }
}
