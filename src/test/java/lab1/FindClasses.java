package lab1;

import org.junit.jupiter.api.Test;
import picocli.CommandLine;
import vjvm.vm.Main;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static lab1.Utils.runCmd;

class FindClasses {
    final Path resPath = FileSystems.getDefault().getPath(System.getenv("VJVM_TESTRES_PATH"));
    final Path jarPath = resPath.resolve("lab1/cases/jar.jar");

    @Test
    void findInDir() {
        Function<String, Integer> exec = (c) -> runCmd(resPath.toString(), "lab1.cases." + c);

        assertEquals(0, exec.apply("Foo"));
    }

    @Test
    void findInJar() {
        Function<String, Integer> exec = (c) -> runCmd(jarPath.toString(), "lab1.cases." + c);

        assertEquals(0, exec.apply("jar.Bar"));
    }

    // manually added.
    @Test
    void notExistInJar() {
        Function<String, Integer> exec = (c) -> runCmd(jarPath.toString(), "lab1.cases." + c);

        assertEquals(-1, exec.apply("jar.Kar"));
    }

    @Test
    void notExistInDir() {
        Function<String, Integer> exec = (c) -> runCmd(resPath.toString(), "lab1.cases." + c);

        assertEquals(-1, exec.apply("Koo"));
    }

    @Test
    void findInJDK() {
        Function<String, Integer> exec = (c) -> runCmd(jarPath.toString(), "" + c);

        assertEquals(0, exec.apply("java.lang.String"));
    }
}
