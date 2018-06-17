package org.usfirst.frc2974.SoccerBot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc2974.SoccerBot.Robot;
import org.usfirst.frc2974.SoccerBot.subsystems.AbstractIntake.ArmPosition;
import org.usfirst.frc2974.SoccerBot.subsystems.Kicker;

//import org.usfirst.frc2974.SoccerBot.RobotMap;
//import org.usfirst.frc2974.SoccerBot.subsystems.Intake.ArmMovement;
//import org.usfirst.frc2974.SoccerBot.subsystems.Intake;

/**
 *
 */
@Deprecated
public class Kick extends Command {

	final private double timeout = 3;
	State state;
	boolean isFinishedFlag;
	double timeSinceFlat;
	//TODO add enum 2 states
	public Kick() {
		requires(Robot.kicker);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		state = State.notInitialized;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		switch (state) {
			case notInitialized:
				Robot.intake.startFlat();
				state = State.notFlat;
				break;
			case notFlat:
				if (Robot.intake.getArmPosition() == ArmPosition.flat) {
					state = State.flat;
					timeSinceFlat = timeSinceInitialized();
				}
				break;
			case flat:
				Robot.kicker.setLatch(Kicker.LatchPosition.unlatched);
				if (Robot.kicker.getPosition() == Kicker.Position.extended
					|| timeSinceInitialized() - timeSinceFlat > timeout) {
					state = State.finished;
				}

		}

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {

		return state == State.finished;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.kicker.setOff();
		Robot.intake.endFlat();

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}

	private enum State {
		notInitialized, notFlat, flat, finished
	}
}
