# RRPathGen

RRPathGen is a tool to generate [Road Runner](https://github.com/acmerobotics/road-runner) paths.
![RRPathGen](https://i.imgur.com/TN0eaFO.png)

# Bunyips Changes + Notes

When using RRPathGen with Bunyips bots, it's recommended you use this fork of the program.
The only change made here is that exported code will use the BunyipsLib method `addNewTrajectory()`, 
a more efficient way of making RoadRunner autonomous paths.\
This saves the programmer having to replace the code manually.

## Installation (Jar)

1. Download the jar from the [releases page](https://github.com/Murray-Bridge-Bunyips/RRPathGen/releases).
2. Check that you have at least java 8 installed `java --version`
3. Run the jar either by double clicking it or through the command line with `java -jar RRPathGen-X.X.X.jar`


## Installation (Intellij)

1. Clone the repo `git clone https://github.com/Murray-Bridge-Bunyips/RRPathGen.git`
2. Setup a run configuration
3. Run the app

## Usage

Generate your paths using the key binds below and once you are done export the path with the export button and copy paste it into your autonomous program.

| Key Bind            | Action                  |
|---------------------|-------------------------|
| Left Click          | Add New Point           |
| Left Drag (Point)   | Drags Selected Point    |
| Alt + Left Click    | Change End Tangent      |
| Shift + Alt + Left Click | Change Robot Heading |
| Left Arrow          | Next Path               |
| Right Arrow         | Previous Path           |
| R                   | Reverse Robot Direction |
| Delete / Backspace  | Delete Selected Node    |
| Ctrl + Z            | Undo Previous Action    |

If you accidentally do something wrong with the config, just delete it at `%appdata%/RRPathGen` for Windows, `~/Library/Application Support/RRPathGen/config.properties` for MacOS and `~/.RRPathGen/config.properties` for Linux.

## Original Project
[RRPathGen by Jarhead20](https://github.com/Jarhead20/RRPathGen)

## License
[MIT](https://choosealicense.com/licenses/mit/)
