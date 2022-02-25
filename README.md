![LunarLander](https://github.com/Mroogal/LunarLander/blob/main/Photos/LunarLander.png?raw=true)
# LunarLander
LunarLander is a small game that we have made as a project for our Java classes. The project consists of two programs: the game itself and a server, that runs scoreboard and sends data about levels.

# Game
The game is about landing a spaceship on a platform. The score is calculated by adding a remaining fuel. On the crush the fuel is lost. A goal of the game is to get a highest possible score.

![Photo of main menu](https://github.com/Mroogal/LunarLander/blob/main/Photos/mainmenu.png?raw=true)
![Photo of main menu](https://github.com/Mroogal/LunarLander/blob/main/Photos/game2.png?raw=true)

We implemented a two difficulty levels with different maps and different multiplayers.

Pressing a **P** during game will show a quick menu with control options where you can choose between **WSAD**/**Arrows**.
The red/green circle is an indicator whether a client is connected to the server.

# Server
Server sends data of maps, stores scoreboard and configuration files. On the demand it sends data to a client (game).
