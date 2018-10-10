# afpbox [![Build Status](https://travis-ci.org/michaelknigge/afpbox.svg?branch=master)](https://travis-ci.org/michaelknigge/afpbox) [![codecov.io](https://codecov.io/github/michaelknigge/afpbox/coverage.svg?branch=master)](https://codecov.io/github/michaelknigge/afpbox?branch=master) [![Coverity Status](https://scan.coverity.com/projects/10253/badge.svg)](https://scan.coverity.com/projects/10253) [![Download](https://api.bintray.com/packages/michaelknigge/maven/afpbox/images/download.svg) ](https://bintray.com/michaelknigge/maven/afpbox/_latestVersion)

Java library for parsing [AFP](https://en.wikipedia.org/wiki/Advanced_Function_Presentation) ([MO:DCA](https://en.wikipedia.org/wiki/MODCA)) printer data streams.

**NOTE: This project started in April 2018 and is completely useless in its current state. The goal is to make afpbox a
lightweight, fast and robust AFP parser. But keep in mind that this is a hobbyist project. So I guess
it will take up to two years to make afpbox a library that is of any use.**

# Dependencies
afpbox has no runtime dependencies on other libraries. This was a design decision and will (hopefully) never change.

# Usage
Because afpbox is available at [jcenter](https://bintray.com/bintray/jcenter) it is very easy to use afpbox in your projects. At first, add afpbox to your build file. If you use Maven, add the following to your build file:

```xml
<dependency>
  <groupId>de.textmode.afpbox</groupId>
  <artifactId>afpbox</artifactId>
  <version>0.1</version>
  <type>pom</type>
</dependency>
```

If you use Gradle, add this:

```
dependencies {
    compile 'de.textmode.afpbox:afpbox:0.1'
}
```

**TODO: Add usage sample here**

# Contribute
If you want to contribute to afpbox, you're welcome. But please make sure that your changes keep the quality of afpbox at least at it's current level. So please make sure that your contributions comply with the afpbox coding conventions (formatting etc.) and that your contributions are validated by JUnit tests.

It is easy to check this - just build the source with `gradle` before creating a pull request. The gradle default tasks will run [checkstyle](http://checkstyle.sourceforge.net/), [findbugs](http://findbugs.sourceforge.net/) and build the JavaDoc. If everything goes well, you're welcome to create a pull request.

Hint: If you use [Eclipse](https://eclipse.org/) as your IDE, you can simply run `gradle eclipse` to create the Eclipse project files. Furthermore you can import Eclipse formatter settings (see file `config/eclipse-formatter.xml`) as well as Eclipse preferences (see file `config/eclipse-preferences.epf`) that will assist you in formatting the afpbox source code according the used coding conventions (no tabs, UTF-8 encoding, indent by 4 spaces, no line longer than 120 characters, etc.).

# Manuals
The following reference materials were used to implement this parser:

* [MO:DCA Reference (Mixed Object Document Content Architecture Reference)](http://www.afpcinc.org/wp-content/uploads/2018/01/MODCA-Reference-09.pdf)
* [GOCA Reference (Graphics Object Content Architecture for AFP Reference)](http://www.afpcinc.org/wp-content/uploads/2018/01/AFP-GOCA-Reference-Graphics-Object-Content-Architecture-for-AFP-Reference.pdf)
* [BCOCA Reference (Bar Code Object Content Architecture Reference)](http://50.87.249.81/~afpcinc1/wp-content/uploads/2016/08/BCOCA-Reference-09.pdf)
* [CMOCA Reference (Color Management Object Content Architecture Reference)](http://50.87.249.81/~afpcinc1/wp-content/uploads/2016/08/cmoca_reference-01.pdf)
* [FOCA Reference (Font Object Content Architecture Reference)](http://50.87.249.81/~afpcinc1/wp-content/uploads/2016/08/FOCA-Reference-Font-Object-Content-Architecture-Reference.pdf)
* [IOCA Reference (Image Object Content Architecture Reference)](http://50.87.249.81/~afpcinc1/wp-content/uploads/2016/08/IOCA-Reference-Image-Object-Content-Architecture-Reference.pdf)
* [MOCA Reference (Metadata Object Content Architecture Reference)](http://50.87.249.81/~afpcinc1/wp-content/uploads/2016/08/MOCA-Reference-Metadata-Object-Content-Architecture-Reference.pdf)
* [PTOCA Reference (Presentation Text Object Content Architecture Reference)](http://50.87.249.81/~afpcinc1/wp-content/uploads/2016/08/PTOCA-Reference-Presentation-Text-Object-Content-Architecture-Reference.pdf)
* [Line Data Reference (Programming Guide and Line Data Reference)](http://www.afpcinc.org/wp-content/uploads/2018/02/LineData-Reference-05.pdf)

All those documents are available at the web site of the [AFP Consortium](http://www.afpcinc.org)
