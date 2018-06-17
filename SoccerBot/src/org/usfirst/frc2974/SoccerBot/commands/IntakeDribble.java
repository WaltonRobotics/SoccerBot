package org.usfirst.frc2974.SoccerBot.commands;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc2974.SoccerBot.Gamepad;
import org.usfirst.frc2974.SoccerBot.Robot;
import org.usfirst.frc2974.SoccerBot.RobotMap;
import org.usfirst.frc2974.SoccerBot.subsystems.AbstractIntake.ArmMovement;
import org.usfirst.frc2974.SoccerBot.subsystems.AbstractIntake.ArmPosition;
import org.usfirst.frc2974.SoccerBot.subsystems.Intake;

/**
 * Makes the intake go to the dribble position, turns the motor to dribble a ball
 */
public class IntakeDribble extends Command {

	private final AnalogPotentiometer angleSensor = RobotMap.intakeAngleSensor;
	private boolean initialized;

	public IntakeDribble() {
		requires(Robot.intake);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		initialized = false;
		Robot.intake.setMotorPower(.3);
		if (Robot.intake.getArmPosition() == ArmPosition.up || Robot.intake.getArmPosition() == ArmPosition.high) {
			Robot.intake.setArmMovement(ArmMovement.fall);
		} else if (Robot.intake.getArmPosition() == ArmPosition.low
			|| Robot.intake.getArmPosition() == ArmPosition.flat) {
			Robot.intake.setArmMovement(ArmMovement.up);
		}
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (Robot.intake.getArmPosition() == ArmPosition.dribble) {
			initialized = true;
		}
		if (initialized) {
			moveIntake();
		}
		if (Robot.oi.loadButton.get()) {
			new IntakeLoad().start();
		}
		if (Robot.oi.flatButton.get()) {
			new IntakeFlat().start();
		}
		if (Robot.oi.dribbleButton.get()) {
			initialize();
		}
	}

	private void moveIntake() {
		boolean isUpPressed = Robot.oi.xbox.getPOVButton(Gamepad.POV.N);
		boolean isFallPressed = Robot.oi.xbox.getPOVButton(Gamepad.POV.S);
		if (isUpPressed) {
			Robot.intake.setArmMovement(Intake.ArmMovement.up);
		} else if (isFallPressed) {
			Robot.intake.setArmMovement(Intake.ArmMovement.fall);
		} else {
			Robot.intake.setArmMovement(Intake.ArmMovement.block);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.oi.manualButton.get();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.intake.setMotorPower(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
