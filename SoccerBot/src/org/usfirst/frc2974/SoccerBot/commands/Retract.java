package org.usfirst.frc2974.SoccerBot.commands;

import org.usfirst.frc2974.SoccerBot.Robot;
import org.usfirst.frc2974.SoccerBot.RobotMap;
import org.usfirst.frc2974.SoccerBot.subsystems.Kicker;
import org.usfirst.frc2974.SoccerBot.subsystems.Kicker.LatchPosition;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Retract extends Command {

    public Retract() {
        requires(Robot.kicker);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.kicker.setOff();
    	Robot.kicker.setLatch(LatchPosition.latched);
    }
    Boolean isBeingRetracted = false;

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.oi.retractButton.get())
    	{
    		if(Robot.kicker.getLatchState() == Kicker.LatchPosition.latched)
    		{
    			Robot.kicker.setLatch(Kicker.LatchPosition.unlatched);
    		}
    		if(Robot.kicker.getLatchState() != Kicker.LatchPosition.latched)
    		{
    			Robot.kicker.setRetract(true);
    			isBeingRetracted = true;
    		}
    		   		
    	}
    	if(isBeingRetracted && Robot.kicker.getPosition() == Kicker.Position.retracted)
    	{
    		Robot.kicker.setRetract(false);
    		isBeingRetracted = false;
    		Robot.kicker.setLatch(Kicker.LatchPosition.latched);
    	}
    	
    	if(Robot.oi.readyButton1.get()||Robot.oi.readyButton2.get())
    	{
    		
    		if(Robot.kicker.getLatchState() == Kicker.LatchPosition.latched && Robot.kicker.getPosition() == Kicker.Position.retracted )
    		{
    			if(Robot.oi.readyButton1.get())
    				Robot.kicker.setCharge1();
    			if(Robot.oi.readyButton2.get())
    				Robot.kicker.setCharge2();
    		}
    		
    	}
    	if(Robot.oi.kickButton.get())
    	{
    		Kick kick = new Kick();
    		kick.start();
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
