package lab1.cases;

import java.io.Closeable;

class Foo implements Closeable {
    static long b = 2;
    final float d = 3.0f;
    int a = 1;
    private String c = "foo";

    public void close() {
    }
}
