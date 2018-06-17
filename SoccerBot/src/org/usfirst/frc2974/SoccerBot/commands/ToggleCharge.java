package org.usfirst.frc2974.SoccerBot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc2974.SoccerBot.Robot;

/**
 * Command used to toggle between a full charge and a half charge
 */
public class ToggleCharge extends Command {

	private boolean isDone;

	public ToggleCharge() {

	}

	// Called just before this Command runs the first time
	protected void initialize() {
		isDone = false;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (!isDone) {
			Robot.kicker.setCharge(!Robot.kicker.getCharge());
			isDone = true;
			SmartDashboard.putBoolean("Full kick", Robot.kicker.getCharge());
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isDone;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
