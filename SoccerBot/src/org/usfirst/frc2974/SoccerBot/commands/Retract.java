package org.usfirst.frc2974.SoccerBot.commands;

import org.usfirst.frc2974.SoccerBot.Robot;

import org.usfirst.frc2974.SoccerBot.subsystems.Kicker.LatchPosition;



import edu.wpi.first.wpilibj.command.Command;


/**
 *when retract button is pressed, kicker retracts
 */
public class Retract extends Command {

    public Retract() {
        requires(Robot.kicker);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.kicker.setOff();
    	Robot.kicker.setLatch(LatchPosition.unlatched);
    	changeState(KickState.dangling);
    }
    
    // Called repeatedly when this Command is scheduled to run
    // when button (in OI) is pushed, kicker retracts 
    Double time;
    KickState state;
    boolean flag = false;
	public enum KickState {
		dangling, retracted, charged, initCycle, pickupCycle, latchCycle

	}
	public void changeState(KickState ks)
	{	time = timeSinceInitialized();
		state = ks;
		flag =false;
	}
    protected void execute() {
    	switch(state)
    	{
    	case dangling:
    	{
    		if(timeSinceInitialized()-time>1)
    		{
    			changeState(KickState.pickupCycle);
    		}
    	break;
    	}
    	case retracted:
    	{
    		if(Robot.oi.readyButton1.get())
				Robot.kicker.setCharge1();
			if(Robot.oi.readyButton2.get())
				Robot.kicker.setCharge2();
    		
    	break;
    	}
    	case charged:
    	{
    		if(Robot.oi.discharge.get())
    		{
    			Robot.kicker.deactivateKickPistons();
    			changeState(KickState.retracted);
    		}
    			
    		if(Robot.oi.kickButton.get())
    			new Kick().start();
    		
    	break;
    	}
    	case initCycle:
    	{
    		if(!flag)
    		{
    			Robot.kicker.setOff();
    			flag = true;
    		}
    		else if(timeSinceInitialized()-time>.25)
    		{
    			Robot.kicker.setLatch(LatchPosition.unlatched);
    			changeState(KickState.pickupCycle);
    		}
    			
    	break;
    	}
    	case pickupCycle:
    	{
    		if(!flag)
    		{
    			Robot.kicker.setLatch(LatchPosition.unlatched);
    			flag = true;
    		}
    		else if(timeSinceInitialized()-time>.25)
    		{
    			Robot.kicker.setRetract(true);
    			changeState(KickState.latchCycle);
    		}
    	break;
    	}
    	case latchCycle:
    	{
    		if(!flag)
    		{
    			Robot.kicker.setLatch(LatchPosition.latched);
    			flag = true;
    		}
    		else if(timeSinceInitialized()-time>.25)
    		{
    			Robot.kicker.setRetract(false);
    		}
    		
    	break;
    	}
    	
    	}
    	
     }

    // Make this return true when this Command no longer needs to run execute()
    // limits retraction, tells when kicker reaches limit
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
