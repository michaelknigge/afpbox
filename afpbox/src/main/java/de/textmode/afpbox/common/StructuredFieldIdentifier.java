package de.textmode.afpbox.common;

/*
 * Copyright 2018 Michael Knigge
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
 * The class {@link StructuredFieldIdentifier} contains just one static integer for every
 * known Structured Field Identifier.
 */
public final class StructuredFieldIdentifier {

    /**
     *  Begin Active Environment Group.
     */
    public static final int BAG = 0xD3A8C9;

    /**
     *  Begin Bar Code Object.
     */
    public static final int BBC = 0xD3A8EB;

    /**
     *  Begin Color Attribute Table.
     */
    public static final int BCA = 0xD3A877;

    /**
     *  Begin Coded Font.
     */
    public static final int BCF = 0xD3A88A;

    /**
     *  Begin Code Page.
     */
    public static final int BCP = 0xD3A887;

    /**
     *  Bar Code Data.
     */
    public static final int BDA = 0xD3EEEB;

    /**
     *  Bar Code Data Descriptor.
     */
    public static final int BDD = 0xD3A6EB;

    /**
     *  Begin Document Environment Group.
     */
    public static final int BDG = 0xD3A8C4;

    /**
     *  Begin Document Index.
     */
    public static final int BDI = 0xD3A8A7;

    /**
     *  Begin Data Map.
     */
    public static final int BDM = 0xD3A8CA;

    /**
     *  Begin Document.
     */
    public static final int BDT = 0xD3A8A8;

    /**
     *  Begin Data Map Transmission Subcase.
     */
    public static final int BDX = 0xD3A8E3;

    /**
     *  Begin Form Environment Group.
     */
    public static final int BFG = 0xD3A8C5;

    /**
     *  Begin Form Map.
     */
    public static final int BFM = 0xD3A8CD;

    /**
     *  Begin Font.
     */
    public static final int BFN = 0xD3A889;

    /**
     *  Begin Graphics Object.
     */
    public static final int BGR = 0xD3A8BB;

    /**
     *  Begin IM Image Object.
     */
    public static final int BII = 0xD3A87B;

    /**
     *  Begin Image Object IO.
     */
    public static final int BIMO = 0xD3A8FB;

    /**
     *  Begin Medium Map.
     */
    public static final int BMM = 0xD3A8CC;

    /**
     *  Begin Medium Overlay.
     */
    public static final int BMO = 0xD3A8DF;

    /**
     *  Begin Named Page Group.
     */
    public static final int BNG = 0xD3A8AD;

    /**
     *  Begin Object Container.
     */
    public static final int BOC = 0xD3A892;

    /**
     *  Begin Object Environment Group.
     */
    public static final int BOG = 0xD3A8C7;

    /**
     *  Begin Print File.
     */
    public static final int BPF = 0xD3A8A5;

    /**
     *  Begin Page.
     */
    public static final int BPG = 0xD3A8AF;

    /**
     *  Begin Page Map.
     */
    public static final int BPM = 0xD3A8CB;

    /**
     *  Begin Page Segment.
     */
    public static final int BPS = 0xD3A85F;

    /**
     *  Begin Presentation Text Block.
     */
    public static final int BPT = 0xD3A89B;

    /**
     *  Begin Resource.
     */
    public static final int BRS = 0xD3A8CE;

    /**
     *  Begin Resource Group.
     */
    public static final int BRG = 0xD3A8C6;

    /**
     *  Begin Resource Environment Group.
     */
    public static final int BSG = 0xD3A8D9;

    /**
     *  Color Attribute Table.
     */
    public static final int CAT = 0xD3B077;

    /**
     *  Conditional Processing Control.
     */
    public static final int CCP = 0xD3A7CA;

    /**
     *  Container Data Descriptor.
     */
    public static final int CDD = 0xD3A692;

    /**
     *  Composed Text Control.
     */
    public static final int CTC = 0xD3A79B;

    /**
     *  Coded Font Control.
     */
    public static final int CFC = 0xD3A78A;

    /**
     *  Coded Font Index.
     */
    public static final int CFI = 0xD38C8A;

    /**
     *  Code Page Control.
     */
    public static final int CPC = 0xD3A787;

    /**
     *  Code Page Descriptor.
     */
    public static final int CPD = 0xD3A687;

    /**
     *  Code Page Index.
     */
    public static final int CPI = 0xD38C87;

    /**
     *  Data Map Transmission Subcase Descriptor.
     */
    public static final int DXD = 0xD3A6E3;

    /**
     *  End Active Environment Group.
     */
    public static final int EAG = 0xD3A9C9;

    /**
     *  End Bar Code Object.
     */
    public static final int EBC = 0xD3A9EB;

    /**
     *  End Color Attribute Table.
     */
    public static final int ECA = 0xD3A977;

    /**
     *  End Coded Font.
     */
    public static final int ECF = 0xD3A98A;

    /**
     *  End Code Page.
     */
    public static final int ECP = 0xD3A987;

    /**
     *  End Document Environment Group.
     */
    public static final int EDG = 0xD3A9C4;

    /**
     *  End Document Index.
     */
    public static final int EDI = 0xD3A9A7;

    /**
     *  End Data Map.
     */
    public static final int EDM = 0xD3A9CA;

    /**
     *  End Document.
     */
    public static final int EDT = 0xD3A9A8;

    /**
     *  End Data Map Transmission Subcase.
     */
    public static final int EDX = 0xD3A9E3;

    /**
     *  End Form Environment Group.
     */
    public static final int EFG = 0xD3A9C5;

    /**
     *  End Form Map.
     */
    public static final int EFM = 0xD3A9CD;

    /**
     *  End Graphics Object.
     */
    public static final int EGR = 0xD3A9BB;

    /**
     *  End IM Image Object.
     */
    public static final int EII = 0xD3A97B;

    /**
     *  End Image Object IO.
     */
    public static final int EIMO = 0xD3A9FB;

    /**
     *  End Medium Map.
     */
    public static final int EMM = 0xD3A9CC;

    /**
     *  End Medium Overlay.
     */
    public static final int EMO = 0xD3A9DF;

    /**
     *  End Named Page Group.
     */
    public static final int ENG = 0xD3A9AD;

    /**
     *  End Object Container.
     */
    public static final int EOC = 0xD3A992;

    /**
     *  End Object Environment Group.
     */
    public static final int EOG = 0xD3A9C7;

    /**
     *  End Print File.
     */
    public static final int EPF = 0xD3A9A5;

    /**
     *  End Page.
     */
    public static final int EPG = 0xD3A9AF;

    /**
     *  End Page Map.
     */
    public static final int EPM = 0xD3A9CB;

    /**
     *  End Page Segment.
     */
    public static final int EPS = 0xD3A95F;

    /**
     *  End Presentation Text Object.
     */
    public static final int EPT = 0xD3A99B;

    /**
     *  End Resource.
     */
    public static final int ERS = 0xD3A9CE;

    /**
     *  End Resource Group.
     */
    public static final int ERG = 0xD3A9C6;

    /**
     *  End Resource Environment Group.
     */
    public static final int ESG = 0xD3A9D9;

    /**
     *  End Font.
     */
    public static final int EFN = 0xD3A989;

    /**
     *  Font Control.
     */
    public static final int FNC = 0xD3A789;

    /**
     *  Font Descriptor.
     */
    public static final int FND = 0xD3A689;

    /**
     *  Font Patterns.
     */
    public static final int FNG = 0xD3EE89;

    /**
     *  Font Index.
     */
    public static final int FNI = 0xD38C89;

    /**
     *  Font Patterns Map.
     */
    public static final int FNM = 0xD3A289;

    /**
     *  Font Orientation.
     */
    public static final int FNO = 0xD3AE89;

    /**
     *  Font Position.
     */
    public static final int FNP = 0xD3AC89;

    /**
     *  Fixed Data Size.
     */
    public static final int FDS = 0xD3AAEC;

    /**
     *  Fixed Data Text.
     */
    public static final int FDX = 0xD3EEEC;

    /**
     *  Form Environment Group Descriptor.
     */
    public static final int FGD = 0xD3A6C5;

    /**
     *  Form Name Map.
     */
    public static final int FNN = 0xD3AB89;

    /**
     *  Graphics Data.
     */
    public static final int GAD = 0xD3EEBB;

    /**
     *  Graphics Data Descriptor.
     */
    public static final int GDD = 0xD3A6BB;

    /**
     *  Index Element.
     */
    public static final int IEL = 0xD3B2A7;

    /**
     *  Image Cell Position.
     */
    public static final int ICP = 0xD3AC7B;

    /**
     *  Image Data Descriptor IO.
     */
    public static final int IDD = 0xD3A6FB;

    /**
     *  Invoke Data Map.
     */
    public static final int IDM = 0xD3ABCA;

    /**
     *  Image Input Descriptor IM.
     */
    public static final int IID = 0xD3A67B;

    /**
     *  Invoke Medium Map.
     */
    public static final int IMM = 0xD3ABCC;

    /**
     *  Include Object.
     */
    public static final int IOB = 0xD3AFC3;

    /**
     *  Image Output Control IM.
     */
    public static final int IOC = 0xD3A77B;

    /**
     *  Image Picture Data IO.
     */
    public static final int IPD = 0xD3EEFB;

    /**
     *  Image Page.
     */
    public static final int IPG = 0xD3AFAF;

    /**
     *  Include Page Overlay.
     */
    public static final int IPO = 0xD3AFD8;

    /**
     *  Include Page Segment.
     */
    public static final int IPS = 0xD3AF5F;

    /**
     *  Image Raster Data IM.
     */
    public static final int IRD = 0xD3EE7B;

    /**
     *  Link Logical Element.
     */
    public static final int LLE = 0xD3B490;

    /**
     *  Line Descriptor Count.
     */
    public static final int LNC = 0xD3AAE7;

    /**
     *  Line Descriptor.
     */
    public static final int LND = 0xD3A6E7;

    /**
     *  Map Bar Code.
     */
    public static final int MBC = 0xD3ABEB;

    /**
     *  Map Color Attribute Table.
     */
    public static final int MCA = 0xD3AB77;

    /**
     *  Medium Copy Count.
     */
    public static final int MCC = 0xD3A288;

    /**
     *  Map Container Data.
     */
    public static final int MCD = 0xD3AB92;

    /**
     *  Map Coded Font (Format 1).
     */
    public static final int MCF1 = 0xD3B18A;

    /**
     *  Map Coded Font (Format 2).
     */
    public static final int MCF2 = 0xD3AB8A;

    /**
     *  Medium Descriptor.
     */
    public static final int MDD = 0xD3A688;

    /**
     *  Map Data Resource.
     */
    public static final int MDR = 0xD3ABC3;

    /**
     *  Medium Finishing Control.
     */
    public static final int MFC = 0xD3A088;

    /**
     *  Map Graphic Object.
     */
    public static final int MGO = 0xD3ABBB;

    /**
     *  Map IO Image Object.
     */
    public static final int MIO = 0xD3ABFB;

    /**
     *  Medium Modification Control.
     */
    public static final int MMC = 0xD3A788;

    /**
     *  Map Media Destination.
     */
    public static final int MMD = 0xD3ABCD;

    /**
     *  Map Medium Overlay.
     */
    public static final int MMO = 0xD3B1DF;

    /**
     *  Map Media Type.
     */
    public static final int MMT = 0xD3AB88;

    /**
     *  Map Page.
     */
    public static final int MPG = 0xD3ABAF;

    /**
     *  Map Page Overlay.
     */
    public static final int MPO = 0xD3ABD8;

    /**
     *  Map Page Segment.
     */
    public static final int MPS = 0xD3B15F;

    /**
     *  Map Presentation Text.
     */
    public static final int MPT = 0xD3AB9B;

    /**
     *  Map Suppression.
     */
    public static final int MSU = 0xD3ABEA;

    /**
     *  No Operation.
     */
    public static final int NOP = 0xD3EEEE;

    /**
     *  Object Area Descriptor.
     */
    public static final int OBD = 0xD3A66B;

    /**
     *  Object Area Position.
     */
    public static final int OBP = 0xD3AC6B;

    /**
     *  Object Container Data.
     */
    public static final int OCD = 0xD3EE92;

    /**
     *  Preprocess Environment Control.
     */
    public static final int PEC = 0xD3A7A8;

    /**
     *  Presentation Fidelity Control.
     */
    public static final int PFC = 0xD3B288;

    /**
     *  Page Descriptor.
     */
    public static final int PGD = 0xD3A6AF;

    /**
     *  Page Position.
     */
    public static final int PGP1 = 0xD3ACAF;

    /**
     *  Page Position (Format 2).
     */
    public static final int PGP2 = 0xD3B1AF;

    /**
     *  Page Modification Control.
     */
    public static final int PMC = 0xD3A7AF;

    /**
     *  Preprocess Presentation Object.
     */
    public static final int PPO = 0xD3ADC3;

    /**
     *  Presentation Text Descriptor (Format 1).
     */
    public static final int PTD1 = 0xD3A69B;

    /**
     *  Presentation Text Descriptor (Format 2).
     */
    public static final int PTD2 = 0xD3B19B;

    /**
     *  Presentation Text Data.
     */
    public static final int PTX = 0xD3EE9B;

    /**
     *  Record Descriptior.
     */
    public static final int RCD = 0xD3A68D;

    /**
     *  Tag Logical Element.
     */
    public static final int TLE = 0xD3A090;

    /**
     *  XML Descriptor.
     */
    public static final int XMD = 0xD3A78E;


    private StructuredFieldIdentifier() {
    }
}
