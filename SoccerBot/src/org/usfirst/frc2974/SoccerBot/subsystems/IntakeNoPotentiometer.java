/**
 * 
 */
package org.usfirst.frc2974.SoccerBot.subsystems;

import org.usfirst.frc2974.SoccerBot.subsystems.AbstractIntake.ArmMovement;

import edu.wpi.first.wpilibj.Timer;

/**
 * @author tfs
 *
 */
public class IntakeNoPotentiometer extends AbstractIntake {
	private final double fallTime = 4;
	/* (non-Javadoc)
	 * @see org.usfirst.frc2974.SoccerBot.subsystems.AbstractIntake#getArmPosition()
	 */
	private Timer fallTimer = new Timer();
	
	public IntakeNoPotentiometer() {
		fallTimer.start();
	}
	
	@Override
	public void setArmMovement(ArmMovement move) {
		fallTimer.reset();
		super.setArmMovement(move);
	}
	
	
	@Override
	public ArmPosition getArmPosition() {
		if(fallTimer.get() >= fallTime){
			return ArmPosition.flat;
		}
		return ArmPosition.up;
	}

}
