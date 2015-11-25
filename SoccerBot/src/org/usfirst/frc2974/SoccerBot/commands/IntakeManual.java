package org.usfirst.frc2974.SoccerBot.commands;

import org.usfirst.frc2974.SoccerBot.Gamepad;
import org.usfirst.frc2974.SoccerBot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc2974.SoccerBot.subsystems.*;;


/**
 * @author piyus
 * @date 11-17-2015
 */
public class IntakeManual extends Command {
	
	public IntakeManual() {
		super();

		requires(Robot.intake);
	}

	// Called just before this Command runs the first time
	/**
	 * Called just before this Command runs the first time
	 */
	protected void initialize() {
		Robot.intake.setArmMovement(Intake.ArmMovement.block);
	}

	// Called repeatedly when this Command is scheduled to run
	/**
	 * Runs the robot. 
	 * Moves the loader up and down. Also runs the loaders TALON motors.
	 */
	protected void execute() {

		boolean isUpPressed = Robot.oi.xbox.getPOVButton(Gamepad.POV.N);
		boolean isFallPressed = Robot.oi.xbox.getPOVButton(Gamepad.POV.S);

		switch (Robot.intake.getAction()) {
		// if not pressed and in up- do nothing
		case up:
			if (!isUpPressed) {
				Robot.intake.setArmMovement(Intake.ArmMovement.block);
			}
			break;
		// if pressed and in fall - do nothing
		case fall:
			if (!isFallPressed) {
				Robot.intake.setArmMovement(Intake.ArmMovement.block);
			}
			break;
		// if pressed and in fall, go up
		case block:
			if (isUpPressed) {
				Robot.intake.setArmMovement(Intake.ArmMovement.up);
			} else if (isFallPressed) {
				Robot.intake.setArmMovement(Intake.ArmMovement.fall);
			}
			break;
		}

		Robot.intake.setMotorPower((Robot.oi.xbox.getLeftTrigger() - .5) * 2);

	}

	// Make this return true when this Command no longer needs to run execute()
	/**
	 * Make this return true when this Command no longer needs to run execute()
	 */
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	/**
	 * Called once after isFinished returns true
	 */
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	/**
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run
	 */
	protected void interrupted() {
	}
}
