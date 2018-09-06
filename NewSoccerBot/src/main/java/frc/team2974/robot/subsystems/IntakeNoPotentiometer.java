package frc.team2974.robot.subsystems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Uses a timer to estimate when the intake is flat, unreliable
 */
public class IntakeNoPotentiometer extends AbstractIntake {

  private static final double fallTime = 4;
  /*
   * (non-Javadoc)
   *
   * @see frc.team2974.robot.subsystems.AbstractIntake#getArmPosition()
   */
  private final Timer fallTimer = new Timer();

  public IntakeNoPotentiometer() {
    fallTimer.start();
  }

  @Override
  public final void setArmMovement(ArmMovement move) {
    fallTimer.reset();
    fallTimer.start();
    super.setArmMovement(move);
  }


  @Override
  public final ArmPosition getArmPosition() {
    SmartDashboard.putNumber("Fall Timer", fallTimer.get());
    if (fallTimer.get() >= fallTime) {
      return ArmPosition.flat;
    }
    return ArmPosition.up;
  }

}
