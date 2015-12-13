package org.usfirst.frc2974.SoccerBot.commands;

import org.usfirst.frc2974.SoccerBot.Robot;

import org.usfirst.frc2974.SoccerBot.subsystems.Kicker.LatchPosition;
import org.usfirst.frc2974.SoccerBot.subsystems.Kicker.Position;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


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
    	//Robot.kicker.setLatch(LatchPosition.unlatched);
    	changeState(KickState.initCycle);
    }
    
    // Called repeatedly when this Command is scheduled to run
    // when button (in OI) is pushed, kicker retracts 
    Double time;
    KickState state;
    boolean stateInitialized = false;
	public enum KickState {
		dangling, retracted, charged, initCycle, pickupCycle, latchCycle, waitForRetract

	}
	public void changeState(KickState ks)
	{	time = timeSinceInitialized();
		state = ks;
		stateInitialized =false;
		SmartDashboard.putString("kick state", ks.toString());
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
    		{
    			Robot.kicker.setCharge1();
    			changeState(KickState.charged);
    		}
				
			if(Robot.oi.readyButton2.get())
			{
				Robot.kicker.setCharge2();
				changeState(KickState.charged);
			}
				
    		
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
    			Robot.kicker.startKick();
    		
    	break;
    	}
    	case initCycle:
    	{
    		if(!stateInitialized)
    		{
    			Robot.kicker.setOff();
    			stateInitialized = true;
    		}
    		else if(timeSinceInitialized()-time>.25)
    		{
    			Robot.kicker.setLatch(LatchPosition.unlatched);
    			changeState(KickState.dangling);
    		}
    			
    	break;
    	}
    	case pickupCycle:
    	{
    		if(!stateInitialized)
    		{
    			Robot.kicker.setLatch(LatchPosition.unlatched);
    			stateInitialized = true;
    		}
    		else if(timeSinceInitialized()-time>.25)
    		{
    			Robot.kicker.setRetract(true);
    			changeState(KickState.waitForRetract);
    		}
    	break;
    	}
    	case waitForRetract:
    	{
    		if (Robot.kicker.getPosition()==Position.retracted || Robot.oi.retractButton.get()) 
    		{
    			changeState(KickState.latchCycle);
    		}//todo timeout- go back to dangling?
    	
    	break;
    	}
    	case latchCycle:
    	{
    		if(!stateInitialized)
    		{
    			Robot.kicker.setLatch(LatchPosition.latched);
    			stateInitialized = true;
    		}
    		else if(timeSinceInitialized()-time>.25)
    		{
    			Robot.kicker.setRetract(false);
    			changeState(KickState.retracted);
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
