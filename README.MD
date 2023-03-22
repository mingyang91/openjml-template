# TL;DR
This guide has been validated on multiple platforms, including:
* macOS, including M1 chip
* Ubuntu 20 or higher
* Windows Subsystem Linux(WSL)

If you encounter any difficulties, please feel free to contact me through the LSBF team or by creating a GitHub issue.

## Prerequisite
* Ensure you have a JDK installed (version 17 or higher is recommended).
* Make sure you have a good network connection to access resources stored in Maven and GitHub.
  * If you need to set proxy options, follow this [Gradlew behind a proxy](https://stackoverflow.com/questions/8938994/gradlew-behind-a-proxy)
## Setup
Run `./gradlew downloadOpenJML`. You will see one of the following outputs:
```bash
$ ./gradlew downloadOpenJML
👌 OpenJML package is present in /Users/mingyang/Repository/fm/build/tmp/download/openjml.zip, no need to do this
👌 OpenJML home is present in /Users/mingyang/Repository/fm/openjml, no need to do this
👌 javac has been replaced
👌 java has been replaced

BUILD SUCCESSFUL in 2s
4 actionable tasks: 4 up-to-date
```
OR
```bash
$ ./gradlew downloadOpenJML   

> Task :buildSrc:jar
:jar: No valid plugin descriptors were found in META-INF/gradle-plugins
👌 OpenJML package is present in /Users/mingyang/Repository/fm/build/tmp/download/openjml.zip, no need to do this
📦 OpenJML unpacking, /Users/mingyang/Repository/fm/openjml
✅ OpenJML unpacked, /Users/mingyang/Repository/fm/openjml
✅ Javac is replaced, /Users/mingyang/Repository/fm/openjml
✅ Java is replaced, /Users/mingyang/Repository/fm/openjml

BUILD SUCCESSFUL in 14s
4 actionable tasks: 3 executed, 1 up-to-date
```
## Run Test
Run `./gradlew test`. You will see:
```bash
./gradlew test           

> Task :test

SetAsTree.SetAsTreeFailingTest > testSequence_1 FAILED
    java.lang.AssertionError at SetAsTree.SetAsTreeFailingTest.java:26

1398 tests completed, 1 failed

> Task :test FAILED

FAILURE: Build failed with an exception.

* What went wrong:
Execution failed for task ':test'.
> There were failing tests. See the report at: file:///Users/mingyang/Repository/fm/build/reports/tests/test/index.html

* Try:
> Run with --stacktrace option to get the stack trace.
> Run with --info or --debug option to get more log output.
> Run with --scan to get full insights.

* Get more help at https://help.gradle.org

BUILD FAILED in 9s
7 actionable tasks: 1 executed, 6 up-to-date
```
Don't worry about the failed test. It's simply validating that the function of Runtime Assertion Checking (RAC) is good.
