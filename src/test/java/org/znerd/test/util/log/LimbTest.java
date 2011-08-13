// See the COPYRIGHT file for copyright and license information
package org.znerd.test.util.log;

import org.junit.Test;
import static org.junit.Assert.*;

import org.znerd.util.log.Limb;

public class LimbTest {

    @Test
    public void testSetNullLimb() {
        boolean ok = false;
        try {
            Limb.setLogger(null);
        } catch (IllegalArgumentException cause) {
            ok = true;
        }
        
        assertTrue("Expected IllegalArgumentException for Limb.setLogger(null).", ok);
    }
}
