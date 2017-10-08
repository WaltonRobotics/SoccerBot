package org.usfirst.frc2974.SoccerBot.commands;

import org.usfirst.frc2974.SoccerBot.Gamepad.POV;
import org.usfirst.frc2974.SoccerBot.Robot;
import org.usfirst.frc2974.SoccerBot.RobotMap;
import org.usfirst.frc2974.SoccerBot.subsystems.AbstractIntake.ArmMovement;
import org.usfirst.frc2974.SoccerBot.subsystems.AbstractIntake.ArmPosition;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * controls loading of the soccer bot
 */
public class IntakeLoad extends Command {
	private final AnalogPotentiometer angleSensor = RobotMap.intakeAngleSensor;

	public IntakeLoad() {
		requires(Robot.intake);
	}

	// Called just before this Command runs the first time
	// sets the required and acceptable angles of loader
	protected void initialize() {
		Robot.intake.setMotorPower(-.35);
		if (Robot.intake.getArmPosition() == ArmPosition.up || Robot.intake.getArmPosition() == ArmPosition.high) {
			Robot.intake.setArmMovement(ArmMovement.fall);
		} else if (Robot.intake.getArmPosition() == ArmPosition.low
				|| Robot.intake.getArmPosition() == ArmPosition.flat) {
			Robot.intake.setArmMovement(ArmMovement.up);
		}
	}

	// Called repeatedly when this Command is scheduled to run
	// controls the angle and offset of loading arm
	protected void execute() {
		if (Robot.oi.xbox.getPOVButton(POV.N) || Robot.oi.xbox.getPOVButton(POV.S)) {
			new IntakeManual().start();
		}
		if (Robot.oi.dribbleButton.get()) {
			new IntakeDribble().start();
		}
		if (Robot.oi.flatButton.get()) {
			new IntakeFlat().start();
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.intake.getArmPosition() == ArmPosition.dribble;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
