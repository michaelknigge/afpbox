package de.textmode.afpbox.triplet;

/**
 * Unit-Tests for the class {@link UnknownTriplet}.
 */
public final class UnknownTripletTest  extends TripletTest<UnknownTriplet> {

    /**
     * Checks if an unknwon triplet is returned as an {@link UnknownTriplet}.
     */
    public void testHappyFlow() throws Exception {
        final UnknownTriplet t1 = this.parseSingle("030040");
        assertEquals(3, t1.getData().length);
        assertEquals(0x03, t1.getData()[0]);
        assertEquals(0x00, t1.getData()[1]);
        assertEquals(0x40, t1.getData()[2]);
    }
}
