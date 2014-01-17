space
=====

A space game / engine / thing. Will likely include gravity, collisions, and other stuff.

Wiki has mechanics info: https://github.com/addtheletters/space/wiki

Dev diary: https://github.com/addtheletters/space/wiki/Dev-Diary

Currrent Objective: Create data systems. Any advice is welcome :P

Priority tasks:
- Polishing player ship movement / control
- Heat-based detection
- Collisions of simple objects
- Basic shooting.
We want to get these tasks done ASAP so we can have something playable!
Then we'll think more of exactly how we want the game to work.

Backburner:
- Make updates for components utilize delta time #priority low
- Damage system, disabling of parts, etc #priority low

Note: Files in glTest are entirely for testing and may represent various stages of development. Some may cease to work as coding proceeds.

Current SensorTest functionality:
- Draws a bunch of gravity-pullable objects, with orange triangle overlays
- Draws a bunch of stationary gravity pullers, with pink square overlays
- Only displays objects detectable by a simple sensor in the protagonist entity
- Also has carried over protagonist + semiworking missile + other entities from EntityTest


See wiki on GitHub for planned final game mechanics and other info.
