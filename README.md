# RRPathGen

RRPathGen is a tool for generating rough RoadRunner trajectories without the need of a robot.<br>
This fork of the original RRPathGen project is syntactically compatible with the BunyipsLib RoadRunner abstractions.<br><br>
For modern BunyipsLib versions (> v5.1.1), use this fork's latest release [v2.1](https://github.com/Murray-Bridge-Bunyips/BunyipsLib_RRPathGen/releases/latest), and also see the built-in [MeepMeep](https://github.com/acmerobotics/meepmeep) functionality built into BunyipsLib to verify pathing.<br>
If using BunyipsLib v5.1.1 or less, use release version [v1.2](https://github.com/Murray-Bridge-Bunyips/BunyipsLib_RRPathGen/releases/tag/release_3) of this fork, and see also [RRPathVisualizer](https://github.com/Murray-Bridge-Bunyips/BunyipsLib_RRPathVisualizer) to verify pathing.

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
