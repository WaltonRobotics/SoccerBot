package org.usfirst.frc2974.SoccerBot.commands;

import org.usfirst.frc2974.SoccerBot.Robot;
import org.usfirst.frc2974.SoccerBot.subsystems.AbstractIntake.ArmPosition;
//import org.usfirst.frc2974.SoccerBot.RobotMap;
//import org.usfirst.frc2974.SoccerBot.subsystems.Intake.ArmMovement;
//import org.usfirst.frc2974.SoccerBot.subsystems.Intake;
import org.usfirst.frc2974.SoccerBot.subsystems.Kicker;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Kick extends Command {
//TODO add enum 2 states
	public Kick() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.kicker);
	}
	State state;
	boolean isFinishedFlag;
	double timeSinceFlat;

	// Called just before this Command runs the first time
	protected void initialize() {
		state = State.notFlat;
	}
	private enum State
	{
		notInitialized, notFlat, flat, finished
	}
	


	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		switch(state)
		{
		case notInitialized:
			Robot.intake.startFlat();
			state = State.notFlat;
			break;
		case notFlat:
			if(Robot.intake.getArmPosition() == ArmPosition.flat)
			{
				state = State.flat;
				timeSinceFlat = timeSinceInitialized();
			}
			break;
		case flat:
			Robot.kicker.setLatch(Kicker.LatchPosition.unlatched);
			if(Robot.kicker.getPosition() == Kicker.Position.extended || timeSinceInitialized()-timeSinceFlat> 8)
				state = State.finished;

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
}
