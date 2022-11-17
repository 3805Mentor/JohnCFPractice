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

import com.atomicrobotics.cflib.CommandScheduler
import com.atomicrobotics.cflib.Constants.drive
import com.atomicrobotics.cflib.Constants.opMode
import com.atomicrobotics.cflib.controls.Controls

/**
 * This class manages the controls for TeleOp OpModes. If you want to register a command, type a
 * line into registerCommands with the following format:
 * gamepad.button.command = { Subsystem.command }
 * For example, that could look like this:
 * gamepad1.a.startCommand = { Lift.toHigh }
 * If you used the line above, then whenever you pressed the a button on gamepad1 it would move the
 * lift to the high position.
 */
object PracticeControls : Controls() {

    /**
     * Registers commands on the gamepads.
     */
    override fun registerCommands() {
        CommandScheduler.scheduleCommand(drive.driverControlled(opMode.gamepad1))
        gamepad1.a.pressedCommand = { drive.switchSpeed() }
        gamepad1.x.pressedCommand = { PracticeClaw.open }
        gamepad1.y.pressedCommand = { PracticeClaw.close }
        gamepad1.dpadUp.pressedCommand = { PracticeLift.start }
        gamepad1.dpadUp.releasedCommand = { PracticeLift.stop }
        gamepad1.dpadDown.pressedCommand = { PracticeLift.reverse }
        gamepad1.dpadDown.releasedCommand = { PracticeLift.stop }
    }
}