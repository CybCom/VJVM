package vjvm.runtime;

import vjvm.utils.UnimplementedError;

import java.util.Arrays;
import java.util.Optional;

/**
 * Slots represents an array of JVM slots as defined in the specification. It
 * supports getting and putting primitive data types, including address.
 */
public class Slots {
    enum Type { // since this is an inner class, it won't use more memory.
        BLANK, BYTE, CHAR, SHORT, INT, FLOAT, LONG, DOUBLE, LONG2, DOUBLE2, REFERENCE
    }
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
        // assert typeList[index] == Type.INT;
        return (int) slotList[index];
    }

    public void int_(int index, int value) {
        // TO-DO: set the int at index
        // throw new UnimplementedError();
        // assert typeList[index] == Type.BLANK;
        typeList[index] = Type.INT;
        slotList[index] = value;
    }

    public long long_(int index) {
        // TO-DO: return the long at index
        // throw new UnimplementedError();
        // assert typeList[index] == Type.LONG;
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
        // assert typeList[index] == Type.FLOAT;
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
        // assert typeList[index] == Type.DOUBLE;
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

    public Object reference_(int index) {
        // TO-DO: return the short at index
        // throw new UnimplementedError();
        assert typeList[index] == Type.REFERENCE;
        return slotList[index];
    }

    public void reference_(int index, Object value) {
        // TO-DO: set the short at index
        // throw new UnimplementedError();
        assert typeList[index] == Type.BLANK;
        typeList[index] = Type.REFERENCE;
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
                case REFERENCE:
                    return Optional.of(reference_(index));
                default:
                    throw new IllegalStateException("Unexpected value: " + typeList[index]);
            }
        }
    }



    public void value(int index, Optional valueOptional) {
        // automatically input a value.
        // assert typeList[index] == Type.BLANK;
        assert valueOptional.isPresent();
        Object value = valueOptional.get();
        Type targetType;
        if (value instanceof Integer) {
            targetType = Type.INT;
        } else if (value instanceof Float) {
            targetType = Type.FLOAT;
        } else if (value instanceof Long) {
            targetType = Type.LONG;
        } else if (value instanceof Double) {
            targetType = Type.DOUBLE;
        } else if (value instanceof Byte) {
            targetType = Type.BYTE;
        } else if (value instanceof Character) {
            targetType = Type.CHAR;
        } else if (value instanceof Short) {
            targetType = Type.SHORT;
        } else {
            throw new UnimplementedError();
        }

        switch (targetType) {
            case CHAR:
                char_(index, (Character) value);
                break;
            case BYTE:
                byte_(index, (Byte) value);
                break;
            case INT:
                int_(index, (Integer) value);
                break;
            case FLOAT:
                float_(index, (Float) value);
                break;
            case SHORT:
                short_(index, (Short) value);
                break;
            case LONG:
                // assert typeList[index + 1] == Type.BLANK;
                long_(index, (Long) value);
                break;
            case DOUBLE:
                assert typeList[index + 1] == Type.BLANK;
                double_(index, (Double) value);
                break;
            case REFERENCE:
                assert typeList[index] == Type.BLANK;
                reference_(index, value);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + typeList[index]);
        }
    }

    /**
     * return the value at the index, while remove it from the slots.
     */
    public Optional popValue(int index) {
        // TODO(optional): return the value at index, or null if there is no value stored at index
        // return Optional.empty();
        if (typeList[index] == Type.BLANK) {
            return Optional.empty();
        } else {
            Optional value;
            switch (typeList[index]) {
                case CHAR:
                    value = Optional.of((char_(index)));
                    typeList[index] = Type.BLANK;
                    return value;
                case BYTE:
                    value = Optional.of((byte_(index)));
                    typeList[index] = Type.BLANK;
                    return value;
                case INT:
                    value = Optional.of((int_(index)));
                    typeList[index] = Type.BLANK;
                    return value;
                case FLOAT:
                    value = Optional.of((float_(index)));
                    typeList[index] = Type.BLANK;
                    return value;
                case SHORT:
                    value = Optional.of((short_(index)));
                    typeList[index] = Type.BLANK;
                    return value;
                case LONG:
                    value = Optional.of((long_(index)));
                    typeList[index] = Type.BLANK;
                    typeList[index + 1] = Type.BLANK;
                    return value;
                case DOUBLE:
                    value = Optional.of((double_(index)));
                    typeList[index] = Type.BLANK;
                    typeList[index + 1] = Type.BLANK;
                    return value;
                case LONG2:
                case DOUBLE2:
                    return popValue(index - 1);
                case REFERENCE:
                    value = Optional.of(reference_(index));
                    typeList[index] = Type.BLANK;
                    return value;
                default:
                    throw new IllegalStateException("Unexpected value: " + typeList[index]);
            }
        }
    }

    public Type typeOf(int index) {
        return typeList[index];
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
}
