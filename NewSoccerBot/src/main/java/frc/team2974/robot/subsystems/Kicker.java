package frc.team2974.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team2974.robot.RobotMap;
import frc.team2974.robot.commands.Kick;
import frc.team2974.robot.commands.NewKickSequence;

/**
 * Subsystem used by NewKickSequence
 */
public class Kicker extends Subsystem {

  private final Solenoid kickerLeft = RobotMap.kickerLeft;
  private final Solenoid kickerRight = RobotMap.kickerRight;
  private final Solenoid retract = RobotMap.retract;
  private final Solenoid latch = RobotMap.latch;
  private final DigitalInput limitSwitchForward = RobotMap.limitSwitchForward;
  private final DigitalInput limitSwitchBackward = RobotMap.limitSwitchBackward;
  private LatchPosition latchPosition;
  private boolean fullCharge = true;
  private double timer;
  // private Kick kickCommand = new Kick();

  public final void initDefaultCommand() {
    setDefaultCommand(new NewKickSequence());
  }

  public final void setRetract(boolean bool) {
    if (bool) {
      kickerLeft.set(false);
      kickerRight.set(false);
    }
    retract.set(bool);
  }

  @Deprecated
  public static final void startKick() {
    new Kick().start();
  }

  public final void setLatch(LatchPosition lp) {
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

  public final void setOff() {
    kickerLeft.set(false);
    kickerRight.set(false);
    retract.set(false);
    latch.set(false);
  }

  public final void setCharge1() {
    kickerLeft.set(true);
  }

  public final void setCharge2() {
    kickerLeft.set(true);
    kickerRight.set(true);
  }

  public final void startCharge() {
    if (fullCharge) {
      kickerLeft.set(true);
      kickerRight.set(true);
    } else {
      kickerLeft.set(true);
      kickerRight.set(false);
    }

  }

  public final void deactivateKickPistons() {
    kickerLeft.set(false);
    kickerRight.set(false);
  }

  public final LatchPosition getLatchState() {
    return latchPosition;
  }

  public final Position getPosition() {
    if (!limitSwitchForward.get()) {
      return Position.extended;
    }

    if (!limitSwitchBackward.get()) {
      return Position.retracted;
    }

    return Position.dontKnow;

  }

  public final boolean getCharge() {
    return fullCharge;
  }

  public final void setCharge(boolean charge) {
    fullCharge = charge;
  }

  public final void startTimer() {
    timer = Timer.getFPGATimestamp();
  }

  public final double getTimeSinceStart() {
    return Timer.getFPGATimestamp() - timer;
  }

  public enum LatchPosition {
    latched, unlatched
  }

  public enum Position {
    extended, retracted, dontKnow
  }

}
