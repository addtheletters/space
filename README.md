space
=====

A space game / engine / thing. Will likely include gravity, collisions, and other stuff.

Heat Systems Planning:
-Joules of heat
-Sinks can suck up a certain # of joules
-Temp determined as joules/degree kelvin
-Heat capacity: Degree at which boolean overCapacity becomes true
-Radiators and hulls of certain sorts release heat at a certain joules/tick
-Heat release means detectable
-More heat release means more detectable
-Temperature?


Missiles aren't working. Issues with constructors or something?
-Make all components have constructor with (themselves)arg and
-add a clone() method

Gonna start working on information mechanics.
-Data sources
-Data aggregators collecting data from sources
-AI or interface uses data from aggregators to display.
-Hacking may alter aggregated information, alter incoming info from sources, or alter how it is displayed


Wiki has mechanics info: https://github.com/addtheletters/space/wiki

TODO:
-Test projectiles, missiles, TurnControl
-Make updates for components utilize delta
-Make getOGLPos work to get the right 3d space position for the mouse
-Make collisions work with ships and direction facings. Prolly gonna need some complicated trig -_-

Current EntityTest functionality:
-Draws a starfeild
-Draws a bunch of movers that leave fading trails
-Draws a bunch of things that face the red
-Draws a bunch of things that accelerate, some of which accelerate in the direction they face
-Draws a bunch of things that accelerate and turn to face in a specific direction without changing trajectory
-Draws a bunch of controllable accelerator things with lots of options :D

Current TestGraphics functionality:
-Test3D's random points/lines
-First person cam (see comments for more)
-Rudimentary GLSL Shaders

Planned TestGraphics functionality:
-2d overlay testing
-3d model testing
-Moving entity display
-Laser display

Current Test3D functionality:
-Generates large grid of random points and lines
-X, Y and Z camera movement, determinable by mouse and keyboard, delta-adjusted for framerate
  -see comments in Test3D
-Vertex Buffer Object rendering

Current OpenALTest functionality:
-Plays sound when you click
-Uses direct IntBuffers to store source / sound buffer handles

See wiki on GitHub for planned final game mechanics and other info.

----UPDATE----
Hey, so what are the thoughts about multiplayer? -Zach
I'm not sure. It most definitley will not be turn-based so... we'll cross that bridge when we come to it. It'd be really nice if we could make it work though. -Ben
