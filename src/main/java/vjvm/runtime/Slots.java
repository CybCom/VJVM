package vjvm.runtime;

import java.util.Arrays;
import java.util.Optional;

/**
 * Slots represents an array of JVM slots as defined in the specification. It
 * supports getting and putting primitive data types, including address.
 */
public class Slots {
    Object[] slotList; // simpler :-)
    Type[] typeList; // maintain the type in slots.
    public Slots(int slotSize) {
        // TODO: initialize data structures
        // throw new UnimplementedError();
        slotList = new Object[slotSize];
        typeList = new Type[slotSize];
        Arrays.fill(slotList, null);
        Arrays.fill(typeList, Type.BLANK);
    }

    public int int_(int index) {
        // TO-DO: return the int at index
        // throw new UnimplementedError();
        assert typeList[index] == Type.INT;
        return (int) slotList[index];
    }

    public void int_(int index, int value) {
        // TO-DO: set the int at index
        // throw new UnimplementedError();
        assert typeList[index] == Type.BLANK;
        typeList[index] = Type.INT;
        slotList[index] = value;
    }

    public long long_(int index) {
        // TO-DO: return the long at index
        // throw new UnimplementedError();
        assert typeList[index] == Type.LONG;
        return (long) slotList[index];
    }

    public void long_(int index, long value) {
        // TO-DO: set the long at index
        // throw new UnimplementedError();
        assert typeList[index] == Type.BLANK && typeList[index + 1] == Type.BLANK;
        typeList[index] = Type.LONG;
        typeList[index + 1] = Type.LONG2;
        slotList[index] = value;
    }

    public float float_(int index) {
        // TO-DO: return the float at index
        // throw new UnimplementedError();
        assert typeList[index] == Type.FLOAT;
        return (float) slotList[index];
    }

    public void float_(int index, float value) {
        // TO-DO: set the float at index
        // throw new UnimplementedError();
        assert typeList[index] == Type.BLANK;
        typeList[index] = Type.FLOAT;
        slotList[index] = value;
    }

    public double double_(int index) {
        // TO-DO: return the double at index
        // throw new UnimplementedError();
        assert typeList[index] == Type.DOUBLE;
        return (double) slotList[index];
    }

    public void double_(int index, double value) {
        // TO-DO: set the double at index
        // throw new UnimplementedError();
        assert typeList[index] == Type.BLANK && typeList[index + 1] == Type.BLANK;
        typeList[index] = Type.DOUBLE;
        typeList[index + 1] = Type.DOUBLE2;
        slotList[index] = value;
    }

    public byte byte_(int index) {
        // TO-DO: return the byte at index
        // throw new UnimplementedError();
        assert typeList[index] == Type.BYTE;
        return (byte) slotList[index];
    }

    public void byte_(int index, byte value) {
        // TO-DO: set the byte at index
        // throw new UnimplementedError();
        assert typeList[index] == Type.BLANK;
        typeList[index] = Type.BYTE;
        slotList[index] = value;
    }

    public char char_(int index) {
        // TO-DO: return the char at index
        // throw new UnimplementedError();
        assert typeList[index] == Type.CHAR;
        return (char) slotList[index];
    }

    public void char_(int index, char value) {
        // TO-DO: set the char at index
        // throw new UnimplementedError();
        assert typeList[index] == Type.BLANK;
        typeList[index] = Type.CHAR;
        slotList[index] = value;
    }

    public short short_(int index) {
        // TO-DO: return the short at index
        // throw new UnimplementedError();
        assert typeList[index] == Type.SHORT;
        return (short) slotList[index];
    }

    public void short_(int index, short value) {
        // TO-DO: set the short at index
        // throw new UnimplementedError();
        assert typeList[index] == Type.BLANK;
        typeList[index] = Type.SHORT;
        slotList[index] = value;
    }

    /**
     * return the packaged value at the index, without operate on the value.
     */
    public Optional<Object> value(int index) {
        // TODO(optional): return the value at index, or null if there is no value stored at index
        // return Optional.empty();
        if (typeList[index] == Type.BLANK) {
            return Optional.empty();
        } else {
            switch (typeList[index]) {
                case CHAR:
                    return Optional.of((char_(index)));
                case BYTE:
                    return Optional.of((byte_(index)));
                case INT:
                    return Optional.of((int_(index)));
                case FLOAT:
                    return Optional.of((float_(index)));
                case SHORT:
                    return Optional.of((short_(index)));
                case LONG:
                    return Optional.of((long_(index)));
                case DOUBLE:
                    return Optional.of((double_(index)));
                case LONG2:
                case DOUBLE2:
                    return value(index - 1);
                default:
                    throw new IllegalStateException("Unexpected value: " + typeList[index]);
            }
        }
    }

    /**
     * return the value at the index, while remove it from the slots.
     */
    public Optional<Object> popValue(int index) {
        // TODO(optional): return the value at index, or null if there is no value stored at index
        // return Optional.empty();
        if (typeList[index] == Type.BLANK) {
            return Optional.empty();
        } else {
            switch (typeList[index]) {
                case CHAR:
                    typeList[index] = Type.BLANK;
                    return Optional.of((char_(index)));
                case BYTE:
                    typeList[index] = Type.BLANK;
                    return Optional.of((byte_(index)));
                case INT:
                    typeList[index] = Type.BLANK;
                    return Optional.of((int_(index)));
                case FLOAT:
                    typeList[index] = Type.BLANK;
                    return Optional.of((float_(index)));
                case SHORT:
                    typeList[index] = Type.BLANK;
                    return Optional.of((short_(index)));
                case LONG:
                    typeList[index] = Type.BLANK;
                    typeList[index + 1] = Type.BLANK;
                    return Optional.of((long_(index)));
                case DOUBLE:
                    typeList[index] = Type.BLANK;
                    typeList[index + 1] = Type.BLANK;
                    return Optional.of((double_(index)));
                case LONG2:
                case DOUBLE2:
                    return popValue(index - 1);
                default:
                    throw new IllegalStateException("Unexpected value: " + typeList[index]);
            }
        }
    }

    public Type typeOf(int index) {
        return typeList[index];
    }

    public void value(int index, Optional valueOptional, Type targetType) {
        System.err.println("push value at " + index + " with " + targetType);
        // automatically input a value.
        assert typeList[index] == Type.BLANK;
        assert valueOptional.isPresent();
        Object value = valueOptional.get();

        switch (targetType) {
            case CHAR:
                assert value instanceof Character;
                char_(index, (Character) value);
                break;
            case BYTE:
                assert value instanceof Byte;
                byte_(index, (Byte) value);
                break;
            case INT:
                assert value instanceof Integer;
                int_(index, (Integer) value);
                break;
            case FLOAT:
                assert value instanceof Float;
                float_(index, (Float) value);
                break;
            case SHORT:
                assert value instanceof Short;
                short_(index, (Short) value);
                break;
            case LONG:
                assert typeList[index + 1] == Type.BLANK;
                assert value instanceof Long;
                long_(index, (Long) value);
                break;
            case DOUBLE:
                assert typeList[index + 1] == Type.BLANK;
                assert value instanceof Double;
                double_(index, (Double) value);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + typeList[index]);
        }
    }

    public int size() {
        // TODO: return the size in the number of slots
        // throw new UnimplementedError();
        return slotList.length;
    }

    public void copyTo(int begin, int length, Slots dest, int destBegin) {
        // TODO: copy from this:[begin, begin+length) to dest:[destBegin,destBegin+length)
        // throw new UnimplementedError();
        for (int i = 0; i < length; i++) {
            dest.slotList[destBegin + i] = this.slotList[begin + i];
            dest.typeList[destBegin + i] = this.typeList[begin + i];
        }
    }

    enum Type { // since this is an inner class, it won't use more memory.
        BLANK, BYTE, CHAR, SHORT, INT, FLOAT, LONG, DOUBLE, LONG2, DOUBLE2
    }
}
