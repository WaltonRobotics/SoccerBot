package org.usfirst.frc2974.SoccerBot.subsystems;

import org.usfirst.frc2974.SoccerBot.RobotMap;
import org.usfirst.frc2974.SoccerBot.commands.Kick;
import org.usfirst.frc2974.SoccerBot.commands.Retract;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Kicker extends Subsystem {
	
	private final Solenoid kickerLeft = RobotMap.kickerLeft;
	private final Solenoid kickerRight = RobotMap.kickerRight;
	private final Solenoid retract = RobotMap.retract;
	private final Solenoid latch = RobotMap.latch;
	private final  DigitalInput limitSwitchForward = RobotMap.limitSwitchForward;
	private final  DigitalInput limitSwitchBackward = RobotMap.limitSwitchBackward;
	Timer timer = new Timer(); 
	Double time;
	LatchPosition latchPosition;
	
	
	//KickPosition action;
	
    public void initDefaultCommand() {
        
        setDefaultCommand(new Retract());
        //retract();
        //action = KickPosition.retract;
        
    }
    
    public void setRetract(boolean bool)
    {
    	if(bool)
    	{
    		kickerLeft.set(false);
    		kickerRight.set(false);
    	}
    	retract.set(bool);
    }

    public void setLatch(LatchPosition lp)
    {
    	switch (lp){
    	case latched :
    		latch.set(false);
    		timer.reset();
    		timer.start();
    		time = timer.get();
    		latchPosition = LatchPosition.latched;
    		break;
    	case unlatched :
    		latch.set(true);
    		latchPosition = LatchPosition.unlatched;
    		break;
    	}
    }
    
    public enum LatchPosition
    {
    	latched, unlatched, waiting
    }
    
    public void setOff()
    {
    	kickerLeft.set(false);
    	kickerRight.set(false);
    	retract.set(false);
    	latch.set(false);
    }
    
    public void setCharge1()
    {
    	kickerLeft.set(true); 
    }
    
    public void setCharge2()
    {
    	kickerLeft.set(true);
    	kickerRight.set(true);
    }
    
    public void deactivateKickPistons()
    {
    	kickerLeft.set(false);
    	kickerRight.set(false);
    }
    
    public LatchPosition getLatchState()
    {
    	if(latchPosition == LatchPosition.latched)
    	{
    		if(time>= .25)
    			return LatchPosition.latched;
    		else
    			return LatchPosition.waiting;	
    	}
    	else
    	{
    		return LatchPosition.unlatched;
    	}
    	
    }
    public Position getPosition()
    {
    	if (!limitSwitchForward.get()){
    		return Position.extended;
    		
    	}
    	
    	if (!limitSwitchBackward.get()){
    		return Position.retracted;
    		
    	}
    	else {
    		return Position.dontKnow;
    	}
    }
    
    public enum Position
    {
    	extended, retracted, dontKnow
    }
    
//    public enum KickPosition
//    {
//    	shoot, ready, retract
//    }
// 
//    public void retract()
//    {
//    	if(!latch.get())
//    		latch.set(true);
//    	if(kickerLeft.get())
//    		kickerLeft.set(false);
//    	if(kickerRight.get())
//    		kickerRight.set(false);
//    	if(!retract.get())
//    		retract.set(true);
//    	
//    	try {
//			wait(250);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	
//    	latch.set(false);
//    
//    	try {
//			wait(250);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	
//    	retract.set(false);
//    	action = KickPosition.retract;
//    }
//    
//    public void getReady(int num)
//    {
//    	if(action!= KickPosition.retract)
//    		retract();
//    	if(!kickerLeft.get())
//    		kickerLeft.set(true);
//    	if(kickerRight.get()!=(num==2))
//    		kickerRight.set(num == 2);
//    	
//    	action = KickPosition.ready;
//    }
//    
//    public void kick()
//    {
//    	if(action!= KickPosition.ready)
//    		getReady(1);
//    	
//    	latch.set(true);
//    	
//    }
    
}

