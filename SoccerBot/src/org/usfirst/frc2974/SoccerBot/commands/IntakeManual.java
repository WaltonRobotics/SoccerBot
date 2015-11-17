package org.usfirst.frc2974.SoccerBot.commands;

import org.usfirst.frc2974.SoccerBot.Gamepad;
import org.usfirst.frc2974.SoccerBot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc2974.SoccerBot.subsystems.*;;

/**
 *
 */
public class IntakeManual extends Command {

	public IntakeManual() {
		super();

		requires(Robot.intake);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		SmartDashboard.putBoolean("manual mode", false);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (SmartDashboard.getBoolean("manual mode")) {
			boolean isPressed = Robot.oi.intakeUpDownButton.get();

			switch (Robot.intake.getAction()) {
			// if not pressed and in up- do nothing
			case up:
				if (!isPressed)
					break;
				// if pressed and in fall - do nothing
			case fall:
				if (isPressed)
					break;
				// if pressed and in fall, go up
				if (isPressed) {
					Robot.intake.setArmMovement(Intake.ArmMovement.up);
				} else {// if not pressed and in up, fall
					Robot.intake.setArmMovement(Intake.ArmMovement.fall);
				}

			}

			Robot.intake.setMotorPower((Robot.oi.xbox.getLeftTrigger() - .5) * 2);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
