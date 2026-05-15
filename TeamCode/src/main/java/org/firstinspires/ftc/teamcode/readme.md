# Makers Assemble 32480 Robot Code

This repository contains the main robot code for Makers Assemble 32480.

The important team code is located in this folder and includes drivetrain control, shooter control, intake control, Limelight targeting, and autonomous routines.

## TeleOp

Our TeleOp code includes four shooting modes:

- Three preset shooting modes that set the shooter velocity.
- One Limelight-assisted shooting mode that adjusts the hood angle and shooter velocity based on distance from the target.

When using the Limelight shooting system, the robot also adjusts its heading to face the target.

The shooter velocity is controlled using PID tuning, with correction values adjusted to match our robot’s behavior.

The drivetrain uses Mecanum wheels with IMU-based field-centric control. This allows the driver to move relative to the field instead of only relative to the robot’s current direction.

## Autonomous

Our autonomous code includes eight routes:

- Six timed autonomous routes.
- Two encoder-based autonomous routes.

The encoder-based routes are more consistent because they depend on motor encoder feedback instead of only time. This makes them less affected by battery voltage and robot speed changes.

## File Guide

| File | Purpose |
|---|---|
| `TeleOpMAnew.java` | Main driver-controlled TeleOp code |
| `RobotHardwareMap.java` | Hardware names and setup |
| `HelperFuncs.java` | Reusable helper commands for autonomous and robot actions |
| `LimelightSubSystem.java` | Limelight readings and target math |
| `SixBallsAutoBlue.java` / `SixBallsAutoRed.java` | Longer autonomous routines |
| `BackAndShootAutoBlue.java` / `BackAndShootAutoRed.java` | Back up and shoot autonomous routines |
| `leaveShootingZoneBlue.java` / `leaveShootingZoneRed.java` | Simple leave-zone autonomous routines |
| `motortest.java` | Motor and mechanism testing code |
| `Constants.java` | Hardware names and constants |

## Code Organization

The goal of this codebase is to keep the robot reliable and make the code easy to modify during build season.

Our structure is:

- Hardware names go in `Constants.java`.
- Hardware setup goes in `RobotHardwareMap.java`.
- Repeated autonomous actions go in `HelperFuncs.java`.
- OpModes should stay as readable as possible.

If something works reliably on the robot, we avoid changing it unless there is a clear reason.

## Helper Functions

`HelperFuncs.java` contains reusable commands that make autonomous code faster to write and easier to read.

It includes helpers for:

- Shooter control
- Intake and kicker actions
- Encoder-based movement
- Simple timed movement

When an action repeats across multiple autonomous routes, we usually move it into `HelperFuncs.java` instead of duplicating the same code.

## Current Robot Focus

This codebase is currently built around:

- Mecanum drive
- Field-centric driving using the IMU
- Shooter, hood, and kicker systems
- Intake system
- Limelight aiming and distance calculation
- Autonomous routines that are quick to edit