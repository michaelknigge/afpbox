package de.textmode.afpbox;

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

import java.io.EOFException;
import java.io.IOException;

import de.textmode.afpbox.io.Record;
import de.textmode.afpbox.io.RecordReader;

/**
 * The {@link AfpParser} reads AFP records using a {@link RecordReader} and interprets
 * the Structured Fields.
 */
public final class AfpParser {

    private final RecordReader reader;
    private final RecordHandler handler;

    /**
     * Constructs an {@code AfpParser}.
     *
     * @param reader   the {@link RecordReader} that will be used to read the AFP records.
     * @param handler  the {@link RecordHandler} that will be invoked for the read records.
     */
    public AfpParser(final RecordReader reader, final RecordHandler handler) {
        this.reader = reader;
        this.handler = handler;
    }

    /**
     * Reads and parses the AFP records. For read record the {@link RecordHandler} is invoked.
     *
     * @throws EOFException if the end of the stream was reached unexpectedly.
     * @throws IOException if an I/O error occurs.
     * @throws AfpException if an AFP record is corrupt.
     */
    public void parse() throws IOException, AfpException {

        Record record;

        while ((record = this.reader.read()) != null) {
            final byte[] data = record.getData();
            if (data.length > 0) {
                if (data[0] != 0x5A) {
                    this.handler.handleLineRecord(record);
                } else {
                    final StructuredFieldIntroducer sfi = new StructuredFieldIntroducer(record);
                    if (this.handler.handleStructuredFieldIntroducer(sfi)) {
                        // TODO: Parse and create a StructredField (note the padding bytes and/or sfi-extension!
                        // TDDO: invoke this.handler.handleStructuredField
                        // this.handler.handleStructuredField(sf);
                    }
                }
            }
        }
    }
}
