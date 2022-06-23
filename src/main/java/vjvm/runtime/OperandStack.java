package vjvm.runtime;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

public class OperandStack {
    @Getter
    private final Slots slots;
    @Getter
    // private final int top; WTF?
    private int top;    // top points to the last pushed element.

    public OperandStack(int stackSize) {
        // TODO: initialize data structures
        // throw new UnimplementedError();
        slots = new Slots(stackSize);
        top = -1;
    }

    public void pushInt(int value) {
        // TODO: push value
        // throw new UnimplementedError();
        top++;
        slots.value(top, Optional.of(value));
    }

    public int popInt() {
        // TODO: pop value
        // throw new UnimplementedError();
        Object value = slots.popValue(top).get();
        top--;
        if (value instanceof Integer) {     // TODO: I can not understand why there can be i2b then istore.
            return (int) value;
        } else if (value instanceof Character) {
            return Character.getNumericValue((Character) value);
        } else {
            return Integer.parseInt(value.toString());
        }
    }

    public void pushFloat(float value) {
        // TO-DO: push value
        // throw new UnimplementedError();
        top++;
        slots.value(top, Optional.of(value));
    }

    public float popFloat() {
        // TODO: pop value
        // throw new UnimplementedError();
        float value = (float) slots.popValue(top).get();
        top--;
        return value;
    }

    public void pushLong(long value) {
        // TO-DO: push value
        // throw new UnimplementedError();
        top++;
        if (top < 0) {
            top = 0;    // I don't know why.
        }
        slots.value(top, Optional.of(value));
        top++;
    }

    public long popLong() {
        // TO-DO: pop value
        // throw new UnimplementedError();
        Object value = slots.popValue(top).get();
        top -= 2;
        if (value instanceof Long) {
            return (long) value;
        } else {
            return Long.parseLong(value.toString());
        }
    }

    public void pushDouble(double value) {
        // TODO: push value
        // throw new UnimplementedError();
        top++;
        slots.value(top, Optional.of(value));
        top++;
    }

    public double popDouble() {
        // TODO: pop value
        // throw new UnimplementedError();
        double value = (double) slots.popValue(top).get();
        top -= 2;
        return value;
    }

    public void pushByte(byte value) {
        // TODO: push value
        // throw new UnimplementedError();
        top++;
        slots.value(top, Optional.of(value));
    }

    public byte popByte() {
        // TO-DO: pop value
        // throw new UnimplementedError();
        byte value = (byte) slots.popValue(top).get();
        top--;
        return value;
    }

    public void pushChar(char value) {
        // TO-DO: push value
        // throw new UnimplementedError();
        top++;
        slots.value(top, Optional.of(value));
    }

    public char popChar() {
        // TO-DO: pop value
        // throw new UnimplementedError();
        char value = (char) slots.popValue(top).get();
        top--;
        return value;
    }

    public void pushShort(short value) {
        // TO-DO: push value
        // throw new UnimplementedError();
        top++;
        slots.value(top, Optional.of(value));
    }

    public Object popReference() {
        // TO-DO: pop value
        // throw new UnimplementedError();
        Object value = slots.popValue(top).get();
        top--;
        return value;
    }

    public void pushReference(Object value) {
        // TO-DO: push value
        // throw new UnimplementedError();
        top++;
        slots.value(top, Optional.of(value));
    }

    public short popShort() {
        // TO-DO: pop value
        // throw new UnimplementedError();
        short value = (short) slots.popValue(top).get();
        top--;
        return value;
    }

    public void pushSlots(Slots slots) {    // how can this bug kept here for such long?
        // TODO: push slots
        // throw new UnimplementedError();
        for (int i = slots.size() - 1; i >= 0; i--) {
            top++;
            if (slots.typeOf(i) == Slots.Type.LONG2 || slots.typeOf(i) == Slots.Type.DOUBLE2) {
                this.slots.value(top, slots.value(i));
                top++;
                i--;
            } else {
                this.slots.value(top, slots.value(i));
            }
        }
    }

    public Slots popSlots(int count) {
        // TODO: pop count slots and return
        // throw new UnimplementedError();
        Slots newSlots = new Slots(count);
        int newTop = -1;
        for (int i = 0; i < count; i++) {
            newTop++;
            if (this.slots.typeOf(top) == Slots.Type.LONG2 || slots.typeOf(top) == Slots.Type.DOUBLE2) {
                newSlots.value(newTop, slots.value(top));
                newTop++;
                slots.typeList[top] = Slots.Type.BLANK;
                slots.typeList[top - 1] = Slots.Type.BLANK;
                top -= 2;
                i++;
            } else {
                newSlots.value(newTop, slots.value(top));
                slots.typeList[top] = Slots.Type.BLANK;
                top--;
            }
        }
        return newSlots;
    }

    public void clear() {
        // TODO: pop all slots
        // throw new UnimplementedError();
        Arrays.fill(slots.typeList, Slots.Type.BLANK);
        top = -1;
    }
}
