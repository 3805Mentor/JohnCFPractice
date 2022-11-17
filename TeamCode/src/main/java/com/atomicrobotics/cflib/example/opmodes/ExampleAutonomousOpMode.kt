/*
    Copyright (c) 2022 Atomic Robotics (https://atomicrobotics3805.org)

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see https://www.gnu.org/licenses/.
*/
package com.atomicrobotics.cflib.example.opmodes

import com.acmerobotics.roadrunner.geometry.Pose2d
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.atomicrobotics.cflib.Constants
import com.atomicrobotics.cflib.driving.drivers.MecanumDrive
import com.atomicrobotics.cflib.driving.localizers.TwoWheelOdometryLocalizer
import com.atomicrobotics.cflib.example.drive.ExampleMecanumDriveConstants
import com.atomicrobotics.cflib.example.localizers.ExampleOdometryConstants
import com.atomicrobotics.cflib.example.mechanisms.Claw
import com.atomicrobotics.cflib.example.mechanisms.Lift
import com.atomicrobotics.cflib.example.routines.ExampleRoutines
import com.atomicrobotics.cflib.example.trajectoryfactory.ExampleTrajectoryFactory
import com.atomicrobotics.cflib.opmodes.AutonomousOpMode

/**
 * This class is an example of how you can create an Autonomous OpMode. Everything is handled by
 * the AutonomousOpMode parent class, so all you have to do is pass in the constructor parameters.
 */
@Disabled
@Autonomous(name = "Example Auto OpMode")
class ExampleAutonomousOpMode : AutonomousOpMode(
    Constants.Color.BLUE,
    ExampleTrajectoryFactory,
    { ExampleRoutines.mainRoutine },
    { ExampleRoutines.initializationRoutine },
    MecanumDrive(
        ExampleMecanumDriveConstants,
        TwoWheelOdometryLocalizer(ExampleOdometryConstants())
    ) { Pose2d() },
    Lift,
    Claw
)