package org.usfirst.frc2974.SoccerBot.subsystems;

import org.usfirst.frc2974.SoccerBot.RobotMap;
import org.usfirst.frc2974.SoccerBot.commands.Kick;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Kicker extends Subsystem {
	
	private final Solenoid kickerLeft = RobotMap.kickerLeft;
	private final Solenoid kickerRight = RobotMap.kickerRight;
	private final Solenoid retract = RobotMap.retract;
	private final Solenoid latch = RobotMap.latch;
	
	KickPosition action;
    public void initDefaultCommand() {
        
        setDefaultCommand(new Kick());
        retract();
        action = KickPosition.retract;
        
    }
    
    public enum KickPosition
    {
    	shoot, ready, retract
    }
 
    public void retract()
    {
    	if(!latch.get())
    		latch.set(true);
    	if(kickerLeft.get())
    		kickerLeft.set(false);
    	if(kickerRight.get())
    		kickerRight.set(false);
    	if(!retract.get())
    		retract.set(true);
    	
    	try {
			wait(250);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	latch.set(false);
    
    	try {
			wait(250);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	retract.set(false);
    	action = KickPosition.retract;
    }
    
    public void getReady(int num)
    {
    	if(action!= KickPosition.retract)
    		retract();
    	if(!kickerLeft.get())
    		kickerLeft.set(true);
    	if(kickerRight.get()!=(num==2))
    		kickerRight.set(num == 2);
    	
    	action = KickPosition.ready;
    }
    
    public void kick()
    {
    	if(action!= KickPosition.ready)
    		getReady(1);
    	
    	latch.set(true);
    	
    }
    
}

