package de.textmode.afpbox.io;

/*
 * Copyright 2018 Michael Knigge
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

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.codec.binary.Hex;

/**
 * Unit-Tests for the class {@link StandardRecordReader}.
 */
public final class StandardRecordReaderTest extends RecordReaderTest {

    /**
     * Checks reading an empty file.
     */
    public void testEmptyFile() throws Exception {
        final ByteArrayInputStream is = new ByteArrayInputStream(new byte[0]);
        final StandardRecordReader reader = new StandardRecordReader(is);

        assertEquals(null, reader.read());
    }

    /**
     * Checks reading a valid file.
     */
    public void testValidFile() throws Exception {
        final byte[] beginPrintFile = Hex.decodeHex("5A0008D3A8A5000000".toCharArray());
        final byte[] beginPage = Hex.decodeHex("5A0010D3A8A5000000F0F0F0F0F0F0F0F1".toCharArray());

        final List<Record> result = this.readAndExpectSuccess(StandardRecordReader.class, beginPrintFile, beginPage);

        assertEquals(2, result.size());

        assertTrue(Arrays.equals(beginPrintFile, result.get(0).getData()));
        assertEquals(0, result.get(0).getOffset());

        assertTrue(Arrays.equals(beginPage, result.get(1).getData()));
        assertEquals(10, result.get(1).getOffset());
    }

    /**
     * Checks reading a file that does not start with x'5A'.
     */
    public void testInvalidFile() throws Exception {
        assertEquals(
                "The first record does not start with X'5A'.",
                this.readAndExpectFailure(StandardRecordReader.class, Hex.decodeHex("5B".toCharArray())));
    }

    /**
     * Checks reading a valid file that has "junk" (CRLF) after each record.
     */
    public void testValidFileWithJunkAtEndOfRecord() throws Exception {
        final byte[] crlf = Hex.decodeHex("0D0A".toCharArray());
        final byte[] beginPrintFile = Hex.decodeHex("5A0008D3A8A5000000".toCharArray());
        final byte[] beginPage = Hex.decodeHex("5A0010D3A8A5000000F0F0F0F0F0F0F0F1".toCharArray());

        final List<Record> result = this.readAndExpectSuccess(
                StandardRecordReader.class,
                beginPrintFile,
                crlf,
                beginPage,
                crlf);

        assertEquals(2, result.size());

        assertTrue(Arrays.equals(beginPrintFile, result.get(0).getData()));
        assertEquals(0, result.get(0).getOffset());

        assertTrue(Arrays.equals(beginPage, result.get(1).getData()));
        assertEquals(12, result.get(1).getOffset());
    }

    /**
     * Checks reading a file that ends unexpectedly.
     */
    public void testUnexpectedEOF() throws Exception {
        assertEquals(
                "Unexpected end of stream while reading the first length byte of record starting at offset 0",
                this.readAndExpectFailure(StandardRecordReader.class, Hex.decodeHex("5A".toCharArray())));

        assertEquals(
                "Unexpected end of stream while reading the second length byte of record starting at offset 0",
                this.readAndExpectFailure(StandardRecordReader.class, Hex.decodeHex("5A00".toCharArray())));

        assertEquals(
                "Unexpected end of stream while reading record starting at offset 0",
                this.readAndExpectFailure(StandardRecordReader.class, Hex.decodeHex("5A000A".toCharArray())));
    }

    /**
     * Checks reading a file that has an invalid length specified at offset 1+2 of each record.
     */
    public void testInvalidLength() throws Exception {
        assertEquals(
                "The length of record at offset 0 is invalid.",
                this.readAndExpectFailure(StandardRecordReader.class, Hex.decodeHex("5A0000".toCharArray())));

        assertEquals(
                "The length of record at offset 0 is invalid.",
                this.readAndExpectFailure(StandardRecordReader.class, Hex.decodeHex("5A0001".toCharArray())));
    }
}
