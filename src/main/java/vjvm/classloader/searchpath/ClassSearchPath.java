package vjvm.classloader.searchpath;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.jar.JarFile;

/**
 * Represents a path to search class files in.
 * A search path may hold resources, such as jar files, so it must implement the Closeable interface.
 * If a subclass doesn't hold any resources, then just do nothing.
 */
public abstract class ClassSearchPath implements Closeable {
    /**
     * Construct search path objects with a given path.
     */
    public static ClassSearchPath[] constructSearchPath(String path) {
        String sep = System.getProperty("path.separator");
        // throw new UnimplementedError("TODO: parse path and return an array of search paths");
        String[] pathStringArray = path.split(sep);
        int arrayLength = pathStringArray.length;
        ClassSearchPath[] pathObjectArray = new ClassSearchPath[arrayLength];

        for (int i = 0; i < arrayLength; ++i) {
            int finalI = i;
            pathObjectArray[i] = new ClassSearchPath() { // inherit the abstract class.
                @Override
                public InputStream findClass(String name) { // name is a descriptor.
                    String targetPathString = pathStringArray[finalI];
                    File targetPathObject = new File(targetPathString);

                    // reform the name of class to relative path.
                    String classPathRelativeString = descriptorToPath(name);

                    if (targetPathObject.isFile()) { // if target is .jar
                        try {   // no 'try'-with-resources, it will close file automatically.
                            JarFile targetJar = new JarFile(targetPathString);
                            return targetJar.getInputStream(targetJar.getJarEntry(classPathRelativeString));
                        } catch (NullPointerException | IOException e) {
                            return null;
                        }
                    } else if (targetPathObject.isDirectory()) { // if target is dir.
                        try {
                            return Files.newInputStream(Paths.get(targetPathString, classPathRelativeString));
                        } catch (NullPointerException | IOException e) {
                            return null;
                        }
                    } else {
                        return null;
                    }
                }

                /** Manually added
                 * convert a descriptor of a non-primitive obj to its .class path.
                 * */
                private String descriptorToPath(String descriptor) {
                    return descriptor.substring(1, descriptor.length() - 1) + ".class";
                }

                @Override
                public void close() throws IOException {

                }
            };
        }
        return pathObjectArray;
    }

    /**
     * Find a class with specified name.
     *
     * @param name name of the class to find.
     * @return Returns a stream containing the binary data if such class is found, or null if not.
     */
    public abstract InputStream findClass(String name) throws IOException;
}
