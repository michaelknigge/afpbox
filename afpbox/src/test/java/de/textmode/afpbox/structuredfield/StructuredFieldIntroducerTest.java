package de.textmode.afpbox.structuredfield;

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

import org.apache.commons.codec.binary.Hex;

import de.textmode.afpbox.AfpException;
import de.textmode.afpbox.common.StructuredFieldIdentifier;
import de.textmode.afpbox.io.Record;
import junit.framework.TestCase;

public final class StructuredFieldIntroducerTest extends TestCase {

    /**
     * Checks a standard structured field.
     */
    public void testStandardStructuredField() throws Exception {
        final byte[] beginPrintFile = Hex.decodeHex("5A0008D3A8A5000000".toCharArray());
        final Record record = new Record(beginPrintFile, 123);

        final StructuredFieldIntroducer sfi = new StructuredFieldIntroducer(record);
        assertFalse(sfi.hasStructuredFieldIntroducerExtension());
        assertFalse(sfi.isSegmented());
        assertFalse(sfi.hasPaddingData());
        assertEquals(sfi.getPaddingDataLength(), 0);
        assertEquals(sfi.getStructuredFieldLength(), 8);
        assertEquals(sfi.getStructuredFieldIntroducerLength(), 9);
        assertEquals(sfi.getStructuredFieldIdentifier(), StructuredFieldIdentifier.BPF);
    }

    /**
     * Checks a structured field with padding data (length two bytes).
     */
    public void testStructuredFieldWithShortPaddingData() throws Exception {
        final byte[] beginPrintFile = Hex.decodeHex("5A000AD3A8A5080000FF02".toCharArray());
        final Record record = new Record(beginPrintFile, 123);

        final StructuredFieldIntroducer sfi = new StructuredFieldIntroducer(record);
        assertFalse(sfi.hasStructuredFieldIntroducerExtension());
        assertFalse(sfi.isSegmented());
        assertTrue(sfi.hasPaddingData());
        assertEquals(sfi.getPaddingDataLength(), 2);
        assertEquals(sfi.getStructuredFieldLength(), 10);
        assertEquals(sfi.getStructuredFieldIntroducerLength(), 9);
        assertEquals(sfi.getStructuredFieldIdentifier(), StructuredFieldIdentifier.BPF);
    }

    /**
     * Checks a structured field with padding data (length 255 bytes).
     */
    public void testStructuredFieldWithLongerShortPaddingData() throws Exception {
        final byte[] beginPrintFile = Hex.decodeHex("5A000AD3A8A5080000FFFF".toCharArray());
        final Record record = new Record(beginPrintFile, 123);

        final StructuredFieldIntroducer sfi = new StructuredFieldIntroducer(record);
        assertFalse(sfi.hasStructuredFieldIntroducerExtension());
        assertFalse(sfi.isSegmented());
        assertTrue(sfi.hasPaddingData());
        assertEquals(sfi.getPaddingDataLength(), 255); // To check if the data is treated unsigned!
        assertEquals(sfi.getStructuredFieldLength(), 10);
        assertEquals(sfi.getStructuredFieldIntroducerLength(), 9);
        assertEquals(sfi.getStructuredFieldIdentifier(), StructuredFieldIdentifier.BPF);
    }

    /**
     * Checks a structured field with padding data (length 511 (0x01FF) bytes).
     */
    public void testStructuredFieldWithLongPaddingData() throws Exception {
        final byte[] beginPrintFile = Hex.decodeHex("5A000BD3A8A508000001FF00".toCharArray());
        final Record record = new Record(beginPrintFile, 123);

        final StructuredFieldIntroducer sfi = new StructuredFieldIntroducer(record);
        assertFalse(sfi.hasStructuredFieldIntroducerExtension());
        assertFalse(sfi.isSegmented());
        assertTrue(sfi.hasPaddingData());
        assertEquals(sfi.getPaddingDataLength(), 511);
        assertEquals(sfi.getStructuredFieldLength(), 11);
        assertEquals(sfi.getStructuredFieldIntroducerLength(), 9);
        assertEquals(sfi.getStructuredFieldIdentifier(), StructuredFieldIdentifier.BPF);
    }

    /**
     * Checks a structured field with invalid padding data.
     */
    public void testStructuredFieldWithLongInvalidPaddingData() throws Exception {
        final byte[] beginPrintFile = Hex.decodeHex("5A000BD3A8A5080000000200".toCharArray());
        final Record record = new Record(beginPrintFile, 123);

        final StructuredFieldIntroducer sfi = new StructuredFieldIntroducer(record);
        try {
            sfi.getPaddingDataLength();
            fail("should fail because long padding data must be at least 3 bytes long");
        } catch (final AfpException e) {
            assertTrue(e.getMessage().contains("invalid length of padding"));
        }
    }

    /**
     * Checks a structured field with invalid padding data.
     */
    public void testStructuredFieldWithTooLongInvalidPaddingData() throws Exception {
        final byte[] beginPrintFile = Hex.decodeHex("5A000BD3A8A5080000EFFF00".toCharArray());
        final Record record = new Record(beginPrintFile, 123);

        final StructuredFieldIntroducer sfi = new StructuredFieldIntroducer(record);
        try {
            sfi.getPaddingDataLength();
            fail("should fail because long padding data is too long");
        } catch (final AfpException e) {
            assertTrue(e.getMessage().contains("invalid length of padding"));
        }
    }

    /**
     * Checks a structured field with a structured field extension.
     */
    public void testStructuredFieldExtension() throws Exception {
        final byte[] beginPrintFile = Hex.decodeHex("5A000BD3A8A5800000030000".toCharArray());
        final Record record = new Record(beginPrintFile, 123);

        final StructuredFieldIntroducer sfi = new StructuredFieldIntroducer(record);
        assertTrue(sfi.hasStructuredFieldIntroducerExtension());
        assertFalse(sfi.isSegmented());
        assertFalse(sfi.hasPaddingData());
        assertEquals(sfi.getPaddingDataLength(), 0);
        assertEquals(sfi.getStructuredFieldLength(), 11);
        assertEquals(sfi.getStructuredFieldIntroducerLength(), 9 + 3);
        assertEquals(sfi.getStructuredFieldIdentifier(), StructuredFieldIdentifier.BPF);
    }

    /**
     * Checks a structured field with a invalid structured field extension.
     */
    public void testInvalidStructuredFieldExtension() throws Exception {
        final byte[] beginPrintFile = Hex.decodeHex("5A000BD3A8A5800000000000".toCharArray());
        final Record record = new Record(beginPrintFile, 123);

        final StructuredFieldIntroducer sfi = new StructuredFieldIntroducer(record);
        try {
            sfi.getStructuredFieldIntroducerLength();
            fail("should fail because an invalid extension length is specified");
        } catch (final AfpException e) {
            assertTrue(e.getMessage().contains("invalid extension length"));
        }
    }

    /**
     * Checks if the "is segmented" flag is detected.
     */
    public void testStructuredFieldSegmentation() throws Exception {
        final byte[] beginPrintFile = Hex.decodeHex("5A0008D3A8A5200000".toCharArray());
        final Record record = new Record(beginPrintFile, 123);

        final StructuredFieldIntroducer sfi = new StructuredFieldIntroducer(record);
        assertFalse(sfi.hasStructuredFieldIntroducerExtension());
        assertTrue(sfi.isSegmented());
        assertFalse(sfi.hasPaddingData());
        assertEquals(sfi.getPaddingDataLength(), 0);
        assertEquals(sfi.getStructuredFieldLength(), 8);
        assertEquals(sfi.getStructuredFieldIntroducerLength(), 9);
        assertEquals(sfi.getStructuredFieldIdentifier(), StructuredFieldIdentifier.BPF);
    }

    /**
     * Checks if an invalid structured field length is detected (7 Bytes is too short).
     */
    public void testTooShortStructuredFieldLength() throws Exception {
        final byte[] beginPrintFile = Hex.decodeHex("5A0007D3A8A5200000".toCharArray());
        final Record record = new Record(beginPrintFile, 123);

        final StructuredFieldIntroducer sfi = new StructuredFieldIntroducer(record);
        try {
            sfi.getStructuredFieldLength();
            fail("Should fail because the record contains a too short structured field length");
        } catch (final AfpException e) {
            assertTrue(e.getMessage().contains("invalid structured field length"));
        }
    }

    /**
     * Checks if an invalid structured field length is detected (X'80000' Bytes is too long).
     */
    public void testTooLongStructuredFieldLength() throws Exception {
        final byte[] beginPrintFile = Hex.decodeHex("5A8000D3A8A5200000".toCharArray());
        final Record record = new Record(beginPrintFile, 123);

        final StructuredFieldIntroducer sfi = new StructuredFieldIntroducer(record);
        try {
            sfi.getStructuredFieldLength();
            fail("Should fail because the record contains a too large structured field length");
        } catch (final AfpException e) {
            assertTrue(e.getMessage().contains("invalid structured field length"));
        }
    }
}
