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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import de.textmode.afpbox.AfpException;
import de.textmode.afpbox.AfpParser;
import de.textmode.afpbox.RecordHandler;
import de.textmode.afpbox.io.Record;
import de.textmode.afpbox.io.RecordReader;
import junit.framework.TestCase;

/**
 * Super-Class for all PtocaControlSequence unit tests.
 */
abstract class StructuredFieldTest<T extends StructuredFieldBase> extends TestCase {

    /**
     * Parses the given structured field and returns the parsed {@link StructuredFieldBase}.
     */
    protected T parse(final String hexString)
            throws IOException, AfpException, DecoderException {

        final List<T> result = new ArrayList<T>();
        final RecordHandler rh = new RecordHandler() {

            @Override
            public void handleLineRecord(final Record record) {
            }

            @Override
            public boolean handleStructuredFieldIntroducer(final StructuredFieldIntroducer sfi) {
                return true;
            }

            @SuppressWarnings("unchecked")
            @Override
            public void handleStructuredField(final StructuredField sf) {
                result.add((T) sf);
            }

            @SuppressWarnings("unchecked")
            @Override
            public void handleFaultyStructuredField(final FaultyStructuredField sf) {
                result.add((T) sf);
            }
        };

        final Stack<Record> recordStack = new Stack<>();
        recordStack.push(null);
        recordStack.push(new Record(Hex.decodeHex(hexString.toCharArray()), 0));

        final RecordReader rr = new RecordReader() {
            @Override
            public Record read() throws IOException {
                return recordStack.pop();
            }
        };

        new AfpParser(rr, rh).parse();

        return result.get(0);
    }
}
