# JVM Basics

## JDK, JRE, JVM

**JVM (Java Virtual Machine)** runs bytecode. It is the engine between your compiled program and the operating system.

**JRE (Java Runtime Environment)** includes the JVM and libraries needed to **run** Java programs.

**JDK (Java Development Kit)** includes the JRE plus tools to **develop** programs (for example `javac`).

Relationship: **JDK contains JRE contains JVM.**

## What is Bytecode

When you compile `.java` files with `javac`, the compiler produces `.class` files containing **bytecode**. Bytecode is not machine code for one CPU; the JVM interprets or compiles it at runtime for the host platform.

## “Write Once, Run Anywhere”

Because Java compiles to bytecode instead of one machine’s native code, the same `.class` files can run on any system that has a compatible JVM. The JVM handles platform differences.
