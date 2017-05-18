package spbu.sem2.hw7.task1;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

class SuperClass {}
interface Interface {}

public class TestClass extends SuperClass implements Interface {
    private PrintWriter out;
    public int[] array;
    @Deprecated
    protected static String str;

    public TestClass(File file) throws IOException {
    }

    public int method1(String str) {
        return 0;
    }

    private void method2(String str, int i) {
    }
}
