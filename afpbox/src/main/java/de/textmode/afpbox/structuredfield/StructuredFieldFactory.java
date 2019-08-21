package de.textmode.afpbox.structuredfield;

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
/*
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
import de.textmode.afpbox.common.StructuredFieldIdentifier;
import de.textmode.afpbox.io.AfpDataInputStream;

/**
 * The {@link StructuredFieldFactory} creates the concrete {@link StructuredField} objects
 * for the read Structured Fields.
 */
public final class StructuredFieldFactory {

    /**
     * Creates a concrete {@link StructuredField} from read data. The data is parsed using the
     * provided {@link AfpDataInputStream}.
     *
     * @param sfid the numeric value of the Structured Field Identifier
     * @param is the stream the data is read from
     *
     * @return the built {@link StructuredField}.
     *
     * @throws AfpException if an error occurs while parsing the {@link StructuredField}.
     */
    public static StructuredField createFor(final int sfid, final AfpDataInputStream is) throws AfpException {

        // Yes.... this code looks ugly and could be done nicer using a HashMap. But remember that
        // Java does not support HashMaps for native data types, so we would have to use a HashMap
        // with an Integer as Key. But then for every lookup an Integer would have to be created
        // from an int... overkill... So we just use a switch-case which is pretty fast at runtime...

        switch (sfid) {
        //        case StructuredFieldIdentifier.BAG:
        //        case StructuredFieldIdentifier.BBC:
        //        case StructuredFieldIdentifier.BCA:
        //        case StructuredFieldIdentifier.BCF:
        //        case StructuredFieldIdentifier.BCP:
        //        case StructuredFieldIdentifier.BDA:
        //        case StructuredFieldIdentifier.BDD:
        //        case StructuredFieldIdentifier.BDG:
        //        case StructuredFieldIdentifier.BDI:
        //        case StructuredFieldIdentifier.BDM:
        //        case StructuredFieldIdentifier.BDT:
        //        case StructuredFieldIdentifier.BDX:
        //        case StructuredFieldIdentifier.BFG:
        //        case StructuredFieldIdentifier.BFM:
        //        case StructuredFieldIdentifier.BFN:
        //        case StructuredFieldIdentifier.BGR:
        //        case StructuredFieldIdentifier.BIMM:
        //        case StructuredFieldIdentifier.BIMO:
        //        case StructuredFieldIdentifier.BMM:
        //        case StructuredFieldIdentifier.BMO:
        //        case StructuredFieldIdentifier.BNG:
        //        case StructuredFieldIdentifier.BOC:
        //        case StructuredFieldIdentifier.BOG:
        //        case StructuredFieldIdentifier.BPF:
        //        case StructuredFieldIdentifier.BPG:
        //        case StructuredFieldIdentifier.BPM:
        //        case StructuredFieldIdentifier.BPS:
        //        case StructuredFieldIdentifier.BPT:
        //        case StructuredFieldIdentifier.BRS:
        //        case StructuredFieldIdentifier.BRG:
        //        case StructuredFieldIdentifier.BSG:
        //        case StructuredFieldIdentifier.CAT:
        //        case StructuredFieldIdentifier.CCP:
        //        case StructuredFieldIdentifier.CDD:
        //        case StructuredFieldIdentifier.CTC:
        //        case StructuredFieldIdentifier.CFC:
        //        case StructuredFieldIdentifier.CFI:
        //        case StructuredFieldIdentifier.CPC:
        //        case StructuredFieldIdentifier.CPD:
        //        case StructuredFieldIdentifier.CPI:
        //        case StructuredFieldIdentifier.DXD:
        //        case StructuredFieldIdentifier.EAG:
        //        case StructuredFieldIdentifier.EBC:
        //        case StructuredFieldIdentifier.ECA:
        //        case StructuredFieldIdentifier.ECF:
        //        case StructuredFieldIdentifier.ECP:
        //        case StructuredFieldIdentifier.EDG:
        //        case StructuredFieldIdentifier.EDI:
        //        case StructuredFieldIdentifier.EDM:
        //        case StructuredFieldIdentifier.EDT:
        //        case StructuredFieldIdentifier.EDX:
        //        case StructuredFieldIdentifier.EFG:
        //        case StructuredFieldIdentifier.EFM:
        //        case StructuredFieldIdentifier.EGR:
        //        case StructuredFieldIdentifier.EIMM:
        //        case StructuredFieldIdentifier.EIMO:
        //        case StructuredFieldIdentifier.EMM:
        //        case StructuredFieldIdentifier.EMO:
        //        case StructuredFieldIdentifier.ENG:
        //        case StructuredFieldIdentifier.EOC:
        //        case StructuredFieldIdentifier.EOG:
        //        case StructuredFieldIdentifier.EPF:
        //        case StructuredFieldIdentifier.EPG:
        //        case StructuredFieldIdentifier.EPM:
        //        case StructuredFieldIdentifier.EPS:
        //        case StructuredFieldIdentifier.EPT:
        //        case StructuredFieldIdentifier.ERS:
        //        case StructuredFieldIdentifier.ERG:
        //        case StructuredFieldIdentifier.ESG:
        //        case StructuredFieldIdentifier.EFN:
        //        case StructuredFieldIdentifier.FNC:
        //        case StructuredFieldIdentifier.FND:
        //        case StructuredFieldIdentifier.FNG:
        //        case StructuredFieldIdentifier.FNI:
        //        case StructuredFieldIdentifier.FNM:
        //        case StructuredFieldIdentifier.FNO:
        //        case StructuredFieldIdentifier.FNP:
        //        case StructuredFieldIdentifier.FDS:
        //        case StructuredFieldIdentifier.FDX:
        //        case StructuredFieldIdentifier.FGD:
        //        case StructuredFieldIdentifier.FNN:
        //        case StructuredFieldIdentifier.GAD:
        //        case StructuredFieldIdentifier.GDD:
        //        case StructuredFieldIdentifier.IEL:
        //        case StructuredFieldIdentifier.IOP:
        //        case StructuredFieldIdentifier.IDD:
        //        case StructuredFieldIdentifier.IDM:
        //        case StructuredFieldIdentifier.IID:
        //        case StructuredFieldIdentifier.IMM:
        //        case StructuredFieldIdentifier.IOB:
        //        case StructuredFieldIdentifier.IOC:
        //        case StructuredFieldIdentifier.IPD:
        //        case StructuredFieldIdentifier.IPG:
        //        case StructuredFieldIdentifier.IPO:
        //        case StructuredFieldIdentifier.IPS:
        //        case StructuredFieldIdentifier.IRD:
        //        case StructuredFieldIdentifier.LLE:
        //        case StructuredFieldIdentifier.LNC:
        //        case StructuredFieldIdentifier.LND:
        //        case StructuredFieldIdentifier.MBC:
        //        case StructuredFieldIdentifier.MCA:
        //        case StructuredFieldIdentifier.MCC:
        //        case StructuredFieldIdentifier.MCD:
        //        case StructuredFieldIdentifier.MCF1:
        //        case StructuredFieldIdentifier.MCF2:
        //        case StructuredFieldIdentifier.MDD:
        //        case StructuredFieldIdentifier.MDR:
        //        case StructuredFieldIdentifier.MFC:
        //        case StructuredFieldIdentifier.MGO:
        //        case StructuredFieldIdentifier.MIO:
        //        case StructuredFieldIdentifier.MMC:
        //        case StructuredFieldIdentifier.MMD:
        //        case StructuredFieldIdentifier.MMO:
        //        case StructuredFieldIdentifier.MMT:
        //        case StructuredFieldIdentifier.MPG:
        //        case StructuredFieldIdentifier.MPO:
        //        case StructuredFieldIdentifier.MPS:
        //        case StructuredFieldIdentifier.MSU:
        case StructuredFieldIdentifier.NOP:
            return new NoOperation(is);
            //        case StructuredFieldIdentifier.OBD:
            //        case StructuredFieldIdentifier.OBP:
            //        case StructuredFieldIdentifier.OCD:
            //        case StructuredFieldIdentifier.PEC:
            //        case StructuredFieldIdentifier.PFC:
            //        case StructuredFieldIdentifier.PGD:
            //        case StructuredFieldIdentifier.PGP1:
            //        case StructuredFieldIdentifier.PGP2:
            //        case StructuredFieldIdentifier.PMC:
            //        case StructuredFieldIdentifier.PPO:
            //        case StructuredFieldIdentifier.PTD1:
            //        case StructuredFieldIdentifier.PTD2:
            //        case StructuredFieldIdentifier.PTX:
            //        case StructuredFieldIdentifier.RCD:
            //        case StructuredFieldIdentifier.TLE:
            //        case StructuredFieldIdentifier.XMD:

        default:
            return new UnknownStructuredField(is);
        }
    }
}
