# Setup Instructions

## JDK version

This project targets **Java 17**.

Example check from a terminal:

```text
java --version
javac --version
```

Sample output (your build numbers may differ):

```text
openjdk 17.0.x ...
OpenJDK Runtime Environment ...
OpenJDK 64-Bit Server VM ...
```

## IDE

IntelliJ IDEA (or another JDK-aware IDE) is recommended. Import/open the project folder so Gradle can resolve the Java toolchain.

## Hello World verification

1. Create a small class with a `public static void main(String[] args)` that prints `Hello, World!`.
2. Run it from the IDE **Run** action, or compile and run from the terminal:

```powershell
javac HelloWorld.java
java HelloWorld
```

3. For your submission, attach **screenshots** of a successful run (IDE output or terminal) in your portfolio or PR description if required by your coach.

The LearnTrack application entry point is `com.airtribe.learntrack.ui.Main` (see project `README.md` for run commands).
