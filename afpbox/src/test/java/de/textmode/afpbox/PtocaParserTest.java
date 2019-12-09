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
    public boolean handleControSequence(final int functionType, final int off, final byte[] data) {
        this.sb.append(Integer.toHexString(functionType));
        this.sb.append(" ");
        this.sb.append(off);
        this.sb.append(" ");
        this.sb.append(Hex.encodeHexString(data));
        this.sb.append("\n");

        return false;
    }

    @Override
    public void handleControSequence(final PtocaControlSequence controlSequence) {
        this.sb.append(controlSequence.getClass().getSimpleName());
        this.sb.append("\n");
    }

    @Override
    public void handleCodePoints(final byte[] codePoints) {
        this.sb.append(Hex.encodeHexString(codePoints));
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

        input.append("04D20001"); // AMB (chained)
        input.append("02D8"); // BLN (chained)
        input.append("03F103"); // SCFL (unchained)

        input.append("F1F2F3"); // EBCDIC-Text "123"

        input.append("2BD3"); // Again, start over...
        input.append("03F104"); // SCFL (unchained)

        input.append("F4F5F6"); // EBCDIC-Text "456"

        PtocaParser.parse(Hex.decodeHex(input.toString().toCharArray()), this);

        final StringBuilder expected = new StringBuilder();
        expected.append("d2 2 04d20001\n"); //AMB
        expected.append("d8 6 02d8\n"); // BLN
        expected.append("f0 8 03f103\n"); // SCFL
        expected.append("f1f2f3\n"); // EBCDIC-Text
        expected.append("f0 16 03f104\n"); // SCFL
        expected.append("f4f5f6\n"); // EBCDIC-Text

        assertEquals(expected.toString(), this.sb.toString());
    }
}
