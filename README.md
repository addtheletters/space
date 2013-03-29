space
=====

A space game / engine / thing. Will likely include gravity, collisions, and other stuff.

Added: Hopefully correct collisions? Need to extend to make it work with ships and direction facings.
Prolly gonna need some complicated trig -_-

-getOGLPos in TestGraphics.java might work, confirm plox

TODO:
-Make updates for components utilize delta
-Make getOGLPos work to get the right 3d space position for the mouse

Current EntityTest functionality:
-Draws a starfeild
-Draws a bunch of movers that leave fading trails
-Draws a bunch of things that face the red
-Draws a bunch of things that accelerate, some of which accelerate in the direction they face
-Draws a bunch of things that accelerate and turn to face in a specific direction without changing trajectory

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

While I'm thinking about it... Mechanics!
-A big part is information. Tactical information, knowledge of the enemy's plans and comm protocols, how they're obtaining information, where the enemy is, what weaponary they have, are they really hostile, etc, etc.
-As such, deception and stealth will play big parts.

-Another big mechanic for the eventual RPG element is know-how.
-Your character will develop, learning how to do new things. Don't expect to know how to adapt various technologies easily or use alien tech easily or be able to hack through the enemy's defenses without breaking a sweat, at least at the start.
-Various crew members and software you aquire could increase your "know-how" rating. But be warned of trojan horses and traitors.

-Befriending people! Convince traitors to come over to your side and give you information on your enemies.
-Reverse-engineer trojan horses to feed misinformation to your enemies.

-You may not even have "enemies." As intel and tactical information is so important, enemies are unwilling to enter fights they're sure they'll lose. Make them thing you outnumber them terribly and they might leave or surrender. Or make them think you're on their side by broadcasting friendly IFFs. Or conduct backdoor diplomacy and agree to certain terms. Just beware of backstabbing or gung-ho idiots who think they can beat a fleet 10 times the size of theirs.

-Gravity. Planets, large objects, gravity manipulation devices.
-Preservation of momentum. It's space. Think about it.
-This means missiles will go faster if you are going faster when you launch them
-Missiles have acceleration. They don't just go at the same speed.
-Missiles are.. fast. Have you seen an RPG, IRL?
-Sheilding will need a feasible explanation, not just "IT'S A SHEILD IT JUST BLOCKS ALL DAMAGE CAUSE I SAY SO"
-Gravitational barrier sheilding
-Electromagnetic feild sheilding
-Mass accelerator cannons
-Prolly the "bread and butter" of weapons. Can be stopped by gravitational sheilding and maybe advanced electromagnetic sheilding tho
-Involves throwing objects at very high speeds. May have something to do with gravitational manipulation or maybe just magnets
-Would prolly be more affective against ablative armor than, say, lasers
-Lasers. Less effective at long ranges, due to not 100% focused rays and standard dispersion factors.
-Lasers would prolly be best for close combat knife-fighting or taking down missiles and fighters.
-Then again, I plan on making missiles pretty fast. AI targeting would prolly be worth investing in ;)
-Ablative armor: works well against lasers. Maybe not so much vs cannons.
-DEWs: Particle beams? Sheilding might work with them differently to lasers.
-Plasma weapons? Prolly would work kinda like mass accelerators, or maybe be more heat-based for damage. 
-EMPs? Bombs?
-"C-fractional bombardment" with gravity manipulation >:)
-Different missile payloads
-Different missile thrusters (and ship thrusters? Maybe if you have the know-how you can repurpose thrusters for different things)
-Different weapon mounts
-"Spinal" weapons: Built into the ship, can only fire in certain direction(s)
-Turret weapons
-Active scanners: Send out signals in some way to hope for returns, can cause you to be detected by passive receivers
-Passive sensors: Attempts to formulate picture of surroundings using incoming data like ambient light, heat, gravitational signatures
-Comms: Broadcasts. Can reveal position, can be coded to certain frequencies. Location of receiver/sender not required to establish contact
-Comms: Tightbeam. Very unlikely to reveal position, can also have coding. Location of at least receiver needed to establish contact
-Comms can transmit data, like sensor and scanner information and coding/encryption protocols. Can also be used for cyberwarfare
-You could uncover certain factions' comms protocols through information brokers and such.
-However they prolly also have some other kind of encryption which you may need computing power to break through.
-Jamming. Interferes with active scans or passive sensors, can help mitigate targeting of missiles or other weapons systems, can interfere with comms
-Comms tapping: If you have someone's encryption protocols and they are broadcasting you can tap into their comms for their sensor data and other information
-If they are using tightbeam comms, you'd have to get a receiver INSIDE the beam, or have some advanced sensor array
-Hacking / Cyberwarfare: Direct physical connection or comms / network protocols of targets required. If they are passively accepting broadcasts it might be possible to exploit them.
-FTL travel. Combat jumping? The ability to use jumping as a way of accelerating objects to insane speeds. Speed counts. Things with lots of speed can do a lot. (slamming an FTL ship into a planet would cause... problems)
-Stealth systems could hide emissions, ambient reflections, or even grav signatures with highly advanced ones
-Active scanners could prolly root out lower teir stealth systems but maybe not higher teir ones
-Gravitational manipulation: cause gravitational disturbances. Alter the mass of your ship, or probes, or missiles, to take advantage of them. Cool stuff.
-Probes and drones. You could send them out with different goals: armed with weaponary, jamming, sensors, act as decoys, comm bouys, or to latch on to enemies and attempt cyberwarfare to aquire their comms protocols, general data, or attempt to shut down various systems. 
-Certain drones would require a comms connection to function, and you would certainly need a comms conection to command them to do stuff. If you know how you could program them to do stuff automatically, and then a comms system may or may not be needed. They could feasibly transmit data to your ship via tightbeam without the ship needing to send data to them
-If an enemy captures one of your drones and it does not have proper self-destruct protocols they could learn your comms protocols or even develop countermeasures to your cyberwarfare systems.
-Damage control? Various systems could become damaged by weapons, overheating, enemy cyberwarfare, sabotoge by traitors aboard you ship. You could repair them, or order your crew to, or have automated procedures take care of it. Damage could cause systems to become unusable. Not everything can be repaired if neither you nor any of your crew nor any of your repair protocols have the know-how to do so.
-Paying attention could be vital. Enemies could take out a drone of yours and replace it with one of theirs, feeding you misinformation. If you missed the lapse in signal being sent you could be unaware of this for a while. You could obtain monitoring programs or AIs that would assist you in detecting stuff like this.
-They could also hack into your drone if it is broadcasting unsecurely or they manage to come in contact with it physically and turn it against you. Prolly not good.
-Fuel/Drive cores: I prolly won't do much with fuel. I don't want fear of running out of fuel to be a really big concern. Drive cores I might do something with: I have to explain FTL jumps somehow. Not sure wether to do drive cores, or reactors that provide power for everything. Different weapons and propulsion systems may need different kinds of power or other vital cores. 
-You could also prolly convert most large energy cores into Improvised Explosive Devices, if you have the know-how or someone on your crew does.
-You'd just need some kind of way to get to where you want after you do that... or be willing to sacrifice whatever that core was providing power for
-Antimatter? Matter-antimatter annihilation engines? Seems like a good idea. Maybe a space equivalent of nitro.


Whew. Just needed to dump some of that out of my head. If anyone has any ideas feel free to add them. Or implement them as classes XD