package org.usfirst.frc2974.SoccerBot.commands;

import org.usfirst.frc2974.SoccerBot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *checks if bot is ready for a kick
 */
public class GetReady extends Command {

    public GetReady() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.kicker);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    // asks if ready button is hit, outputs a response in code
    protected void execute() {
    	
    	if(Robot.oi.readyButton1.get()||Robot.oi.readyButton2.get())
    	{
    		Robot.kicker.getReady(Robot.oi.readyButton1.get()?1:2);
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
