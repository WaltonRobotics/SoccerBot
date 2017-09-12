package org.usfirst.frc2974.SoccerBot.subsystems;

import org.usfirst.frc2974.SoccerBot.RobotMap;
import org.usfirst.frc2974.SoccerBot.commands.Kick;
import org.usfirst.frc2974.SoccerBot.commands.NewKickSequence;
import org.usfirst.frc2974.SoccerBot.commands.Retract;

import edu.wpi.first.wpilibj.DigitalInput;
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
	private final DigitalInput limitSwitchForward = RobotMap.limitSwitchForward;
	private final DigitalInput limitSwitchBackward = RobotMap.limitSwitchBackward;
	
	LatchPosition latchPosition;
	//private Kick kickCommand = new Kick();
	
	public void initDefaultCommand() {
		setDefaultCommand(new NewKickSequence());
	}
	
	public enum LatchPosition {
		latched, unlatched
	}

	public enum Position {
		extended, retracted, dontKnow
	}
	
	public void setRetract(boolean bool) {
		if (bool) {
			kickerLeft.set(false);
			kickerRight.set(false);
		}
		retract.set(bool);
	}

	@Deprecated
	public void startKick() {
		new Kick().start();
	}
	
	public void setLatch(LatchPosition lp) {
		switch (lp) {
		case latched:
			latch.set(false);
			latchPosition = LatchPosition.latched;
			break;
		case unlatched:
			latch.set(true);
			latchPosition = LatchPosition.unlatched;
			break;
		}
	}

	public void setOff() {
		kickerLeft.set(false);
		kickerRight.set(false);
		retract.set(false);
		latch.set(false);
	}

	public void setCharge1() {
		kickerLeft.set(true);
	}

	public void setCharge2() {
		kickerLeft.set(true);
		kickerRight.set(true);
	}

	public void deactivateKickPistons() {
		kickerLeft.set(false);
		kickerRight.set(false);
	}

	public LatchPosition getLatchState() {
		return latchPosition;
	}

	public Position getPosition() {
		if (!limitSwitchForward.get()) 
			return Position.extended;
		
		if (!limitSwitchBackward.get()) 
			return Position.retracted;
			
		return Position.dontKnow;
		
	}



}
