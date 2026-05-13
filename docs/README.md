# Sudoku Game - MVC Java Project

Professional Sudoku application built with Java Swing and MVC architecture.

## Project Overview
This project is a complete Sudoku implementation designed for academic excellence, following best practices in software engineering.

## Architecture: Model-View-Controller (MVC)
The project is strictly organized into three layers:
- **Model**: Manages data (`Cell[][]`), game state, and validation logic. It is independent of the UI.
- **View**: Handled by `SudokuGUI`, using Java Swing. It only displays data and notifies events.
- **Controller**: Coordinates everything. It updates the model when the user interacts with the view and refreshes the view accordingly.

## Technical Details

### Backtracking Algorithm
The solver uses a recursive backtracking algorithm:
1. Find an empty cell.
2. Try numbers 1-9.
3. Check if the number is valid (row, col, block).
4. If valid, place it and recurse.
5. If it leads to a solution, return true. Otherwise, backtrack (reset cell to 0).

### GitFlow Workflow
We follow a strict GitFlow pattern:
- `main`: Production-ready code.
- `develop`: Integration branch for features.
- `feature/*`: Specific features like `feature/ui-welcome-screen`.

### CI/CD with GitHub Actions
Configured in `.github/workflows/ci.yml`:
- Automated build on every push to `develop/main`.
- Unit testing with JUnit 5.
- Code coverage reporting with JaCoCo.

## Installation & Execution
1. Clone the repository.
2. Ensure you have JDK 17 and Maven installed.
3. Compile: `mvn clean compile`
4. Run: `mvn exec:java -Dexec.mainClass="com.sudoku.Main"`

## Oral Defense Points
- **Modularity**: Explain how MVC allows changing the UI without touching the logic.
- **Algorithm Efficiency**: Discuss the complexity of backtracking in a 9x9 grid.
- **Tooling**: Highlight the use of Maven, JUnit, and GitHub Actions for professional quality.
