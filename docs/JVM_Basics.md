# JVM Basics

## What is JDK, JRE, JVM

### JVM (Java Virtual Machine)

JVM is the engine that runs Java programs.

When you write Java code, it does not run directly on your computer. First, it is converted into bytecode, and the JVM reads that bytecode and executes it.

You can think of JVM as a translator between Java code and your operating system.

---

### JRE (Java Runtime Environment)

JRE provides everything needed to run a Java program.

It includes:

* JVM
* Required libraries and files to run Java applications

If you only want to run Java programs, JRE is enough.

---

### JDK (Java Development Kit)

JDK is used to develop Java programs.

It includes:

* JRE
* Compiler (`javac`)
* Debugging tools
* Development utilities

If you want to write, compile, and run Java programs, you need JDK.

---

### Simple Relationship

**JDK > JRE > JVM**

* JVM runs Java code
* JRE provides the environment to run it
* JDK provides tools to create it

---

## What is Bytecode

Bytecode is the intermediate code generated after compiling a Java program.

When you write a Java source file such as `HelloWorld.java`, compile it with:

```bash
javac HelloWorld.java
```

This produces `HelloWorld.class`.

That `.class` file contains **bytecode**.

Bytecode is not machine code and not human-readable source code. It is a special format that JVM understands.

The JVM reads this bytecode and converts it into machine-specific instructions.

---

## What does “Write Once, Run Anywhere” mean

Java follows the idea of “Write Once, Run Anywhere” because Java code is compiled into bytecode, not directly into machine-specific code.

This means you can write a Java program on one system (for example Windows) and run the same `.class` file on another system (like Linux or Mac) without changing the code.

As long as the target system has a compatible JVM installed, the same Java program can run there. The JVM handles the platform-specific execution, which makes Java highly portable.
