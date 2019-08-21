package de.textmode.afpbox;

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

import java.io.IOException;
import java.util.LinkedList;

import org.apache.commons.codec.binary.Hex;

import de.textmode.afpbox.common.StructuredFieldIdentifier;
import de.textmode.afpbox.io.Record;
import de.textmode.afpbox.io.RecordReader;
import de.textmode.afpbox.structuredfield.FaultyStructuredField;
import de.textmode.afpbox.structuredfield.StructuredField;
import de.textmode.afpbox.structuredfield.StructuredFieldIntroducer;
import junit.framework.TestCase;

public final class AfpParserTest extends TestCase implements RecordReader, RecordHandler {

    private final LinkedList<Record> records = new LinkedList<>();
    private final LinkedList<Record> handledLineRecords = new LinkedList<>();
    private final LinkedList<StructuredFieldIntroducer> handledStructuredFieldIntroducers = new LinkedList<>();
    private final LinkedList<StructuredField> handledStructuredFields = new LinkedList<>();
    private final LinkedList<FaultyStructuredField> handledFaultyStructuredFields = new LinkedList<>();

    @Override
    public void setUp() {
        this.records.clear();
        this.handledLineRecords.clear();
        this.handledStructuredFieldIntroducers.clear();
        this.handledStructuredFields.clear();
        this.handledFaultyStructuredFields.clear();
    }

    /**
     * Processes an empty file.
     */
    public void testEmptyFile() throws Exception {
        new AfpParser(this, this).parse();

        assertTrue(this.records.isEmpty());
        assertTrue(this.handledLineRecords.isEmpty());
        assertTrue(this.handledStructuredFieldIntroducers.isEmpty());
        assertTrue(this.handledStructuredFields.isEmpty());
        assertTrue(this.handledFaultyStructuredFields.isEmpty());
    }

    /**
     * Processes an AFP file with a faulty Structured Field.
     */
    public void testFaultyStructuredField() throws Exception {
        // TODO implement a unit test
    }

    /**
     * Processes only line mode records.
     */
    public void testOnlyLineRecords() throws Exception {
        // Two simple Line Mode Records (with ASA control characters and three EBCDIC spaces)
        final Record record1 = new Record(Hex.decodeHex("F1404040".toCharArray()), 0);
        final Record record2 = new Record(Hex.decodeHex("40404040".toCharArray()), 1);

        this.records.add(record1);
        this.records.add(record2);

        new AfpParser(this, this).parse();

        assertEquals(this.handledLineRecords.poll(), record1);
        assertEquals(this.handledLineRecords.poll(), record2);
        assertEquals(this.handledLineRecords.poll(), null);

        assertTrue(this.handledStructuredFieldIntroducers.isEmpty());
        assertTrue(this.handledStructuredFields.isEmpty());
        assertTrue(this.handledFaultyStructuredFields.isEmpty());
    }

    /**
     * Processes a mixed-mode file.
     */
    public void testMixedModeFile() throws Exception {
        // AFP records and simple Line Mode Records (with ASA control characters and three EBCDIC spaces)
        final Record nopRecord1 = new Record(Hex.decodeHex("5A0008D3A8A5000000".toCharArray()), 0);
        final Record lineRecord1 = new Record(Hex.decodeHex("F1404040".toCharArray()), 1);
        final Record lineRecord2 = new Record(Hex.decodeHex("40404040".toCharArray()), 2);
        final Record nopRecord2 = new Record(Hex.decodeHex("5A000BD3EEEE000000F1F2F3".toCharArray()), 3);

        this.records.add(nopRecord1);
        this.records.add(lineRecord1);
        this.records.add(lineRecord2);
        this.records.add(nopRecord2);

        new AfpParser(this, this).parse();

        assertEquals(this.handledLineRecords.poll(), lineRecord1);
        assertEquals(this.handledLineRecords.poll(), lineRecord2);
        assertEquals(this.handledLineRecords.poll(), null);

        final StructuredFieldIntroducer sfi1 = this.handledStructuredFieldIntroducers.poll();
        assertEquals(sfi1.getStructuredFieldIdentifier(), StructuredFieldIdentifier.BPF);

        final StructuredFieldIntroducer sfi2 = this.handledStructuredFieldIntroducers.poll();
        assertEquals(sfi2.getStructuredFieldIdentifier(), StructuredFieldIdentifier.NOP);

        assertEquals(this.handledStructuredFieldIntroducers.poll(), null);

        assertTrue(this.handledStructuredFields.isEmpty());
        assertTrue(this.handledFaultyStructuredFields.isEmpty());
    }

    @Override
    public Record read() throws IOException {
        return this.records.poll();
    }

    @Override
    public void handleLineRecord(final Record record) {
        this.handledLineRecords.add(record);
    }

    @Override
    public boolean handleStructuredFieldIntroducer(final StructuredFieldIntroducer sfi) {
        this.handledStructuredFieldIntroducers.add(sfi);
        return false;
    }

    @Override
    public void handleStructuredField(final StructuredField sf) {
        this.handledStructuredFields.add(sf);
    }

    @Override
    public void handleFaultyStructuredField(final FaultyStructuredField sf) {
        this.handledFaultyStructuredFields.add(sf);
    }
}
