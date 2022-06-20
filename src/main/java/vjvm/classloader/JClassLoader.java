package vjvm.classloader;

import lombok.var;
import lombok.Getter;
import lombok.SneakyThrows;
import vjvm.classloader.searchpath.ClassSearchPath;
import vjvm.runtime.JClass;
import vjvm.vm.VMContext;

import java.io.Closeable;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class JClassLoader implements Closeable {
    private final JClassLoader parent;
    private final ClassSearchPath[] searchPaths;
    private final HashMap<String, JClass> definedClass = new HashMap<>();
    @Getter
    private final VMContext context;

    public JClassLoader(JClassLoader parent, ClassSearchPath[] searchPaths, VMContext context) {
        this.context = context;
        this.parent = parent;
        this.searchPaths = searchPaths;
    }

    /**
     * Load class
     * <p>
     * If a class is found, construct it using the data returned by ClassSearchPath.findClass and return it.
     * <p>
     * Otherwise, return null.
     */
    public JClass loadClass(String descriptor) {
        // throw new UnimplementedError("TODO: load class");
        // To construct a JClass, use the following constructor
        // return new JClass(new DataInputStream(istream_from_file), this);
        JClass targetClass = definedClass.get(descriptor); // search the defined.
        if (targetClass != null) {
            return targetClass;
        } else if (parent != null) {    // search the parent.
            targetClass = parent.loadClass(descriptor);
        }
        if (targetClass != null) {
            definedClass.put(descriptor, targetClass);
            return targetClass;
        } else {
            try {
                for (ClassSearchPath onePath : searchPaths) {
                    InputStream possibleFile = onePath.findClass(descriptor);
                    if (possibleFile != null) {
                        targetClass = new JClass(new DataInputStream(possibleFile), this);
                        definedClass.put(descriptor, targetClass);
                        return targetClass;
                    }
                }
            } catch (IOException e) {   // if the searchPath is null.
                return null;
            }
        }
        return null;
    }

    @Override
    @SneakyThrows
    public void close() {
        for (var s : searchPaths) {
            s.close();
        }
    }
}
