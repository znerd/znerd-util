// See the COPYRIGHT file for copyright and license information
package org.znerd.util.text;

import java.util.regex.Pattern;

/**
 * Text string utility functions.
 */
public class TextUtils {

    /**
     * Puts quote characters around the string representation of an object. Returns <code>"(null)"</code> if the object is <code>null</code>.
     */
    public static final String quote(Object o) {
        return o == null ? "(null)" : "\"" + o.toString() + '"';
    }

    /**
     * Checks if the specified string is either <code>null</code> or empty or contains only whitespace.
     */
    public static final boolean isEmpty(String s) {
        return s == null || "".equals(s.trim());
    }

    /**
     * Checks if the specified string matches the specified regular expression.
     * <p>
     * This method differs from {@link String#matches(String)} in that this method also supports partial matches. To give an example:
     * 
     * <pre>
     * &quot;bla bla&quot;.match(&quot;bla$&quot;); // returns false
     * TextUtils.matches(&quot;bla bla&quot;, &quot;bla$&quot;); // returns true
     * </pre>
     */
    public static final boolean matches(String s, String regex) {
        if (s == null) {
            throw new IllegalArgumentException("s == null");
        } else if (regex == null) {
            throw new IllegalArgumentException("regex == null");
        }
        return Pattern.compile(regex).matcher(s).find();
    }

    private TextUtils() {
    }
}
