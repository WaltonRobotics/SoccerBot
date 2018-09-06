package frc.team2974.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team2974.robot.Robot;
import frc.team2974.robot.subsystems.Kicker;
import frc.team2974.robot.subsystems.Kicker.LatchPosition;
import frc.team2974.robot.subsystems.Kicker.Position;


/**
 * when retract button is pressed, kicker retracts
 */
@Deprecated
public class Retract extends Command {

  // Called repeatedly when this Command is scheduled to run
  // when button (in OI) is pushed, kicker retracts
  private Double time;
  private KickState state;
  private boolean stateInitialized = false;

  public Retract() {
    requires(Robot.kicker);
  }

  // Called just before this Command runs the first time
  protected final void initialize() {
    Robot.kicker.setOff();
    //Robot.kicker.setLatch(LatchPosition.unlatched);
    changeState(KickState.initCycle);
  }

  private void changeState(KickState ks) {
    time = timeSinceInitialized();
    state = ks;
    stateInitialized = false;
    SmartDashboard.putString("kick state", ks.toString());
  }

  protected final void execute() {
    switch (state) {
      case dangling: {
        if ((timeSinceInitialized() - time) > 1) {
          changeState(KickState.pickupCycle);
        }
        break;
      }
      case retracted: {
//    		if(Robot.oi.readyButton1.get())
//    		{
//    			Robot.kicker.setCharge1();
//    			changeState(KickState.charged);
//    		}
//
//			if(Robot.oi.readyButton2.get())
//			{
//				Robot.kicker.setCharge2();
//				changeState(KickState.charged);
//			}

        break;
      }
      case charged: {
        if (Robot.oi.discharge.get()) {
          Robot.kicker.deactivateKickPistons();
          changeState(KickState.retracted);
        }

        if (Robot.oi.kickButton.get()) {
          Kicker.startKick();
        }

        break;
      }
      case initCycle: {
        if (!stateInitialized) {
          Robot.kicker.setOff();
          stateInitialized = true;
        } else if ((timeSinceInitialized() - time) > 0.25) {
          Robot.kicker.setLatch(LatchPosition.unlatched);
          changeState(KickState.dangling);
        }

        break;
      }
      case pickupCycle: {
        if (!stateInitialized) {
          Robot.kicker.setLatch(LatchPosition.unlatched);
          stateInitialized = true;
        } else if ((timeSinceInitialized() - time) > 0.25) {
          Robot.kicker.setRetract(true);
          changeState(KickState.waitForRetract);
        }
        break;
      }
      case waitForRetract: {
        if (Robot.kicker.getPosition() == Position.retracted /*|| Robot.oi.retractButton.get()*/) {
          changeState(KickState.latchCycle);
        }//todo timeout- go back to dangling?

        break;
      }
      case latchCycle: {
        if (!stateInitialized) {
          Robot.kicker.setLatch(LatchPosition.latched);
          stateInitialized = true;
        } else if ((timeSinceInitialized() - time) > 0.25) {
          Robot.kicker.setRetract(false);
          changeState(KickState.retracted);
        }

        break;
      }

    }

  }

  // Make this return true when this Command no longer needs to run execute()
  // limits retraction, tells when kicker reaches limit
  protected final boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  protected void interrupted() {
  }

  public enum KickState {
    dangling, retracted, charged, initCycle, pickupCycle, latchCycle, waitForRetract

  }
}
