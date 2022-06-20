package vjvm.runtime.classdata;

import lombok.Getter;
import lombok.var;

import java.io.DataInput;
import java.io.IOException;

/**
 * Conclude 2 bytes index for one interface, to keep the format of Class file.
 * */
public class Interface {
    // and Interfaces is just an array of shorts, no more attributes.
    @Getter
    private final int interfaceInfo;

    /**
     * Build an Interface obj, to keep the int final.
     * */
    public Interface(DataInput dataInput) throws IOException {
        this.interfaceInfo = dataInput.readUnsignedShort();
    }

    /**
     * Build an array of interface, find the num automatically.
     * @param dataInput the input of class file.
     * @return an array of interface obj.
     * */
    public static Interface[] buildInterfaces(DataInput dataInput) throws IOException {
        var count = dataInput.readUnsignedShort();
        Interface[] interfaces = new Interface[count];
        for (int i = 0; i < count; ++i) {   // from 0, this is different with pool.
            interfaces[i] = new Interface(dataInput);
        }
        return interfaces;
    }
}

