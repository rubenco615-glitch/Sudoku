# Sudoku Pro - MVC Java Implementation

A premium Sudoku game built with Java, focusing on clean architecture (MVC) and professional software engineering standards.

## Project Structure

```
Sudoku/
├── src/main/java/com/sudoku/
│   ├── model/         # Game logic and data
│   ├── view/          # Java Swing GUI
│   ├── controller/    # Event handling
│   ├── service/       # Generator and Solver algorithms
│   └── util/          # Constants and validators
├── docs/              # Technical documentation
└── pom.xml            # Maven configuration
```

## Documentation

Detailed documentation can be found in the [docs/](docs/) folder:
- [General README](docs/README.md)
- [Architecture & MVC](docs/ARCHITECTURE.md)

## Development

This project follows **GitFlow**.

### Commits Example (Standard)
- `feat: add sudoku board model`
- `feat: implement swing interface`
- `fix: prevent editing fixed cells`

## Build & Run

```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="com.sudoku.Main"
```