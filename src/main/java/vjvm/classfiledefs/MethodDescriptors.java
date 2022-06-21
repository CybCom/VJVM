package vjvm.classfiledefs;

import lombok.var;

public class MethodDescriptors {
    /**
     * Calculate the arg nums from a descriptor.
     */
    public static int argc(String descriptor) {
        assert descriptor.startsWith("(");

        // TODO: calculate arguments size in slots
        // throw new UnimplementedError();
        int countSum = 0;
        for (int i = 1; descriptor.charAt(i) != ')'; i++) {
            countSum += Descriptors.size(descriptor.charAt(i));
            if (descriptor.charAt(i) == '[') {  // meet an array arg
                while (descriptor.charAt(i) == '[') {
                    i++;    // while meet array of arrays, like '[[I'
                }
            }
        }
        return countSum;
    }

    public static char returnType(String descriptor) {
        assert descriptor.startsWith("(");

        var i = descriptor.indexOf(')') + 1;
        assert i < descriptor.length();
        return descriptor.charAt(i);
    }
}
