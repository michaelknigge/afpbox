package de.textmode.afpbox.structuredfield;

import org.apache.commons.codec.binary.Hex;

/**
 * Unit-Tests for the class {@link NoOperation}.
 */
public final class NoOperationTest extends StructuredFieldTest<NoOperation> {

    public void testNoOperation() throws Exception {
        final String sfi = "5A0010D3EEEE000000";
        final String data = "F0F0F0F0F0F0F0F1";
        final NoOperation sf = this.parse(sfi + data);
        assertEquals(data, Hex.encodeHexString(sf.getData()).toUpperCase());
    }
}
