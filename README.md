space
=====

A space game / engine / thing. Will likely include gravity, collisions, and other stuff.

Also, I need a much more descriptive README, such as

TODO, Release Notes, Version History

Umm... It's not really even a game yet so I'll hold off on version numbers for now...
Anyways...

*Text overlay in TestGraphics displays odd blocks instead of actual characters.
*Someone find a fix! Use the powah of the internet XD

-added slick2d library
-removed slick_util because of conflicts with slick

Entity update now needs delta and all other entities in the area
Todo: Make update actually use delta

Current EntityTest functionality:
-Draws a feild of stars, as entities with PointRenderComponents
-Draws a few wanderers, moving in one random direction

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
