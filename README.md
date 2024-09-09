# RRPathGen

This fork of the original RRPathGen project is syntactically compatible with the BunyipsLib `RoadRunner` interface.
This is the go-to tool for generating trajectories using the BunyipsLib framework, see also [RRPathVisualizer](https://github.com/Murray-Bridge-Bunyips/BunyipsLib_RRPathVisualizer).

RRPathGen is a tool to generate [Road Runner](https://github.com/acmerobotics/road-runner) v0.5 paths.
![RRPathGen](https://github.com/user-attachments/assets/13bc744e-f0ce-4779-aa87-9471e72bad61)

## Usage

Generate your paths using the key binds below and once you are done export the path with the export button and copy paste it into your autonomous program.

| Key Bind                 | Action                  |
|--------------------------|-------------------------|
| Left Click               | Add New Point           |
| Left Drag (Point)        | Drags Selected Point    |
| Alt(Option) + Left Click | Change End Tangent      |
| Shift + Alt(Option) + Left Click | Change Robot Heading    |
| Left Arrow               | Next Path               |
| Right Arrow              | Previous Path           |
| R                        | Reverse Robot Direction |
| Delete / Backspace       | Delete Selected Node    |
| Ctrl + Z                 | Undo Previous Action    |
| Ctrl Drag (Point)        | Snapping Rotations      |

If you accidentally do something wrong with the config, just delete it at `%appdata%/RRPathGen` for Windows, `~/Library/Application Support/RRPathGen/config.properties` for MacOS and `~/.RRPathGen/config.properties` for Linux.

## Acknowledgements
Upstream project [Jarhead20/RRPathGen](https://github.com/Jarhead20/RRPathGen).<br />
The inspiration from this project came from Technic Bots' [Blitz](https://technicbots.com/Blitz) app.<br />
The field images were aquired from [MeepMeep](https://github.com/NoahBres/MeepMeep).<br />
And a big thank you to [Ryan Brott](https://github.com/rbrott) for helping me with the spline implementation.

## License
[MIT](https://choosealicense.com/licenses/mit/) (Jarhead20/RRPathGen)
