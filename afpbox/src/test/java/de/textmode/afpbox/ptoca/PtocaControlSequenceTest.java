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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import de.textmode.afpbox.AfpException;
import de.textmode.afpbox.PtocaControlSequenceHandler;
import de.textmode.afpbox.PtocaParser;
import junit.framework.TestCase;

/**
 * Super-Class for all PtocaControlSequence unit tests.
 */
abstract class PtocaControlSequenceTest<T extends PtocaControlSequence> extends TestCase {

    /**
     * Parses the given control sequence and expects an error.
     */
    protected void parseAndExpectFailure(
            final String hexString,
            final String expectedMessage) throws IOException, AfpException, DecoderException {

        try {
            this.parse(hexString);
            fail("this unit test should fail!");
        } catch (final AfpException ex) {
            assertEquals(expectedMessage, ex.getMessage());
        }
    }

    /**
     * Parses the given control sequence and returns the parsed {@link PtocaControlSequence}.
     */
    protected T parse(final String hexString)
            throws IOException, AfpException, DecoderException {

        final List<T> result = new ArrayList<T>();
        PtocaParser.parse(Hex.decodeHex(hexString.toCharArray()), new PtocaControlSequenceHandler() {

            @SuppressWarnings("unchecked")
            @Override
            public void handleControSequence(final PtocaControlSequence controlSequence) {
                result.add((T) controlSequence);
            }

            @Override
            public boolean handleControSequence(final int functionType, final byte[] data, final int off) {
                return true;
            }

            @Override
            public void handleCodePoints(
                    final byte[] codePoints,
                    final int off,
                    final int len) throws IOException {
                throw new IOException("Faulty test case!");
            }
        });

        return result.get(0);
    }
}
