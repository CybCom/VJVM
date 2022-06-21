package vjvm.runtime;

import lombok.Getter;

import java.util.Arrays;

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
        System.err.println("Push INT " + value);
        top++;
        slots.int_(top, value);
    }

    public int popInt() {
        // TODO: pop value
        // throw new UnimplementedError();
        int value = (int) slots.popValue(top).get();
        top--;
        return value;
    }

    public void pushFloat(float value) {
        // TO-DO: push value
        // throw new UnimplementedError();
        top++;
        slots.float_(top, value);
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
        slots.long_(top, value);
        top++;
    }

    public long popLong() {
        // TO-DO: pop value
        // throw new UnimplementedError();
        long value = (long) slots.popValue(top).get();
        top -= 2;
        return value;
    }

    public void pushDouble(double value) {
        // TO-DO: push value
        // throw new UnimplementedError();
        top++;
        slots.double_(top, value);
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
        // TO-DO: push value
        // throw new UnimplementedError();
        top++;
        slots.byte_(top, value);
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
        slots.char_(top, value);
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
        slots.short_(top, value);
    }

    public short popShort() {
        // TO-DO: pop value
        // throw new UnimplementedError();
        short value = (short) slots.popValue(top).get();
        top--;
        return value;
    }

    public void pushSlots(Slots slots) {
        // TODO: push slots     what does this mean? count slots?
        // throw new UnimplementedError();
        for (int i = 0; i < slots.size(); i++) {
            slots.copyTo(0, slots.size(), this.slots, top + 1);
            top += slots.size();
        }
    }

    public Slots popSlots(int count) {
        // TODO: pop count slots and return
        // throw new UnimplementedError();
        Slots newSlots = new Slots(count);
        int newTop = -1;
        for (int i = 0; i < count; i++) {
            newTop++;

            System.err.println("pop slots of op stack count " + count + ", have slots " + (top + 1));

            if (this.slots.typeOf(top) == Slots.Type.LONG2 || slots.typeOf(top) == Slots.Type.DOUBLE2) {
                newSlots.value(newTop, slots.value(top), slots.typeOf(top - 1));
                newTop++;
                slots.typeList[top] = Slots.Type.BLANK;
                slots.typeList[top - 1] = Slots.Type.BLANK;
                top -= 2;
                i++;
            } else {
                newSlots.value(newTop, slots.value(top), slots.typeOf(top));
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
