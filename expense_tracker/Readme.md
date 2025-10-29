
# Expense Tracker (HW2) — Documentation & Setup

This project is the baseline "Expense Tracker" app where users can add daily transactions by amount and category. It follows a simple MVC structure with `model`, `view`, and `controller` packages and a `ExpenseTrackerApp` entry point.

## Prerequisites

- JDK: tested with `openjdk 17.0.7 2023-04-18`. Use Java 17 or newer.

## Compile & Run

From the project root, navigate to `src`, compile, and run the app. Running must be done from the `src` directory so the classpath resolves the packaged classes (`controller`, `model`, `view`).

```
cd src
javac ExpenseTrackerApp.java
java ExpenseTrackerApp
```

If you see `Error: Could not find or load main class ExpenseTrackerApp`, ensure you are in the `src` directory when running `java ExpenseTrackerApp`.

## Generate Javadoc

Generate API documentation into a `jdoc` folder (sibling of `src`). Run this from the `src` directory:

```
cd src
javadoc -d ../jdoc ExpenseTrackerApp.java controller/*.java model/*.java view/*.java
```

The generated documentation will be located in `../jdoc/index.html`.

## Project Structure

- `src/ExpenseTrackerApp.java`: Application entry point; wires Model, View, Controller.
- `src/model/`: Domain model (`Transaction`, `ExpenseTrackerModel`).
- `src/view/`: Swing UI (`ExpenseTrackerView`).
- `src/controller/`: Orchestration and validation (`ExpenseTrackerController`, `InputValidation`).

## Notes

- On Windows PowerShell, ensure you change directory to `src` before running `java ExpenseTrackerApp`.
- If you edit source files, re-run `javac ExpenseTrackerApp.java` from the `src` directory to recompile.
