package frc.team2974.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2974.robot.Robot;
import frc.team2974.robot.subsystems.AbstractIntake;
import frc.team2974.robot.subsystems.AbstractIntake.ArmPosition;
import frc.team2974.robot.subsystems.Kicker;
import frc.team2974.robot.subsystems.Kicker.LatchPosition;

//import frc.team2974.robot.RobotMap;
//import frc.team2974.robot.subsystems.Intake.ArmMovement;
//import frc.team2974.robot.subsystems.Intake;

/**
 *
 */
@Deprecated
public class Kick extends Command {

  private static final double timeout = 3;
  private State state;
  boolean isFinishedFlag;
  private double timeSinceFlat;

  //TODO add enum 2 states
  public Kick() {
    requires(Robot.kicker);
  }

  // Called just before this Command runs the first time
  protected final void initialize() {
    state = State.notInitialized;
  }

  // Called repeatedly when this Command is scheduled to run
  protected final void execute() {
    switch (state) {
      case notInitialized:
        AbstractIntake.startFlat();
        state = State.notFlat;
        break;
      case notFlat:
        if (Robot.intake.getArmPosition() == ArmPosition.flat) {
          state = State.flat;
          timeSinceFlat = timeSinceInitialized();
        }
        break;
      case flat:
        Robot.kicker.setLatch(LatchPosition.unlatched);
        if ((Robot.kicker.getPosition() == Kicker.Position.extended)
            || ((timeSinceInitialized() - timeSinceFlat) > timeout)) {
          state = State.finished;
        }

    }

  }

  // Make this return true when this Command no longer needs to run execute()
  protected final boolean isFinished() {

    return state == State.finished;
  }

  // Called once after isFinished returns true
  protected final void end() {
    Robot.kicker.setOff();
    AbstractIntake.endFlat();

  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  protected void interrupted() {
  }

  private enum State {
    notInitialized, notFlat, flat, finished
  }
}
