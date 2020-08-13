package de.textmode.afpbox.triplet;

/*
 * Copyright 2020 Michael Knigge
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

import java.util.List;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import de.textmode.afpbox.AfpException;
import junit.framework.TestCase;

/**
 * Super-Class for all {@link Triplet} unit tests.
 */
abstract class TripletTest<T extends Triplet> extends TestCase {

    /**
     * Parses the given Triplet and expects an error.
     */
    protected void parseAndExpectFailure(
            final String hexString,
            final String expectedMessage) throws DecoderException {

        try {
            this.parse(hexString);
            fail("this unit test should fail!");
        } catch (final AfpException ex) {
            assertEquals(expectedMessage, ex.getMessage());
        }
    }

    /**
     * Parses the given triplets and returns the parsed {@link Triplet(s)}.
     */
    protected List<Triplet> parse(final String hexString)
            throws AfpException, DecoderException {

        return TripletParser.parse(Hex.decodeHex(hexString.toCharArray()));
    }

    /**
     * Parses the given triplet and returns the first parsed {@link Triplet}.
     */
    protected T parseSingle(final String hexString) throws AfpException, DecoderException {
        return (T) TripletParser.parse(Hex.decodeHex(hexString.toCharArray())).get(0);
    }
}
