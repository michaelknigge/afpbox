package de.textmode.afpbox.triplet;

import java.util.ArrayList;
import java.util.List;

import de.textmode.afpbox.AfpException;
import de.textmode.afpbox.io.AfpDataInputStream;

/**
 * The {@link TripletParser} reads {@link Triplet Triplet(s)} from a byte array.
 */
public final class TripletParser {

    private TripletParser() {
    }

    /**
     * Parses the given {@link Triplet Triplet(s)}.
     *
     * @param data     the Triplets
     *
     * @throws AfpException if a Triplet is corrupt / truncated.
     */
    public static List<Triplet> parse(final byte[] data) throws AfpException {

        final List<Triplet> triplets = new ArrayList<>();
        final AfpDataInputStream reader = new AfpDataInputStream(data);

        while (reader.bytesAvailable() > 0) {
            // TODO: We should not throw an exception. We should create a
            // "FaultyTriplet" with the rest of the remaining data. So the caller
            // can at least get the previous triplets from the returned list...
            final int length = reader.readUnsignedByte();
            if (length < 2) {
                throw new AfpException("A length value " + length + " was read for a triplet.");
            }

            final int tripletId = reader.readUnsignedByte();
            final byte[] triplet = reader.readBytes(reader.tell() - 2, length);

            // TODO: If a exception is thrown, create an FaultyTriplet - same as in PtocaParser
            triplets.add(TripletFactory.createFor(tripletId, triplet));
        }

        return triplets;
    }
}
