package de.textmode.afpbox.ptoca;

import org.apache.commons.codec.binary.Hex;

/**
 * Unit-Tests for the class {@link UnknownControlSequence}.
 */
public final class UnknownControlSequenceTest extends PtocaControlSequenceTest<UnknownControlSequence> {

    /**
     * Tries to parse a control sequence which is unknown (function type 0xFE).
     */
    public void testUnknownControlSequence() throws Exception {
        final String hex = "04FE0001";
        final UnknownControlSequence ucs = this.parse("2BD3" + hex);

        assertEquals(0xFE, ucs.getFunctionType());
        assertEquals(4, ucs.getLength());
        assertEquals(hex, Hex.encodeHexString(ucs.getData()).toUpperCase());
    }
}
