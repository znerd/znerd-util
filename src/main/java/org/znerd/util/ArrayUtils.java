// See the COPYRIGHT file for copyright and license information
package org.znerd.util;

import static org.znerd.util.text.TextUtils.quote;

public class ArrayUtils {
    public static int countElements(Object[] array) {
        if (array == null) {
            return 0;
        } else {
            return array.length;
        }
    }

    public static String printQuoted(Object[] array, String infix, String beforeLast) {
        if (array == null) {
            throw new IllegalArgumentException("array == null");
        }
        infix = (infix != null) ? infix : "";
        beforeLast = (beforeLast != null) ? beforeLast : infix;

        if (array.length < 1) {
            return "";
        } else if (array.length == 1) {
            return toQuotedString(array, 0);
        } else if (array.length == 2) {
            return toQuotedString(array, 0) + beforeLast + toQuotedString(array, 1);
        }

        String result = toQuotedString(array, 0);
        for (int i = 1; i < array.length - 1; i++) {
           result += infix + toQuotedString(array, i);
        }
        result += beforeLast + toQuotedString(array, array.length - 1);
        return result;
    }

    private static String toQuotedString(Object[] array, int index) {
        Object item = array[index];
        if (item == null) {
            throw new IllegalArgumentException("array[" + index + "] == null");
        }

        String string = item.toString();
        if (string == null) {
            throw new IllegalArgumentException("array[" + index + "].toString() == null");
        }

        return quote(string);
    }

    private ArrayUtils() {
    }
}
