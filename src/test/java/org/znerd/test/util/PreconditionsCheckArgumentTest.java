// See the COPYRIGHT file for copyright and license information
package org.znerd.test.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.znerd.util.Preconditions;

public class PreconditionsCheckArgumentTest {

    @Test
    public void testConditionFalse() {
        Preconditions.checkArgument(false, "This should not throw an exception.");
    }
    
    @Test
    public void testConditionTrue() {
        String errorMessage = "This should indeed throw an exception.";
        try {
            Preconditions.checkArgument(true, errorMessage);
            fail("Expected an IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            assertEquals(errorMessage, e.getMessage());
            assertNull(e.getCause());
        }
    }
}
