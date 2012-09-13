// See the COPYRIGHT file for copyright and license information
package org.znerd.util.text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.znerd.util.Preconditions;

/**
 * Text string utility functions.
 */
public class TextUtils {

    private static final String NULL_STRING = "(null)";

    /**
     * Puts quote characters around the string representation of an object, escaping all backslash and quote characters inside the string. Returns <code>"(null)"</code> if the object is
     * <code>null</code> or if the object's <code>toString()</code> method returns <code>null</code>.
     */
    public static final String quote(Object o) {
        if (o == null) {
            return NULL_STRING;
        }

        String s = o.toString();
        if (s == null) {
            return NULL_STRING;
        }

        s = s.replaceAll("\\\\", Matcher.quoteReplacement("\\\\"));
        s = s.replaceAll("\"", Matcher.quoteReplacement("\\\""));
        return '"' + s + '"';
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
        Preconditions.checkArgument(s == null, "s == null");
        Preconditions.checkArgument(regex == null, "regex == null");
        return Pattern.compile(regex).matcher(s).find();
    }

    /**
     * Compares the specified strings for equality, after normalizing whitespace and ignoring case.
     * 
     * @param s1
     *            the first string, or <code>null</code>.
     * @param s2
     *            the second string, or <code>null</code>.
     * @return if <code>s1</code> and <code>s2</code> are considered equal, normalizing whitespace and ignoring case.
     */
    public static boolean fuzzyEquals(String s1, String s2) {
        s1 = normalizeWhitespace(s1);
        s2 = normalizeWhitespace(s2);

        return s1.equalsIgnoreCase(s2);
    }

    /**
     * Removes all leading and trailing whitespace from a string, and replaces all internal whitespace with a single space character. If <code>null</code> is passed, then <code>""</code> is returned.
     * 
     * @param s
     *            the string, or <code>null</code>.
     * @return the string with all leading and trailing whitespace removed and all internal whitespace normalized, never <code>null</code>.
     */
    public static String normalizeWhitespace(String s) {
        String normalized = "";
        if (s != null) {
            s = s.trim();
            boolean prevIsWhitespace = false;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                boolean thisIsWhitespace = (c <= 0x20);

                if (!(thisIsWhitespace && prevIsWhitespace)) {
                    if (thisIsWhitespace) {
                        normalized += ' ';
                        prevIsWhitespace = true;
                    } else {
                        normalized += c;
                        prevIsWhitespace = false;
                    }
                }
            }
        }
        return normalized;
    }

    private TextUtils() {
    }
}
