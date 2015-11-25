package org.usfirst.frc2974.SoccerBot.commands;

import org.usfirst.frc2974.SoccerBot.Robot;
import org.usfirst.frc2974.SoccerBot.RobotMap;
import org.usfirst.frc2974.SoccerBot.subsystems.Intake.ArmMovement;
import org.usfirst.frc2974.SoccerBot.subsystems.Intake;
import org.usfirst.frc2974.SoccerBot.subsystems.Kicker;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Kick extends Command {

    public Kick() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.kicker);
        requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	isFinishedFlag = false;
    }
    boolean isFinishedFlag;
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.intake.setArmMovement(Intake.ArmMovement.fall);
    	Robot.kicker.setLatch(Kicker.LatchPosition.unlatched);
    	if(Robot.kicker.getPosition() == Kicker.Position.extended)
    		isFinishedFlag = true;
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
    	return Robot.kicker.getPosition() == Kicker.Position.extended;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.kicker.deactivateKickPistons();
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
