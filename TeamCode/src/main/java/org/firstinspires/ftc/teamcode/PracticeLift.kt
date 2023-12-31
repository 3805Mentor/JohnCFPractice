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
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.DcMotorSimple
import org.atomicrobotics3805.cflib.Constants.opMode
import org.atomicrobotics3805.cflib.Command
import org.atomicrobotics3805.cflib.parallel
import org.atomicrobotics3805.cflib.subsystems.PowerMotor
import org.atomicrobotics3805.cflib.subsystems.Subsystem
import org.atomicrobotics3805.cflib.subsystems.MotorToPosition
import kotlin.math.PI

/**
 * This class is an example of a lift controlled by a single motor. Unlike the Intake example object, it can use
 * encoders to go to a set position. Its first two commands, toLow and toHigh, do just that. The start command turns
 * the motor on and lets it spin freely, and the reverse command does the same but in the opposite direction. The stop
 * command stops the motor. These last three are meant for use during the TeleOp period to control the lift manually.
 * To use this class, copy it into the proper package and change the first eight constants (COUNTS_PER_INCH is fine as
 * is).
 */
@Config
@Suppress("Unused", "MemberVisibilityCanBePrivate")
object PracticeLift : Subsystem {
    var NAME_1 = "lift1"
    var SPEED = 1.0
    var DIRECTION_1 = DcMotorSimple.Direction.REVERSE
    var HIGH_POSITION = 20
    var LOW_POSITION = 15
    private const val PULLEY_WIDTH = 2.0
    private const val COUNTS_PER_REV = 28 * 13.7
    private const val DRIVE_GEAR_REDUCTION =1
    private const val COUNTS_PER_INCH = COUNTS_PER_REV * DRIVE_GEAR_REDUCTION / (PULLEY_WIDTH * PI)
    lateinit var liftMotor1: DcMotorEx
    val start: Command
        get() = parallel {
                +PowerMotor(liftMotor1, SPEED)
        }
    val reverse: Command
        get() = parallel {
            +PowerMotor(liftMotor1, -SPEED)
        }
    val stop: Command
        get() = parallel {
            +PowerMotor(liftMotor1, 0.0)
        }
    val toBottom: Command
        get() = parallel {
                +MotorToPosition(liftMotor1, (0.5 * COUNTS_PER_INCH).toInt(), SPEED)
        }
    val toLow: Command
        get() = parallel {
            +MotorToPosition(liftMotor1, (LOW_POSITION * COUNTS_PER_INCH).toInt(), SPEED)
        }
    val toHigh: Command
        get() = parallel {
            +MotorToPosition(liftMotor1, (HIGH_POSITION * COUNTS_PER_INCH).toInt(), SPEED)
        }
    override fun initialize() {
        liftMotor1 = opMode.hardwareMap.get(DcMotorEx::class.java, NAME_1)
        liftMotor1.direction = DIRECTION_1
        liftMotor1.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        liftMotor1.mode = DcMotor.RunMode.RUN_USING_ENCODER
    }



}