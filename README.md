# afpbox [![Build Status](https://travis-ci.org/michaelknigge/afpbox.svg?branch=master)](https://travis-ci.org/michaelknigge/afpbox) [![codecov.io](https://codecov.io/github/michaelknigge/afpbox/coverage.svg?branch=master)](https://codecov.io/github/michaelknigge/afpbox?branch=master) [![Coverity Status](https://scan.coverity.com/projects/10253/badge.svg)](https://scan.coverity.com/projects/10253) [![Download](https://api.bintray.com/packages/michaelknigge/maven/afpbox/images/download.svg) ](https://bintray.com/michaelknigge/maven/afpbox/_latestVersion)

Java library for parsing [AFP](https://en.wikipedia.org/wiki/Advanced_Function_Presentation) ([MO:DCA](https://en.wikipedia.org/wiki/MODCA)) printer data streams.

**NOTE: This project is still work in progress...**

# Dependencies
afpbox has no runtime dependencies on other libraries. This was a design decision and will (hopefully) never change.

# Usage
Because afpbox is available at [jcenter](https://bintray.com/bintray/jcenter) it is very easy to use afpbox in your projects. At first, add afpbox to your build file. If you use Maven, add the following to your build file:

```xml
<dependency>
  <groupId>de.textmode.afpbox</groupId>
  <artifactId>afpbox</artifactId>
  <version>0.3</version>
  <type>pom</type>
</dependency>
```

If you use Gradle, add this:

```
dependencies {
    compile 'de.textmode.afpbox:afpbox:0.3'
}
```

## AFP-Parser

AFP (MODCA) is a record oriented data stream. For this reason you need to implement a `RecordReader` first. afpbox comes with two common implementations
of a `RecordReader`.

The `StandardRecordReader` is probably the `RecordReader` of your choice. It reads the special control character X'5A' of the
record and determines the record length from the following two bytes.

The `MvsRecordReader` expects that every AFP record is prefixed with four bytes. The record length is determined from the first two bytes. The following
two bytes are ignored. This record format corresponds to the record format VB on z/OS (formerly known as OS/390, which was formerly known as MVS).

Now when you have a `RecordReader` you further need a `RecordHandler`. The main idea behind the `RecordHandler` is that the application can control
which structured fields have to be parsed and which not. You have to implement a `RecordHandler` according to your needs.

When you have a `RecordReader` and a `RecordHandler` you are ready to create a `AfpParser`. Let's build a sample application that will
count the pages of an AFP file so you'll get the idea behind the design of afpbox:

```java
int pageCounter = 0;
final InputStream is = new FileInputStream("myfile.afp");
final RecordHandler rh = new RecordHandler() {

    @Override
    public void handleLineRecord(final Record record) {
        // We just ignore line records (we don't support mixed-mode files in this sample).
    }

    @Override
    public boolean handleStructuredFieldIntroducer(final StructuredFieldIntroducer sfi) {
        // *ONLY* if the read record is a "Begin Page" (BPG) structured field: parse
        // the structured field and pass the passed structured field to method
        // "handleStructuredField" of the RecordHandler.
        return sfi.getStructuredFieldIdentifier() == StructuredFieldIdentifier.BPG;
    }

    @Override
    public void handleStructuredField(final StructuredField sf) {
        // We only get invoked on structured field "Begin Page" (BPG) - see above...
        ++pageCounter;
    }

    @Override
    public void handleFaultyStructuredField(final FaultyStructuredField sf) {
        // Hopefully we don't see faulty structured fields in our file...
    }
};

new AfpParser(new StandardRecordReader(is), rh).parse();

System.out.println("Pages in this file: " + pageCounter);
```

## PTOCA-Parser
If you want to parse PTOCA control sequences, you have to combine the PTOCA data of all PTX
structured fields (within a Presentation Text Block) and parse this combined data.

afpbox provides a `PtocaParser` for this PTOCA data. To use this `PtocaParser` you need to implement
a `PtocaControlSequenceHandler` according to your needs. The idea of the design is somehow the same
as for the `RecordHandler` above - the application decides which control sequences are parsed
and which not. 

Here is an example how to use the `PtocaParser`. This sample removes all NOPs from the PtocaControlSequence
block and constructs a new PTOCA block. The sample is rather dumb and incomplete but it shows the idea
behind the `PtocaControlSequenceHandler` and how to use it.

```java
final ByteArrayOutputStream baos = new ByteArrayOutputStream();

PtocaParser.parse(ptocaBlock, new PtocaControlSequenceHandler() {

    @Override
    public boolean handleControSequence(final int functionType, final byte[] data, final int off) {
        // *ONLY* if the PTOCA function type is not "No Operation" (NOP - no matter if chained or unchained) parse
        // the PTOCA control sequence and invoke "handleControSequence" of the PtocaControlSequenceHandler.
        return functionType != PtocaControlSequenceFunctionType.NOP_UNCHAINED && functionType != PtocaControlSequenceFunctionType.NOP_CHAINED;
    }

    @Override
    public void handleControSequence(final PtocaControlSequence controlSequence) {
       baos.write(controlSequence.getData());
    }

    @Override
    public void handleCodePoints(final byte[] codePoints, final int off, final int len) {
        baos.write(controlSequence.getData(), off, len);
    }
});
```

# Structured Fields
The following table shows which Structured Fields are currently supported (*"supported"* means
that afpbox can parse the Stuctured Field and create a specific Java object for it).

Acronym| Identifier| Structured Field Name                  | Supported
-------|-----------|----------------------------------------|----------
BAG    | X'D3A8C9' | Begin Active Environment Group         | :x:
BBC    | X'D3A8EB' | Begin Bar Code Object                  | :x:
BDA    | X'D3EEEB' | Bar Code Data                          | :x:
BDD    | X'D3A6EB' | Bar Code Data Descriptor               | :x:
BDG    | X'D3A8C4' | Begin Document Environment Group       | :x:
BDI    | X'D3A8A7' | Begin Document Index                   | :x:
BDT    | X'D3A8A8' | Begin Document                         | :x:
BFG    | X'D3A8C5' | Begin Form Environment Group           | :x:
BFM    | X'D3A8CD' | Begin Form Map                         | :x:
BGR    | X'D3A8BB' | Begin Graphics Object                  | :x:
BII    | X'D3A87B' | Begin IM Image                         | :x:
BIM    | X'D3A8FB' | Begin Image Object                     | :x:
BMM    | X'D3A8CC' | Begin Medium Map                       | :x:
BMO    | X'D3A8DF' | Begin Overlay                          | :x:
BNG    | X'D3A8AD' | Begin Named Page Group                 | :x:
BOC    | X'D3A892' | Begin Object Container                 | :x:
BOG    | X'D3A8C7' | Begin Object Environment Group         | :x:
BPF    | X'D3A8A5' | Begin Print File                       | :x:
BPG    | X'D3A8AF' | Begin Page                             | :x:
BPS    | X'D3A85F' | Begin Page Segment                     | :x:
BPT    | X'D3A89B' | Begin Presentation Text Object         | :x:
BRG    | X'D3A8C6' | Begin Resource Group                   | :x:
BRS    | X'D3A8CE' | Begin Resource                         | :x:
BSG    | X'D3A8D9' | Begin Resource Environment Group       | :x:
CDD    | X'D3A692' | Container Data Descriptor              | :x:
CTC    | X'D3A79B' | Composed Text Control                  | :x:
EAG    | X'D3A9C9' | End Active Environment Group           | :x:
EBC    | X'D3A9EB' | End Bar Code Object                    | :x:
EDG    | X'D3A9C4' | End Document Environment Group         | :x:
EDI    | X'D3A9A7' | End Document Index                     | :x:
EDT    | X'D3A9A8' | End Document                           | :x:
EFG    | X'D3A9C5' | End Form Environment Group             | :x:
EFM    | X'D3A9CD' | End Form Map                           | :x:
EGR    | X'D3A9BB' | End Graphics Object                    | :x:
EII    | X'D3A97B' | End IM Image                           | :x:
EIM    | X'D3A9FB' | End Image Object                       | :x:
EMM    | X'D3A9CC' | End Medium Map                         | :x:
EMO    | X'D3A9DF' | End Overlay                            | :x:
ENG    | X'D3A9AD' | End Named Page Group                   | :x:
EOC    | X'D3A992' | End Object Container                   | :x:
EOG    | X'D3A9C7' | End Object Environment Group           | :x:
EPF    | X'D3A9A5' | End Print File                         | :x:
EPG    | X'D3A9AF' | End Page                               | :x:
EPS    | X'D3A95F' | End Page Segment                       | :x:
EPT    | X'D3A99B' | End Presentation Text Object           | :x:
ERG    | X'D3A9C6' | End Resource Group                     | :x:
ERS    | X'D3A9CE' | End Resource                           | :x:
ESG    | X'D3A9D9' | End Resource Environment Group         | :x:
FGD    | X'D3A6C5' | Form Environment Group Descriptor      | :x:
GAD    | X'D3EEBB' | Graphics Data                          | :x:
GDD    | X'D3A6BB' | Graphics Data Descriptor               | :x:
ICP    | X'D3AC7B' | IM Image Cell Position                 | :x:
IDD    | X'D3A6FB' | Image Data Descriptor                  | :x:
IEL    | X'D3B2A7' | Index Element                          | :x:
IID    | X'D3A67B' | Image Input Descriptor                 | :x:
IMM    | X'D3ABCC' | Invoke Medium Map                      | :x:
IOB    | X'D3AFC3' | Include Object                         | :x:
IOC    | X'D3A77B' | IM Image Output Control                | :x:
IPD    | X'D3EEFB' | Image Picture Data                     | :x:
IPG    | X'D3AFAF' | Include Page                           | :x:
IPO    | X'D3AFD8' | Include Page Overlay                   | :x:
IPS    | X'D3AF5F' | Include Page Segment                   | :x:
IRD    | X'D3EE7B' | IM Image Raster Data                   | :x:
LLE    | X'D3B490' | Link Logical Element                   | :x:
MBC    | X'D3ABEB' | Map Bar Code Object                    | :x:
MCC    | X'D3A288' | Medium Copy Count                      | :x:
MCD    | X'D3AB92' | Map Container Data                     | :x:
MCF    | X'D3AB8A' | Map Coded Font                         | :x:
MCF-1  | X'D3B18A' | Map Coded Font Format-1                | :x:
MDD    | X'D3A688' | Medium Descriptor                      | :x:
MDR    | X'D3ABC3' | Map Data Resource                      | :x:
MFC    | X'D3A088' | Medium Finishing Control               | :x:
MGO    | X'D3ABBB' | Map Graphics Object                    | :x:
MIO    | X'D3ABFB' | Map Image Object                       | :x:
MMC    | X'D3A788' | Medium Modification Control            | :x:
MMD    | X'D3ABCD' | Map Media Destination                  | :x:
MMO    | X'D3B1DF' | Map Medium Overlay                     | :x:
MMT    | X'D3AB88' | Map Media Type                         | :x:
MPG    | X'D3ABAF' | Map Page                               | :x:
MPO    | X'D3ABD8' | Map Page Overlay                       | :x:
MPS    | X'D3B15F' | Map Page Segment                       | :x:
MPT    | X'D3AB9B' | Map Presentation Text                  | :x:
MSU    | X'D3ABEA' | Map Suppression                        | :x:
NOP    | X'D3EEEE' | No Operation                           | :white_check_mark:
OBD    | X'D3A66B' | Object Area Descriptor                 | :x:
OBP    | X'D3AC6B' | Object Area Position                   | :x:
OCD    | X'D3EE92' | Object Container Data                  | :x:
PEC    | X'D3A7A8' | Presentation Environment Control       | :x:
PFC    | X'D3B288' | Presentation Fidelity Control          | :x:
PGD    | X'D3A6AF' | Page Descriptor                        | :x:
PGP    | X'D3B1AF' | Page Position                          | :x:
PGP-1  | X'D3ACAF' | Page Position Format-1                 | :x:
PMC    | X'D3A7AF' | Page Modification Control              | :x:
PPO    | X'D3ADC3' | Preprocess Presentation Object         | :x:
PTD    | X'D3B19B' | Presentation Text Data Descriptor      | :x:
PTD-1  | X'D3A69B' | Presentation Text Descriptor Format-1  | :x:
PTX    | X'D3EE9B' | Presentation Text Data                 | :x:
TLE    | X'D3A090' | Tag Logical Element                    | :x:

# Triplets
The following table shows the Triplets and the current status of the corresponding support of the Triplet 
(*"supported"* means that afobox can parse the Triplet and create a specific Java object for it).

ID    | Name                                                | Supported
------|-----------------------------------------------------|-------------------
X'4D' | Area Definition                                     | :x:
X'80' | Attribute Qualifier                                 | :x:
X'36' | Attribute Value                                     | :x:
X'26' | Character Rotation                                  | :x:
X'96' | CMR Tag Fidelity                                    | :x:
X'01' | Coded Graphic Character Set Global ID               | :x:
X'75' | Color Fidelity                                      | :x:
X'91' | Color Management Resource Descriptor                | :x:
X'4E' | Color Specification                                 | :x:
X'65' | Comment                                             | :x:
X'8B' | Data-Object Font Descriptor                         | :x:
X'43' | Descriptor Position                                 | :x:
X'97' | Device Appearance                                   | :x:
X'50' | Encoding Scheme ID                                  | :x:
X'22' | Extended Resource Local ID                          | :x:
X'88' | Finishing Fidelity                                  | :x:
X'85' | Finishing Operation                                 | :x:
X'20' | Font Coded Graphic Character Set Global Identifier  | :x:
X'1F' | Font Descriptor Specification                       | :x:
X'78' | Font Fidelity                                       | :x:
X'5D' | Font Horizontal Scale Factor                        | :x:
X'84' | Font Resolution and Metric Technology               | :x:
X'02' | Fully Qualified Name                                | :x:
X'9A' | Image Resolution                                    | :x:
X'73' | IMM Insertion (R)                                   | :x:
X'9D' | Keep Group Together                                 | :x:
X'27' | Line Data Object Position Migration (R)             | :x:
X'62' | Local Date and Time Stamp                           | :x:
X'8C' | Locale Selector                                     | :x:
X'04' | Mapping Option                                      | :x:
X'45' | Media Eject Control                                 | :x:
X'87' | Media Fidelity                                      | :x:
X'56' | Medium Map Page Number                              | :x:
X'68' | Medium Orientation                                  | :x:
X'8F' | MO:DCA Function Set                                 | :x:
X'18' | MO:DCA Interchange Set                              | :x:
X'4B' | Object Area Measurement Units                       | :x:
X'4C' | Object Area Size                                    | :x:
X'57' | Object Byte Extent                                  | :x:
X'2D' | Object Byte Offset                                  | :x:
X'63' | Object Checksum (R)                                 | :x:
X'10' | Object Classification                               | :x:
X'9C' | Object Container Presentation Space Size            | :x:
X'5E' | Object Count                                        | :x:
X'21' | Object Function Set Specification (R)               | :x:
X'5A' | Object Offset                                       | :x:
X'64' | Object Origin Identifier (R)                        | :x:
X'59' | Object Structured Field Extent                      | :x:
X'58' | Object Structured Field Offset                      | :x:
X'46' | Page Overlay Conditional Processing (R)             | :x:
X'81' | Page Position Information                           | :x:
X'82' | Parameter Value                                     | :x:
X'83' | Presentation Control                                | :x:
X'71' | Presentation Space Mixing Rules                     | :x:
X'70' | Presentation Space Reset Mixing                     | :x:
X'95' | Rendering Intent                                    | :x:
X'24' | Resource Local ID                                   | :x:
X'6C' | Resource Object Include                             | :x:
X'21' | Resource Object Type                                | :x:
X'25' | Resource Section Number                             | :x:
X'47' | Resource Usage Attribute (R)                        | :x:
X'86' | Text Fidelity                                       | :x:
X'1D' | Text Orientation (R)                                | :x:
X'74' | Toner Saver                                         | :x:
X'FF' | Triplet Extender                                    | :x:
X'72' | Universal Date and Time Stamp                       | :x:
X'8E' | UP3i Finishing Operation                            | :x:


# PTOCA Control Sequence
The following table shows which PTOCA Control Sequence are currently supported (*"supported"* means
that afpbox can parse the PTOCA Control Sequence and create a specific Java object for it).

Acronym| Control Sequence Name                  | Supported
-------|----------------------------------------|----------
AMB    | Absolute Move Baseline                 | :white_check_mark:
AMI    | Absolute Move Inline                   | :white_check_mark:
BLN    | Begin Line                             | :white_check_mark:
BSU    | Begin Suppression                      | :white_check_mark:
DBR    | Draw B-axis Rule                       | :x:
DIR    | Draw I-axis Rule                       | :x:
ESU    | End Suppression                        | :white_check_mark:
GAR    | Glyph Advance Run                      | :x:
GIR    | Glyph ID Run                           | :x:
GLC    | Glyph Layout Control                   | :x:
GOR    | Glyph Offset Run                       | :x:
NOP    | No Operation                           | :white_check_mark:
OVS    | Overstrike                             | :white_check_mark:
RMB    | Relative Move Baseline                 | :white_check_mark:
RMI    | Relative Move Inline                   | :white_check_mark:
RPS    | Repeat String                          | :white_check_mark:
SBI    | Set Baseline Increment                 | :white_check_mark:
SCFL   | Set Coded Font Local                   | :white_check_mark:
SEC    | Set Extended Text Color                | :x:
SIA    | Set Intercharacter Adjustment          | :white_check_mark:
SIM    | Set Inline Margin                      | :white_check_mark:
STC    | Set Text Color                         | :x:
STO    | Set Text Orientation                   | :x:
SVI    | Set Variable Space Character Increment | :white_check_mark:
TBM    | Temporary Baseline Move                | :white_check_mark:
TRN    | Transparent Data                       | :white_check_mark:
UCT    | Unicode Complex Text                   | :x:
USC    | Underscore                             | :white_check_mark:

# GOCA Drawing Orders
The following table shows which GOCA Drawing Orders are currently supported (*"supported"* means
that afpbox can parse the GOCA Drawing Order and create a specific Java object for it).

Acronym| Identifier| Structured Field Name                  | Supported
-------|-----------|----------------------------------------|----------
GBAR   | X'68'     | Begin Area                             | :x: 
GBCP   | X'DE'     | Begin Custom Pattern                   | :x:
GBIMG  | X'D1'     | Begin Image at Given Position          | :x:
GBOX   | X'C0'     | Box at Given Position                  | :x:
GCBEZ  | X'E5'     | Cubic Bezier Curve at Given Position   | :x:
GCBIMG | X'91'     | Begin Image at Current Position        | :x:
GCBOX  | X'80'     | Box at Current Position                | :x:
GCCBEZ | X'A5'     | Cubic Bezier Curve at Current Position | :x:
GCCHST | X'83'     | Character String at Current Position   | :x:
GCFARC | X'87'     | Full Arc at Current Position           | :x:
GCFLT  | X'85'     | Fillet at Current Position             | :x:
GCHST  | X'C3'     | Character String at Given Position     | :x:
GCLINE | X'81'     | Line at Current Position               | :x:
GCMRK  | X'82'     | Marker at Current Position             | :x:
GCOMT  | X'01'     | Comment                                | :x:
GCPARC | X'A3'     | Partial Arc at Current Position        | :x:
GCRLINE| X'A1'     | Relative Line at Current Position      | :x:
GDPT   | X'DF'     | Delete Pattern                         | :x:
GEAR   | X'60'     | End Area                               | :x:
GECP   | X'5E'     | End Custom Pattern                     | :x:
GEIMG  | X'93'     | End Image                              | :x:
GEPROL | X'3E'     | End Prolog                             | :x:
GFARC  | X'C7'     | Full Arc at Given Position             | :x:
GFLT   | X'C5'     | Fillet at Given Position               | :x:
GIMD   | X'92'     | Image Data                             | :x:
GLGD   | X'FEDC'   | Linear Gradient                        | :x:
GLINE  | X'C1'     | Line at Given Position                 | :x:
GMRK   | X'C2'     | Marker at Given Position               | :x:
GNOP1  | X'00'     | No-Operation                           | :x:
GPARC  | X'E3'     | Partial Arc at Given Position          | :x:
GRGD   | X'FEDD'   | Radial Gradient                        | :x:
GRLINE | X'E1'     | Relative Line at Given Position        | :x:
GSAP   | X'22'     | Set Arc Parameters                     | :x:
GSBMX  | X'0D'     | Set Background Mix                     | :x:
GSCA   | X'34'     | Set Character Angle                    | :x:
GSCC   | X'33'     | Set Character Cell                     | :x:
GSCD   | X'3A'     | Set Character Direction                | :x:
GSCH   | X'35'     | Set Character Shear                    | :x:
GSCLT  | X'20'     | Set Custom Line Type                   | :x:
GSCOL  | X'0A'     | Set Color                              | :x:
GSCP   | X'21'     | Set Current Position                   | :x:
GSCR   | X'39'     | Set Character Precision                | :x:
GSCS   | X'38'     | Set Character Set                      | :x:
GSECOL | X'26'     | Set Extended Color                     | :x:
GSFLW  | X'11'     | Set Fractional Line Width              | :x:
GSGCH  | X'04'     | Segment Characteristics                | :x:
GSLE   | X'1A'     | Set Line End                           | :x:
GSLJ   | X'1B'     | Set Line Join                          | :x:
GSLT   | X'18'     | Set Line Type                          | :x:
GSLW   | X'19'     | Set Line Width                         | :x:
GSMC   | X'37'     | Set Marker Cell                        | :x:
GSMP   | X'3B'     | Set Marker Precision (obsolete)        | :x:
GSMS   | X'3C'     | Set Marker Set                         | :x:
GSMT   | X'29'     | Set Marker Symbol                      | :x:
GSMX   | X'0C'     | Set Mix                                | :x:
GSPCOL | X'B2'     | Set Process Color                      | :x:
GSPIK  | X'43'     | Set Pick Identifier                    | :x:
GSPRP  | X'A0'     | Set Pattern Reference Point            | :x:
GSPS   | X'08'     | Set Pattern Set                        | :x:
GSPT   | X'28'     | Set Pattern Symbol                     | :x:
????   | X'71'     | End Segment                            | :x:

# Contribute
If you want to contribute to afpbox, you're welcome. But please make sure that your changes keep the quality of afpbox at least at it's current level. So please make sure that your contributions comply with the afpbox coding conventions (formatting etc.) and that your contributions are validated by JUnit tests.

It is easy to check this - just build the source with `gradle` before creating a pull request. The gradle default tasks will run [checkstyle](http://checkstyle.sourceforge.net/), [findbugs](http://findbugs.sourceforge.net/) and build the JavaDoc. If everything goes well, you're welcome to create a pull request.

Hint: If you use [Eclipse](https://eclipse.org/) as your IDE, you can simply run `gradle eclipse` to create the Eclipse project files. Furthermore you can import Eclipse formatter settings (see file `config/eclipse-formatter.xml`) as well as Eclipse preferences (see file `config/eclipse-preferences.epf`) that will assist you in formatting the afpbox source code according the used coding conventions (no tabs, UTF-8 encoding, indent by 4 spaces, no line longer than 120 characters, etc.).

# Manuals
The following reference materials were used to implement this parser:

* [MO:DCA Reference (Mixed Object Document Content Architecture Reference)](https://www.afpconsortium.org/uploads/1/1/8/4/118458708/modca-reference-09.pdf)
* [GOCA Reference (Graphics Object Content Architecture for AFP Reference)](https://www.afpconsortium.org/uploads/1/1/8/4/118458708/afp-goca-reference-03.pdf)
* [BCOCA Reference (Bar Code Object Content Architecture Reference)](https://www.afpconsortium.org/uploads/1/1/8/4/118458708/bcoca-reference-09.pdf)
* [CMOCA Reference (Color Management Object Content Architecture Reference)](https://www.afpconsortium.org/uploads/1/1/8/4/118458708/cmoca-reference-01.pdf)
* [FOCA Reference (Font Object Content Architecture Reference)](https://www.afpconsortium.org/uploads/1/1/8/4/118458708/foca-reference-06.pdf)
* [IOCA Reference (Image Object Content Architecture Reference)](https://www.afpconsortium.org/uploads/1/1/8/4/118458708/ioca-reference-07.pdf)
* [MOCA Reference (Metadata Object Content Architecture Reference)](https://www.afpconsortium.org/uploads/1/1/8/4/118458708/moca-reference-01.pdf)
* [PTOCA Reference (Presentation Text Object Content Architecture Reference)](https://www.afpconsortium.org/uploads/1/1/8/4/118458708/ptoca-reference-03.pdf)
* [Line Data Reference (Programming Guide and Line Data Reference)](https://www.afpconsortium.org/uploads/1/1/8/4/118458708/linedata-reference-05.pdf)

All those documents are available at the web site of the [AFP Consortium](https://www.afpconsortium.org)
