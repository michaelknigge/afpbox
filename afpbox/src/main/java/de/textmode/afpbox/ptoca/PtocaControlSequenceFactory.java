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

import de.textmode.afpbox.AfpException;
import de.textmode.afpbox.common.PtocaControlSequenceFunctionType;

/**
 * The {@link PtocaControlSequenceFactory} creates the concrete {@link PtocaControlSequence} objects
 * for the PTOCA control sequences.
 */
public final class PtocaControlSequenceFactory {
    /**
     * Creates a concrete {@link PtocaControlSequence} from the given byte[].
     *
     * @param functionType  the function type of the PTOCA control sequence
     * @param data          the raw data of the PTOCA control sequence
     *
     * @return the built {@link PtocaControlSequence}.
     *
     * @throws AfpException if an error occurs while parsing the {@link PtocaControlSequence}.
     */
    public static PtocaControlSequence createFor(
            final int functionType,
            final byte[] data) throws AfpException {

        // Yes.... this code looks ugly and could be done nicer using a HashMap. But remember that
        // Java does not support HashMaps for native data types, so we would have to use a HashMap
        // with an Integer as Key. But then for every lookup an Integer would have to be created
        // from an int... overkill... So we just use a switch-case which is pretty fast at runtime...

        switch (functionType) {
        case PtocaControlSequenceFunctionType.AMB_CHAINED:
        case PtocaControlSequenceFunctionType.AMB_UNCHAINED:
            return new AbsoluteMoveBaseline(functionType, data);

        case PtocaControlSequenceFunctionType.AMI_CHAINED:
        case PtocaControlSequenceFunctionType.AMI_UNCHAINED:
            return new AbsoluteMoveInline(functionType, data);

        case PtocaControlSequenceFunctionType.BLN_CHAINED:
        case PtocaControlSequenceFunctionType.BLN_UNCHAINED:
            return new BeginLine(functionType, data);

        case PtocaControlSequenceFunctionType.BSU_CHAINED:
        case PtocaControlSequenceFunctionType.BSU_UNCHAINED:
            return new BeginSuppression(functionType, data);

            //        case PtocaControlSequenceFunctionType.DBR:
            //        case PtocaControlSequenceFunctionType.DIR:

        case PtocaControlSequenceFunctionType.ESU_CHAINED:
        case PtocaControlSequenceFunctionType.ESU_UNCHAINED:
            return new EndSuppression(functionType, data);

        case PtocaControlSequenceFunctionType.NOP_CHAINED:
        case PtocaControlSequenceFunctionType.NOP_UNCHAINED:
            return new NoOperation(functionType, data);

        case PtocaControlSequenceFunctionType.OVS_CHAINED:
        case PtocaControlSequenceFunctionType.OVS_UNCHAINED:
            return new Overstrike(functionType, data);

        case PtocaControlSequenceFunctionType.RMB_CHAINED:
        case PtocaControlSequenceFunctionType.RMB_UNCHAINED:
            return new RelativeMoveBaseline(functionType, data);

        case PtocaControlSequenceFunctionType.RMI_CHAINED:
        case PtocaControlSequenceFunctionType.RMI_UNCHAINED:
            return new RelativeMoveInline(functionType, data);

        case PtocaControlSequenceFunctionType.RPS_CHAINED:
        case PtocaControlSequenceFunctionType.RPS_UNCHAINED:
            return new RepeatString(functionType, data);

        case PtocaControlSequenceFunctionType.SBI_CHAINED:
        case PtocaControlSequenceFunctionType.SBI_UNCHAINED:
            return new SetBaselineIncrement(functionType, data);

        case PtocaControlSequenceFunctionType.SCFL_CHAINED:
        case PtocaControlSequenceFunctionType.SCFL_UNCHAINED:
            return new SetCodedFontLocal(functionType, data);

            //        case PtocaControlSequenceFunctionType.SEC:

        case PtocaControlSequenceFunctionType.SIA_CHAINED:
        case PtocaControlSequenceFunctionType.SIA_UNCHAINED:
            return new SetIntercharacterAdjustment(functionType, data);

        case PtocaControlSequenceFunctionType.SIM_CHAINED:
        case PtocaControlSequenceFunctionType.SIM_UNCHAINED:
            return new SetInlineMargin(functionType, data);

            //        case PtocaControlSequenceFunctionType.STC:
            //        case PtocaControlSequenceFunctionType.STO:

        case PtocaControlSequenceFunctionType.SVI_CHAINED:
        case PtocaControlSequenceFunctionType.SVI_UNCHAINED:
            return new SetVariableSpaceCharacterIncrement(functionType, data);

        case PtocaControlSequenceFunctionType.TBM_CHAINED:
        case PtocaControlSequenceFunctionType.TBM_UNCHAINED:
            return new TemporaryBaselineMove(functionType, data);

        case PtocaControlSequenceFunctionType.TRN_CHAINED:
        case PtocaControlSequenceFunctionType.TRN_UNCHAINED:
            return new TransparentData(functionType, data);

        case PtocaControlSequenceFunctionType.USC_CHAINED:
        case PtocaControlSequenceFunctionType.USC_UNCHAINED:
            return new Underscore(functionType, data);

        default:
            return new UnknownControlSequence(functionType, data);
        }
    }
}
