# Group 20 Project

# Communications
- Discord server

# Members
- Angus Ng
- Edison (Alison) Amery-Zwartz
- Fletcher Bright
- William Fermanis
- Lingjun Gui
- Krystal Ng

# ASSIGNMENTS
| Member | Assignment |
|   :---:   |    :---:     |
| Krystal Ng  | Renderer   |
| Willliam Fermanis  | Domain     |
| Lingjun Gui | Persistency |
| Fletcher Bright | App        |
| Angus Ng | Recorder   | 
| Whole Team       | Fuzz       |

https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html

# Map Design
Use txt file with unicode symbols to represent tiles, e.g. [level1](levels/level1.txt).
The txt map design file is for human to design the map.

Which can be converted to JSON file, used for program to save and load game.

Symbol List:
- 🤖: Chap
- 👻: Enemy
- 🚩: Exit
- 🈲: ExitLock
- 🔳: FreeTile
- 💡: InfoField
- 🔑: Key (Green): 
- 🗝️: Key (Yellow): 
- 🔒 :LockedDoor (Green)
- 🔏: LockedDoor (Yellow)
- 💎: Treasure
- 🧱: WallTile