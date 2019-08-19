package de.textmode.afpbox.structuredfield;

/**
 * {@link StructuredFieldBase} is a {@link StructuredField} that contains only opaque data.
 */
public abstract class StructuredFieldBase implements StructuredField {

    private final byte[] data;

    /**
     * The constructor just stores the raw data of the Structured Field.
     *
     * @param data the raw data of the Structured Field
     */
    public StructuredFieldBase(final byte[] data) {
        this.data = data;
    }

    /**
     * Returns the data of the Structured Field (all bytes after the Structured Field Introducer).
     *
     * @return the data of the Structured Field
     */
    public final byte[] getData() {
        return this.data;
    }
}
