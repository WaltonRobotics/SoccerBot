package org.usfirst.frc2974.SoccerBot.commands;

import org.usfirst.frc2974.SoccerBot.Robot;
import org.usfirst.frc2974.SoccerBot.RobotMap;
import org.usfirst.frc2974.SoccerBot.subsystems.Intake.ArmMovement;

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
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.oi.kickButton.get())
    	{
    		Robot.intake.setArmMovement(ArmMovement.fall);
    		try {
				wait(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		Robot.kicker.kick();
    	}
    	
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
    	return !RobotMap.limitSwitchForward.get();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.kicker.retract();
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
