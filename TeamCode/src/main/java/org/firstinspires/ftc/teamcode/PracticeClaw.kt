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
package org.firstinspires.ftc.teamcode

import com.acmerobotics.dashboard.config.Config
import com.qualcomm.robotcore.hardware.Servo
import com.qualcomm.robotcore.hardware.ServoController
import org.atomicrobotics3805.cflib.Command
import org.atomicrobotics3805.cflib.Constants
import org.atomicrobotics3805.cflib.Constants.opMode
import org.atomicrobotics3805.cflib.subsystems.MoveServo
import org.atomicrobotics3805.cflib.subsystems.Subsystem

/**
 * This class is an example of a claw controlled by a single servo. Its first two commands, open and close, which each
 * move the claw to the corresponding position. The third command, switch, opens it if it's closed and closes it if it's
 * open. The switch command is particularly useful during TeleOp.
 * If you want, you can also use this class for other mechanisms that also involve one servo, like a delivery bucket.
 * To use this class, copy it into the proper package and change the four constants.
 */
@Config
@Suppress("PropertyName", "MemberVisibilityCanBePrivate", "unused")
object PracticeClaw : Subsystem {
    var NAME = "claw"
    var OPEN_POSITION = 0.8
    var CLOSE_POSITION = 0.42
    var TIME = 1.0
    lateinit var clawServo: Servo
    // commands
    val open: Command
        get() = MoveServo(clawServo, OPEN_POSITION, TIME)
    val close:  Command get() = MoveServo(clawServo, CLOSE_POSITION, TIME)
    
    override fun initialize() {
        clawServo = opMode.hardwareMap.get(Servo::class.java, NAME)
    }
}