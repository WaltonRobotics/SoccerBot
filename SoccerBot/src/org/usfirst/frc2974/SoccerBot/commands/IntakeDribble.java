package org.usfirst.frc2974.SoccerBot.commands;

import org.usfirst.frc2974.SoccerBot.Robot;
import org.usfirst.frc2974.SoccerBot.RobotMap;
import org.usfirst.frc2974.SoccerBot.Gamepad.POV;
import org.usfirst.frc2974.SoccerBot.subsystems.AbstractIntake.ArmMovement;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class IntakeDribble extends Command {
	private final AnalogPotentiometer angleSensor = RobotMap.intakeAngleSensor;

	public IntakeDribble() {
		requires(Robot.intake);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		SmartDashboard.putNumber("angleDribble", 700);
		SmartDashboard.putNumber("offsetDribble", 10);
		SmartDashboard.putNumber("motorSpeed", .3);
		Robot.intake.setDribbleMode();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.intake.setMotorPower(SmartDashboard.getNumber("motorSpeed"));

		switch (Robot.intake.getArmPosition()) {
		case up:
		case high: {
			Robot.intake.setArmMovement(ArmMovement.fall);
			break;
		}
		case low:
		case flat: {
			Robot.intake.setArmMovement(ArmMovement.up);
			break;
		}
		case dribble: {
			Robot.intake.setArmMovement(ArmMovement.block);
			break;
		}
		}

		if (Robot.oi.xbox.getPOVButton(POV.N) || Robot.oi.xbox.getPOVButton(POV.S)) {
			new IntakeManual().start();
		}
//		if (Robot.oi.loadButton.get()) {
//			new IntakeLoad().start();
//		}
		if (Robot.oi.flatButton.get()) {
			new IntakeFlat().start();
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
