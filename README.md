# Color & Number Match Card Game

## Project Description

Color & Number Match Card Game is a JavaFX-based multiplayer card game inspired by the classic game Uno. The application recreates traditional turn-based mechanics where players match cards by color or number while introducing original features, customizable settings, and an interactive digital user interface.

The objective of the game is to eliminate all cards from your hand before the other players. The project includes multiplayer gameplay support, AI-controlled bot opponents, action cards such as Reverse, Skip, and Draw Two, customizable settings, fullscreen support, and accessibility features such as colorblind mode.

The user interface was designed to simulate the experience of playing a physical card game while remaining visually organized and beginner-friendly. Players can interact with cards directly through an intuitive JavaFX GUI and navigate between multiple scenes including the Main Menu, Lobby, Settings, How To Play, Gameplay, and Game Over screens.

---

# Contributors

- Elisa Moran
- Hadia Khaire
- Makayla Kemp

---

# Technologies Used

- Java 21
- JavaFX
- Maven
- IntelliJ IDEA

---

# Project Structure

The project follows the MVC (Model-View-Controller) design pattern.

## Model
Handles game logic, players, decks, cards, settings, and gameplay mechanics.

## View
FXML layout files and graphical UI elements.

## Controller
Manages user interaction, scene switching, gameplay updates, and settings functionality.

---

# How to Run the Application

## Requirements

- Java JDK 21
- Maven installed
- JavaFX dependencies enabled
- IntelliJ IDEA recommended

## Running the Application

1. Clone the repository:

```bash
git clone <repository-url>
```

2. Open the project in IntelliJ IDEA.

3. Ensure Maven dependencies are loaded.

4. Run the application using the JavaFX Maven plugin:

```bash
mvn javafx:run
```

## Known Issues

Some settings changes may cause minor UI inconsistencies across scenes.
Fullscreen scaling may occasionally result in floating text or incorrect element alignment.
Certain layout elements may slightly shift depending on display resolution or monitor size.
Multiplayer gameplay is still partially limited in some scenarios.

## Notes

This project was developed and tested using Windows 11 and IntelliJ IDEA.
