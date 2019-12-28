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
     * Absolute Move Baseline (unchained).
     */
    public static final int AMB_UNCHAINED = 0xD2;

    /**
     * Absolute Move Baseline (chained).
     */
    public static final int AMB_CHAINED = 0xD3;

    /**
     * Absolute Move Inline (unchained).
     */
    public static final int AMI_UNCHAINED = 0xC6;

    /**
     * Absolute Move Inline (chained).
     */
    public static final int AMI_CHAINED = 0xC7;

    /**
     * Begin Line (unchained).
     */
    public static final int BLN_UNCHAINED = 0xD8;

    /**
     * Begin Line (chained).
     */
    public static final int BLN_CHAINED = 0xD9;

    /**
     * Begin Suppression (unchained).
     */
    public static final int BSU_UNCHAINED = 0xF2;

    /**
     * Begin Suppression (chained).
     */
    public static final int BSU_CHAINED = 0xF3;

    /**
     * Draw B-axis Rule (unchained).
     */
    public static final int DBR_UNCHAINED = 0xE6;

    /**
     * Draw B-axis Rule (chained).
     */
    public static final int DBR_CHAINED = 0xE7;

    /**
     * Draw I-axis Rule (unchained).
     */
    public static final int DIR_UNCHAINED = 0xE4;

    /**
     * Draw I-axis Rule (chained).
     */
    public static final int DIR_CHAINED = 0xE5;

    /**
     * End Suppression (unchained).
     */
    public static final int ESU_UNCHAINED = 0xF4;

    /**
     * End Suppression (chained).
     */
    public static final int ESU_CHAINED = 0xF5;

    /**
     * Glyph Advance Run (unchained).
     * <p><b>Note: Each GAR control sequence must be chained to a preceding GIR control sequence.
     * It can be followed by a chained GOR, GIR, or UCT control sequence. If it is followed
     * by a different control sequence, the GAR terminates the GLC chain.</b>
     */
    public static final int GAR_UNCHAINED = 0x8C;

    /**
     * Glyph Advance Run (chained).
     * <p><b>Note: Each GAR control sequence must be chained to a preceding GIR control sequence.
     * It can be followed by a chained GOR, GIR, or UCT control sequence. If it is followed
     * by a different control sequence, the GAR terminates the GLC chain.</b>
     */
    public static final int GAR_CHAINED = 0x8D;

    /**
     * Glyph ID Run (chained).
     * <p><b>Note: A GIR control sequence must be chained to a GLC, if in the first grouping, or
     * to a preceding GAR or GOR, if in subsequent groupings. It must be followed by a chained
     * GAR that contains a glyph advance for each glyph ID in this control sequence.</b>
     */
    public static final int GIR_CHAINED = 0x8B;

    /**
     * Glyph Layout Control (chained).
     * <p><b>Note: The GLC control sequence must be followed by one or more GIR/GAR[/GOR] groupings.</b>
     */
    public static final int GLC_CHAINED = 0x6D;

    /**
     * Glyph Offset Run (unchained).
     */
    public static final int GOR_UNCHAINED = 0x8E;

    /**
     * Glyph Offset Run (chained).
     */
    public static final int GOR_CHAINED = 0x8F;

    /**
     * No Operation (unchained).
     */
    public static final int NOP_UNCHAINED = 0xF8;

    /**
     * No Operation (chained).
     */
    public static final int NOP_CHAINED = 0xF9;

    /**
     * Overstrike (unchained).
     */
    public static final int OVS_UNCHAINED = 0x72;

    /**
     * Overstrike (chained).
     */
    public static final int OVS_CHAINED = 0x73;

    /**
     * Relative Move Baseline (unchained).
     */
    public static final int RMB_UNCHAINED = 0xD4;

    /**
     * Relative Move Baseline (chained).
     */
    public static final int RMB_CHAINED = 0xD5;

    /**
     * Relative Move Inline (unchained).
     */
    public static final int RMI_UNCHAINED = 0xC8;

    /**
     * Relative Move Inline (chained).
     */
    public static final int RMI_CHAINED = 0xC9;

    /**
     * Repeat String (unchained).
     */
    public static final int RPS_UNCHAINED = 0xEE;

    /**
     * Repeat String (chained).
     */
    public static final int RPS_CHAINED = 0xEF;

    /**
     * Set Baseline Increment (unchained).
     */
    public static final int SBI_UNCHAINED = 0xD0;

    /**
     * Set Baseline Increment (chained).
     */
    public static final int SBI_CHAINED = 0xD1;

    /**
     * Set Coded Font Local (unchained).
     */
    public static final int SCFL_UNCHAINED = 0xF0;

    /**
     * Set Coded Font Local (chained).
     */
    public static final int SCFL_CHAINED = 0xF1;

    /**
     * Set Extended Text Color (unchained).
     */
    public static final int SEC_UNCHAINED = 0x80;

    /**
     * Set Extended Text Color (chained).
     */
    public static final int SEC_CHAINED = 0x81;

    /**
     * Set Intercharacter Adjustment (unchained).
     */
    public static final int SIA_UNCHAINED = 0xC2;

    /**
     * Set Intercharacter Adjustment (chained).
     */
    public static final int SIA_CHAINED = 0xC3;

    /**
     * Set Inline Margin (unchained).
     */
    public static final int SIM_UNCHAINED = 0xC0;

    /**
     * Set Inline Margin (chained).
     */
    public static final int SIM_CHAINED = 0xC1;

    /**
     * Set Text Color (unchained).
     */
    public static final int STC_UNCHAINED = 0x74;

    /**
     * Set Text Color (chained).
     */
    public static final int STC_CHAINED = 0x75;

    /**
     * Set Text Orientation (unchained).
     */
    public static final int STO_UNCHAINED = 0xF6;

    /**
     * Set Text Orientation (chained).
     */
    public static final int STO_CHAINED = 0xF7;

    /**
     * Set Variable Space Character Increment (unchained).
     */
    public static final int SVI_UNCHAINED = 0xC4;

    /**
     * Set Variable Space Character Increment (chained).
     */
    public static final int SVI_CHAINED = 0xC5;

    /**
     * Temporary Baseline Move (unchained).
     */
    public static final int TBM_UNCHAINED = 0x78;

    /**
     * Temporary Baseline Move (chained).
     */
    public static final int TBM_CHAINED = 0x79;

    /**
     * Transparent Data (unchained).
     */
    public static final int TRN_UNCHAINED = 0xDA;

    /**
     * Transparent Data (chained).
     */
    public static final int TRN_CHAINED = 0xDB;

    /**
     * Unicode Complex Text (unchained).
     * <p><b>Note: The UCT control sequence always terminates chaining!</b>
     */
    public static final int UCT_UNCHAINED = 0x6A;

    /**
     * Underscore (unchained).
     */
    public static final int USC_UNCHAINED = 0x76;

    /**
     * Underscore (chained).
     */
    public static final int USC_CHAINED = 0x77;
}
