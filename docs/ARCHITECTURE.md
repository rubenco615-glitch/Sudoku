# Architecture and Design

This document explains the technical architecture of the Sudoku project.

## MVC Pattern

The project follows the **Model-View-Controller** pattern to ensure modularity and ease of maintenance.

### Model
- **Location**: `src/main/java/com/sudoku/model`
- **Responsibility**: Manages game data, board state, and Sudoku rules.

### View
- **Location**: `src/main/java/com/sudoku/view`
- **Responsibility**: Handles user interface using Java Swing. No business logic.

### Controller
- **Location**: `src/main/java/com/sudoku/controller`
- **Responsibility**: Listens to user events from the View, updates the Model, and refreshes the View.

## Class Diagram

```mermaid
classDiagram
    class Sudoku {
        -Cell[][] board
        +generateBoard(Difficulty)
        +isMoveValid(row, col, val)
        +placeNumber(row, col, val)
        +isSolved()
    }
    class SudokuGUI {
        -JPanel gridPanel
        +refreshBoard()
        +showMessage(msg)
    }
    class SudokuController {
        -Sudoku model
        -SudokuGUI view
        +handleNewGame()
        +handleSolve()
    }
    class Cell {
        -int value
        -boolean fixed
    }
    
    SudokuController --> Sudoku : manages
    SudokuController --> SudokuGUI : updates
    Sudoku *-- Cell : contains
```

## State Diagram (Application States)

```mermaid
stateDiagram-v2
    [*] --> WelcomeScreen
    WelcomeScreen --> Playing : Click Jugar
    Playing --> Validating : Input Number
    Validating --> Playing : Move Valid/Invalid
    Playing --> Solved : Solution Verified
    Solved --> WelcomeScreen : Reset/New Game
    Playing --> Menu : Exit
```

## Flow Diagram (MVC)

```mermaid
sequenceDiagram
    participant User
    participant View
    participant Controller
    participant Model

    User->>View: Inputs number
    View->>Controller: Notify move event
    Controller->>Model: Validate move
    Model-->>Controller: Move valid/invalid
    Controller->>Model: Update state
    Controller->>View: Refresh display
    View-->>User: Show updated board
```
