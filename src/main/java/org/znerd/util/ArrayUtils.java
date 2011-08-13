// See the COPYRIGHT file for copyright and license information
package org.znerd.util;

public class ArrayUtils {
    public static int countElements(Object[] array) {
        if (array == null) {
            return 0;
        } else {
            return array.length;
        }
    }
    
    private ArrayUtils() {
    }
}
