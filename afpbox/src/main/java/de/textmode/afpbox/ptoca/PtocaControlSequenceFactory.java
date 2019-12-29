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

            //        case PtocaControlSequenceFunctionType.BSU:
            //        case PtocaControlSequenceFunctionType.DBR:
            //        case PtocaControlSequenceFunctionType.DIR:
            //        case PtocaControlSequenceFunctionType.ESU:
            //        case PtocaControlSequenceFunctionType.NOP:
            //        case PtocaControlSequenceFunctionType.OVS:

        case PtocaControlSequenceFunctionType.RMB_CHAINED:
        case PtocaControlSequenceFunctionType.RMB_UNCHAINED:
            return new RelativeMoveBaseline(functionType, data);

        case PtocaControlSequenceFunctionType.RMI_CHAINED:
        case PtocaControlSequenceFunctionType.RMI_UNCHAINED:
            return new RelativeMoveInline(functionType, data);

            //        case PtocaControlSequenceFunctionType.RPS:
            //        case PtocaControlSequenceFunctionType.SBI:
            //        case PtocaControlSequenceFunctionType.SCF:
            //        case PtocaControlSequenceFunctionType.SEC:
            //        case PtocaControlSequenceFunctionType.SIA:
            //        case PtocaControlSequenceFunctionType.SIM:
            //        case PtocaControlSequenceFunctionType.STC:
            //        case PtocaControlSequenceFunctionType.STO:
            //        case PtocaControlSequenceFunctionType.SVI:
            //        case PtocaControlSequenceFunctionType.TBM:
            //        case PtocaControlSequenceFunctionType.TRN:
            //        case PtocaControlSequenceFunctionType.USC:

        default:
            return new UnknownControlSequence(functionType, data);
        }
    }

}
