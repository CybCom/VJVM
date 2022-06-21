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
        boolean arrayFin = true;    // if in an array like [[[[I
        boolean objFin = true;  // if in situ like [Lobj;
        for (int i = 1; descriptor.charAt(i) != ')'; i++) {
            countSum += Descriptors.size(descriptor.charAt(i));
            if (descriptor.charAt(i) == '[') {  // meet an array arg
                arrayFin = false;
            }
            if (descriptor.charAt(i) == 'L') {  // meet an obj arg
                objFin = false;
            }
            while ((!arrayFin) || (!objFin)) {
                i++;
                if (descriptor.charAt(i) == '[') {  // meet an array arg
                    arrayFin = false;
                }
                if (descriptor.charAt(i) == 'L') {  // meet an obj arg
                    objFin = false;
                }
                if (!arrayFin && descriptor.charAt(i) != '[') {
                    arrayFin = true;
                }
                if (!objFin && descriptor.charAt(i) == ';') {
                    objFin = true;
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
