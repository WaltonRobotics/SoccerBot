/**
 * 
 */
package org.usfirst.frc2974.SoccerBot.subsystems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Uses a timer to estimate when the intake is flat, unreliable
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
		fallTimer.start();
		super.setArmMovement(move);
	}
	
	
	@Override
	public ArmPosition getArmPosition() {
		SmartDashboard.putNumber("Fall Timer", fallTimer.get());
		if(fallTimer.get() >= fallTime){
			return ArmPosition.flat;
		}
		return ArmPosition.up;
	}

}
