space
=====

A space game / engine / thing. Will likely include gravity, collisions, and other stuff.

Also, I need a much more descriptive README, such as

TODO, Release Notes, Version History

Umm... It's not really even a game yet so I'll hold off on version numbers for now...
Anyways...

Changed functionality of Renderer class
EntityRenderer, a basic one, not yet actually implemented render()
Restricted turn and acceleration components

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
