package de.textmode.afpbox;

import org.apache.commons.codec.binary.Hex;

import de.textmode.afpbox.ptoca.PtocaControlSequence;
import junit.framework.TestCase;

public final class PtocaParserTest extends TestCase implements PtocaControlSequenceHandler {

    final StringBuilder sb = new StringBuilder();

    @Override
    public void setUp() {
        this.sb.setLength(0);
    }

    @Override
    public boolean handleControSequence(final int functionType, final byte[] data, final int off) {
        this.sb.append("type=");
        this.sb.append(Integer.toHexString(functionType));
        this.sb.append(" off=");
        this.sb.append(off);

        final int len = data[off];
        this.sb.append(" len=");
        this.sb.append(len);
        this.sb.append(" data=");

        final byte[] subData = new byte[len];
        System.arraycopy(data,  off,  subData, 0, len);

        this.sb.append(Hex.encodeHexString(subData));
        this.sb.append("\n");

        return false;
    }

    @Override
    public void handleControSequence(final PtocaControlSequence controlSequence) {
        this.sb.append(controlSequence.getClass().getSimpleName());
        this.sb.append("\n");
    }

    @Override
    public void handleCodePoints(final byte[] codePoints, final int off, final int len) {
        final byte[] data = new byte[len];
        System.arraycopy(codePoints, off, data, 0, len);
        this.sb.append("text=");
        this.sb.append(Hex.encodeHexString(data));
        this.sb.append("\n");
    }

    private void parseAndExpectFailure(final byte[] data, final String expectedMessage) {
        try {
            PtocaParser.parse(data, this);
            fail("This unit test should fail");
        } catch (final Exception e) {
            assert e.getMessage().contains(expectedMessage);
        }
    }

    /**
     * Corner case - parse an completely empty data stream.
     */
    public void testCompletelyEmpty() throws Exception {
        PtocaParser.parse(new byte[0], this);
    }

    /**
     * Corner case - parse an empty data stream (just the 0x2BD3 and nothing m
     */
    public void testEmpty() throws Exception {
        final byte[] data = new byte[] { 0x2B, (byte) 0xD3 };
        this.parseAndExpectFailure(data, "The PTOCA stream seems to be truncated after a control sequence class byte");
    }

    /**
     * Corner case - invalid length 1 for an PTOCA control sequence.
     */
    public void testInvalidLength() throws Exception {
        final byte[] data = new byte[] { 0x2B, (byte) 0xD3, 0x01 };
        this.parseAndExpectFailure(data, "The PTOCA stream seems to be truncated after a control sequence class byte");

        final byte[] data2 = new byte[] { 0x2B, (byte) 0xD3, 0x01, 0x0D, 0x00, 0x00 };
        this.parseAndExpectFailure(data2, "A length value 1 was read for an PTOCA control sequence.");
    }

    /**
     * Data Stream is truncated (4 bytes required but only 3 bytes available).
     */
    public void testTruncated() {
        final byte[] data = new byte[] { 0x2B, (byte) 0xD3, 0x04, 0x0D, 0x00 };
        this.parseAndExpectFailure(data,
                "Tried to parse 4 bytes at offset 2 but there are only 3 bytes left");
    }

    /**
     * Tests a rather complex data Stream...
     */
    public void testComplex() throws Exception {
        final StringBuilder input = new StringBuilder();
        input.append("2BD3");

        input.append("04D30001"); // AMB (chained)
        input.append("02D9");     // BLN (chained)
        input.append("03F003");   // SCFL (unchained)

        input.append("F1F2F3");   // EBCDIC-Text "123"

        input.append("2BD3");     // Again, start over...
        input.append("03F004");   // SCFL (unchained)

        input.append("F4F5F6");   // EBCDIC-Text "456"

        PtocaParser.parse(Hex.decodeHex(input.toString().toCharArray()), this);

        final StringBuilder expected = new StringBuilder();
        expected.append("type=d3 off=2 len=4 data=04d30001\n"); // AMB
        expected.append("type=d9 off=6 len=2 data=02d9\n");     // BLN
        expected.append("type=f0 off=8 len=3 data=03f003\n");   // SCFL
        expected.append("text=f1f2f3\n");                       // EBCDIC-Text
        expected.append("type=f0 off=16 len=3 data=03f004\n");  // SCFL
        expected.append("text=f4f5f6\n");                       // EBCDIC-Text

        assertEquals(expected.toString(), this.sb.toString());
    }
}
