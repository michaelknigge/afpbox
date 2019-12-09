package de.textmode.afpbox.common;

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

/**
 * The class {@link PtocaControlSequenceFunctionType} contains just one static integer for every
 * known PTOCA control sequence function type.
 */
public final class PtocaControlSequenceFunctionType {

    /**
     * Absolute Move Baseline.
     */
    public static final int AMB = 0xD2;

    /**
     * Absolute Move Inline.
     */
    public static final int AMI = 0xC6;

    /**
     * Begin Line.
     */
    public static final int BLN = 0xD8;

    /**
     * Begin Suppression.
     */
    public static final int BSU = 0xF2;

    /**
     * Draw B-axis Rule.
     */
    public static final int DBR = 0xE6;

    /**
     * Draw I-axis Rule.
     */
    public static final int DIR = 0xE4;

    /**
     * End Suppression.
     */
    public static final int ESU = 0xF4;

    /**
     * Glyph Advance Run.
     * <p>
     * <b>Note: Each GAR control sequence must be chained to a preceding GIR control sequence.
     * It can be followed by a chained GOR, GIR, or UCT control sequence. If it is followed
     * by a different control sequence, the GAR terminates the GLC chain.</b>
     */
    public static final int GAR = 0x8C;

    /**
     * Glyph ID Run (only valid as an chained control sequence).
     * <p>
     * <b>Note: A GIR control sequence must be chained to a GLC, if in the first grouping, or
     * to a preceding GAR or GOR, if in subsequent groupings. It must be followed by a chained
     * GAR that contains a glyph advance for each glyph ID in this control sequence.</b>
     */
    public static final int GIR = 0x8B;

    /**
     * Glyph Layout Control (only valid as an chained control sequence).
     * <p>
     * <b>Note: The GLC control sequence must be followed by one or more GIR/GAR[/GOR] groupings.</b>
     */
    public static final int GLC = 0x6D;

    /**
     * Glyph Offset Run.
     */
    public static final int GOS = 0xF4;

    /**
     * No Operation.
     */
    public static final int NOP = 0xF8;

    /**
     * Overstrike.
     */
    public static final int OVS = 0x72;

    /**
     * Relative Move Baseline.
     */
    public static final int RMB = 0xD4;

    /**
     * Relative Move Inline.
     */
    public static final int RMI = 0xC8;

    /**
     * Repeat String.
     */
    public static final int RPS = 0xEE;

    /**
     * Set Baseline Increment.
     */
    public static final int SBI = 0xD0;

    /**
     * Set Coded Font Local.
     */
    public static final int SCFL = 0xF0;

    /**
     * Set Extended Text Color.
     */
    public static final int SEC = 0x80;

    /**
     * Set Intercharacter Adjustment.
     */
    public static final int SIA = 0xC2;

    /**
     * Set Inline Margin.
     */
    public static final int SIM = 0xC0;

    /**
     * Set Text Color.
     */
    public static final int STC = 0x74;

    /**
     * Set Text Orientation.
     */
    public static final int STO = 0xF6;

    /**
     * Set Variable Space Character Increment.
     */
    public static final int SVI = 0xC4;

    /**
     * Temporary Baseline Move.
     */
    public static final int TBM = 0x78;

    /**
     * Transparent Data.
     */
    public static final int TRN = 0xDA;

    /**
     * Unicode Complex Text (only valid as an unchained control sequence).
     * <p>
     * <b>Note: The UCT control sequence always terminates chaining!</b>
     */
    public static final int UCT = 0x6A;

    /**
     * Underscore.
     */
    public static final int USC = 0x76;
}
