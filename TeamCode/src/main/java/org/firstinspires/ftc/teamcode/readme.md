# Makers Assemble 32480
### Introduction

Hello. we are Makers Assemble 32480.
This codebase is our main codebase and
we are happy that you decided to review our code because it means so much to us so we want to thank you from our hearts.

This folder is where our real robot code is.
Most of the important code in here is for drivetrain, shooter, intake, limelight, and autonomous.

## Our TeleOp
Our Teleop consists of 4 shooting modes.
3 shooting modes are pre-sets that we made to change our shooters velocity, and one shooting mode that changes our hood
degrees by how far we are from the target and changes the velocity on that same condition.
In addition to that, when we use the limelight shooting system, the robot changes its heading to face the target.

We use PID to tune our shooters velocity with a correction set to our preferences.

Our drivebase uses Mecanum drive and we have an IMU that lets us use field centric on the field letting us use the Mecanum drive to its fullest potential.

## Our Autonomous
Our Autonomous consists of 8 different routes.

6 routes are timed-based autonomous,
And 2 are encoder-based autonomous which let us use autonomous to its full potential and let us not depend on our current battery charge to decide if the autonomous works or not.

what is actually in this folder
quick file guide:

TeleOpMAnew.java = main driver control code
RobotHardwareMap.java = all hardware names and setup
HelperFuncs.java = helper commands to make writing autonomous faster
LimelightSubSystem.java = limelight reading / target math
SixBallsAutoBlue.java / SixBallsAutoRed.java = longer autos
BackAndShootAutoBlue.java / BackAndShootAutoRed.java = back up and shoot autos
leaveShootingZoneBlue.java / leaveShootingZoneRed.java = simple leave zone autos
motortest.java = testing stuff
Constants.java = names and constants
how we write code here
we are not trying to make this look like some giant software company project.
the main goal is that the robot works and that we can actually find stuff fast during build season.

that means:

hardware names go in Constants.java
hardware setup goes in RobotHardwareMap.java
repeated autonomous actions should go in HelperFuncs.java
opmodes should stay as readable as possible
if something looks a little weird but the robot runs solid, leave it alone unless there is a reason to change it.

## HelperFuncs Modules
We made HelperFuncs.java to make writing commands faster.
The point of it is so we do not have to rewrite the same lines every single time.
Instead of writing the same thing again and again, we can just call one function and keep moving faster.
It is basically our shortcut / command file for repeated robot actions.

### stuff in there includes:

shooter helpers
intake / kicker helpers
encoder movement helpers
simple timed movement helpers
so if we are writing something and it repeats a lot, HelperFuncs.java is one of the first places we check before writing a bunch of duplicate code.

# current robot focus
right now this code is mainly built around:

mecanum drive
field oriented driving with the imu
shooter + hood + kicker system
intake system
limelight aiming / distance help
autonomous routines that are fast to edit