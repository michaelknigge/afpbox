package de.textmode.afpbox.ptoca;

/*
 * Copyright 2019 Michael Knigge
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.binary.Hex;

/**
 * Unit-Tests for the class {@link GlyphLayoutControl}.
 */
public final class GlyphLayoutControlTest extends PtocaControlSequenceTest<GlyphLayoutControl> {

    /**
     * Checks if a faulty GLC is determined.
     */
    public void testFaulty() throws Exception {
        this.parseAndExpectFailure("2BD3036D01",
                "PTOCA control sequence GLC has invalid length of 3 bytes (expected at least 10 bytes)");
    }

    /**
     * GLC without font OID and font name.
     */
    public void testWithoutObjectIdentifierAndName() throws Exception {
        final GlyphLayoutControl glc = this.parse("2BD30A6D1122000000000000");
        assertEquals(0x1122, glc.getAdvance());
        assertEquals(0, glc.getFontObjectIdentifierLength());
        assertEquals(0, glc.getFontNameLength());
        assertEquals(0, glc.getFontObjectIdentifier().length);
        assertEquals(0, glc.getFontName().length());
    }

    /**
     * GLC with font OID but without font name.
     */
    public void testWithObjectIdentifierButWithoutName() throws Exception {
        final String oid = "0102030405060708090A0B0C0D";

        final StringBuilder sb = new StringBuilder();
        sb.append("2BD3");
        sb.append("17"); // Length
        sb.append("6D"); //  GLC
        sb.append("8000"); // Advance
        sb.append("0D"); // Length of FontOID
        sb.append("00"); // Length of FontName
        sb.append("00000000"); // Reserved
        sb.append(oid); // Font OID

        final GlyphLayoutControl glc = this.parse(sb.toString());
        assertEquals(-32768, glc.getAdvance());
        assertEquals(0x0D, glc.getFontObjectIdentifierLength());
        assertEquals(0, glc.getFontNameLength());
        assertEquals(oid, Hex.encodeHexString(glc.getFontObjectIdentifier()).toUpperCase());
        assertEquals(0, glc.getFontName().length());
    }

    /**
     * GLC without font OID but with font name.
     */
    public void testWithoutObjectIdentifierButWithName() throws Exception {
        final String name = "Arial";
        final byte[] nameBin = StandardCharsets.UTF_16BE.encode(name).array();

        final StringBuilder sb = new StringBuilder();
        sb.append("2BD3");
        sb.append("14"); // Length
        sb.append("6D"); //  GLC
        sb.append("7FFF"); // Advance
        sb.append("00"); // Length of FontOID
        sb.append("0A"); // Length of FontName (in bytes!)
        sb.append("00000000"); // Reserved
        sb.append(Hex.encodeHexString(nameBin).toUpperCase()); // Font name

        final GlyphLayoutControl glc = this.parse(sb.toString());
        assertEquals(32767, glc.getAdvance());
        assertEquals(0, glc.getFontObjectIdentifierLength());
        assertEquals(10, glc.getFontNameLength());
        assertEquals(0, glc.getFontObjectIdentifier().length);
        assertEquals(name, glc.getFontName());
    }

    /**
     * GLC with font OID and font name.
     */
    public void testWithObjectIdentifierAndName() throws Exception {
        final String oid = "0102030405060708090A0B0C0D";
        final String name = "Arial";
        final byte[] nameBin = StandardCharsets.UTF_16BE.encode(name).array();

        final StringBuilder sb = new StringBuilder();
        sb.append("2BD3");
        sb.append("21"); // Length
        sb.append("6D"); //  GLC
        sb.append("0001"); // Advance
        sb.append("0D"); // Length of FontOID
        sb.append("0A"); // Length of FontName (in bytes!)
        sb.append("00000000"); // Reserved
        sb.append(oid);
        sb.append(Hex.encodeHexString(nameBin).toUpperCase()); // Font name

        final GlyphLayoutControl glc = this.parse(sb.toString());
        assertEquals(1, glc.getAdvance());
        assertEquals(13, glc.getFontObjectIdentifierLength());
        assertEquals(10, glc.getFontNameLength());
        assertEquals(oid, Hex.encodeHexString(glc.getFontObjectIdentifier()).toUpperCase());
        assertEquals(name, glc.getFontName());
    }
}
