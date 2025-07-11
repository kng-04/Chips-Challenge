# 🧩 Larry Croft’s Adventures

_A SWEN225 Group Project — Victoria University of Wellington (2024)_

## 📘 Overview

**Larry Croft’s Adventures** is a creative reimagining of the classic *Chip’s Challenge* (1989), implemented entirely in Java. The game challenges players to solve tile-based mazes, collect keys and treasures, and escape each level — all while evading hazards and enemies.  

This project was developed as part of the SWEN225 Software Design course, and brings together concepts of object-oriented design, modular architecture, event-driven programming, and quality assurance practices.

> 👥 Team project (5–6 members)

---

## 🧱 Architecture

The project is split into six distinct modules, each mapped to a Java package:

| Module       | Responsibility |
|--------------|----------------|
| `domain`     | Game logic and rules (tiles, objects, movement, collision, etc.) |
| `app`        | GUI management, keystroke handling, level switching, countdown timer |
| `renderer`   | Visual rendering of the game board, animations, sounds |
| `persistency`| JSON-based save/load and level storage |
| `recorder`   | Record and replay entire gameplay sessions |
| `fuzz`       | Automated input testing to detect edge cases and bugs |

---

## ▶️ How to Run the Game

1. Open the project in **IntelliJ** or **Eclipse**
2. Locate the main class: nz.ac.wgtn.swen225.lc.app.Main
3. Run the program
   
---
