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

public final class TripletParserTest extends TripletTest {

    /**
     * An empty byte stream should just return an empty List of Triplets.
     */
    public void testEmpty() throws Exception {
        assertTrue(this.parse("").isEmpty());
    }

    /**
     * Parsing a single Triplet.
     */
    public void testSingleTriplet() throws Exception {
        assertEquals("CommentTriplet", this.parse("036540").get(0).getClass().getSimpleName());
    }

    /**
     * Parsing multiple Triplets.
     */
    public void testMultipleTriplets() throws Exception {
        final List<Triplet> result = this.parse("036540" + "034325" + "0325FF");

        final CommentTriplet t1 = (CommentTriplet) result.get(0);
        assertEquals(0x40, t1.getComment()[0]);

        final DescriptorPositionTriplet t2 = (DescriptorPositionTriplet) result.get(1);
        assertEquals(0x25, t2.getDescriptorPositionId());

        final ResourceSectionNumberTriplet t3 = (ResourceSectionNumberTriplet) result.get(2);
        assertEquals(0xFF, t3.getResourceSectionNumber());
    }

    public void testTooShortTriplet() throws Exception {
        this.parseAndExpectFailure(
                "05654040",
                "Tried to parse 5 bytes at offset 0 but there are only 4 bytes left to parse.");

        this.parseAndExpectFailure(
                "036540" + "05654040",
                "Tried to parse 5 bytes at offset 3 but there are only 4 bytes left to parse.");
    }

    public void testZeroLengthTriplet() throws Exception {
        this.parseAndExpectFailure(
                "00654040",
                "A length value 0 was read for a triplet.");

        this.parseAndExpectFailure(
                "036540" + "00654040",
                "A length value 0 was read for a triplet.");
    }

    public void testTruncatedTriplet() throws Exception {
        this.parseAndExpectFailure(
                "03654040" + "0565404040",
                "Tried to parse 64 bytes at offset 3 but there are only 6 bytes left to parse.");
    }
}
