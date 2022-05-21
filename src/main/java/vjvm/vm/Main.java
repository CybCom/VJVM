package vjvm.vm;

import lombok.var;
import picocli.CommandLine;
import vjvm.classfiledefs.Descriptors;
import vjvm.runtime.JClass;
import vjvm.runtime.classdata.FieldInfo;
import vjvm.runtime.classdata.Interface;
import vjvm.runtime.classdata.MethodInfo;
import vjvm.runtime.classdata.constant.ClassConstant;
import vjvm.runtime.classdata.constant.Constant;
import vjvm.runtime.classdata.constant.UTF8Constant;
import vjvm.utils.UnimplementedError;

import java.io.IOException;
import java.util.concurrent.Callable;

import static picocli.CommandLine.*;

@Command(name = "vjvm", mixinStandardHelpOptions = true, version = "vjvm 0.0.1", description = "A toy JVM written in java", subcommands = {
    Run.class, Dump.class, CommandLine.HelpCommand.class})
public class Main implements Callable<Integer> {
    @Option(names = {"-cp",
        "--classpath"}, paramLabel = "CLASSPATH", description = "the class path to search, multiple paths should be separated by ':'")
    String userClassPath = ".";

    public static void main(String[] args) {
        System.exit(new CommandLine(new Main()).execute(args));
    }

    @Override
    public Integer call() {
        CommandLine.usage(this, System.err);
        return -1;
    }
}

@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
@Command(name = "run", description = "Execute java program")
class Run implements Callable<Integer> {
    @ParentCommand
    private Main parent;

    @Parameters(index = "0", description = "Class to run, e.g. vjvm.vm.Main")
    private String entryClass = "";

    @Parameters(index = "1..*", description = "Arguments passed to java program")
    private String[] args = {};

    @Override
    public Integer call() {
        throw new UnimplementedError("You will implement this in lab 2");
    }

}

@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
@Command(name = "dump", description = "Dump class file")
class Dump implements Callable<Integer> {
    @ParentCommand
    private Main parent;

    @Parameters(index = "0", description = "Class to dump, e.g. java.lang.String")
    private String className = "";

    @Override
    public Integer call() throws IOException {
        var ctx = new VMContext(parent.userClassPath);

        // the package vjvm.classfiledefs contains some constants and utility
        // functions that we provided for your convenience
        // please check the individual files for more information
        var descriptor = Descriptors.of(className);

        var clazz = ctx.userLoader().loadClass(descriptor);
        if (clazz == null) {
            // you can print anything to System.err; we won't check it
            System.err.printf("Can not find class %s\n", className);
            return -1;
        }

        dump(clazz);
        return 0;
    }

    private void dump(JClass clazz) {
        // throw new UnimplementedError("TODO: dump clazz in lab 1.2; remove this for 1.1");
        String lineSeparator = System.lineSeparator();
        String sep = System.getProperty("file.separator");


        System.out.println("class name: " + className.replace(".", sep));
        System.out.printf("minor version: %d" + lineSeparator, clazz.minorVersion());
        System.out.printf("major version: %d" + lineSeparator, clazz.majorVersion());
        System.out.printf("flags: 0x%x" + lineSeparator, clazz.accessFlags());

        // it's a ClassConstant, not UTF-8.
        int index = ((ClassConstant) clazz.constantPool().constant(clazz.thisClass())).nameIndex();
        System.out.println("this class: " +
            ((UTF8Constant) clazz.constantPool().constant(index)).value());
        index = ((ClassConstant) clazz.constantPool().constant(clazz.superClass())).nameIndex();
        System.out.println("super class: " +
            ((UTF8Constant) clazz.constantPool().constant(index)).value());

        System.err.println("!!!!!!!!!!!!!!!!");
        System.err.println(((UTF8Constant)clazz.constantPool().constant(((ClassConstant)clazz.constantPool().constant(11)).nameIndex())).toString());
        System.err.println("********************");

        System.out.println("constant pool:");
        printConstantPool(clazz);

        //System.out.println("interfaces:");
        //printInterfaces(clazz);
//

        System.out.println("fields:");
        printFields(clazz);
//
        //System.out.println("methods:");
        //printMethods(clazz);
    }

    private void printConstantPool(JClass clazz) {
        for (int i = 1; i < clazz.constantPool().size(); ++i) {
            Constant target = clazz.constantPool().constant(i);
            System.out.println(target.toString());
        }
    }

    private void printInterfaces(JClass clazz) {
        for (Interface i : clazz.interfaces()) {
            System.out.println(((UTF8Constant) clazz.constantPool().constant(i.interfaceInfo())).value());
        }
    }

    private void printFields(JClass clazz) {
        for (FieldInfo i : clazz.fields()) {
            System.out.printf(i.name() + "(%x" + "): " + i.descriptor(), i.accessFlags());
            System.out.println();
        }
    }

    private void printMethods(JClass clazz) {
        for (MethodInfo i : clazz.methods()) {
            System.out.printf(i.name() + "(%x" + "): " + i.descriptor(), i.accessFlags());
            System.out.println();
        }
    }
}
