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
import com.atomicrobotics.cflib.Constants.opMode
import com.atomicrobotics.cflib.Command
import com.atomicrobotics.cflib.subsystems.PowerMotor
import com.atomicrobotics.cflib.subsystems.Subsystem
import com.atomicrobotics.cflib.subsystems.MotorToPosition
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

    var NAME = "lift"
    var SPEED = 1.0
    var DIRECTION = DcMotorSimple.Direction.FORWARD
    var HIGH_POSITION = 10.0
    var LOW_POSITION = 5.0

    private const val PULLEY_WIDTH = 1.0
    private const val COUNTS_PER_REV = 28 * 19.2
    private const val DRIVE_GEAR_REDUCTION = 1.0
    private const val COUNTS_PER_INCH = COUNTS_PER_REV * DRIVE_GEAR_REDUCTION / (PULLEY_WIDTH * PI)

    val start: Command
        get() = PowerMotor(liftMotor, SPEED)
    val reverse: Command
        get() = PowerMotor(liftMotor, -SPEED)
    val stop: Command
        get() = PowerMotor(liftMotor, 0.0)
    val toBottom: Command
        get() = MotorToPosition(liftMotor, 0, SPEED)
    val toLow: Command
        get() = MotorToPosition(liftMotor, (LOW_POSITION * COUNTS_PER_INCH).toInt(), SPEED)
    val toHigh: Command
        get() = MotorToPosition(liftMotor, (HIGH_POSITION * COUNTS_PER_INCH).toInt(), SPEED)

    lateinit var liftMotor: DcMotorEx

    override fun initialize() {
        liftMotor = opMode.hardwareMap.get(DcMotorEx::class.java, NAME)
        liftMotor.direction = DIRECTION
        liftMotor.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        liftMotor.mode = DcMotor.RunMode.RUN_USING_ENCODER
    }
}