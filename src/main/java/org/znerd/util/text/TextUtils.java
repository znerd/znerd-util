// See the COPYRIGHT file for copyright and license information
package org.znerd.util.text;

public class TextUtils {

    public static final String quote(Object o) {
        return o == null ? "(null)" : "\"" + o.toString() + '"';
    }
    
    public static final boolean isEmpty(String s) {
        return s == null || "".equals(s.trim());
    }

    private TextUtils() {
    }
}
