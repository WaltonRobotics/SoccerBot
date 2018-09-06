package frc.team2974.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2974.robot.Gamepad.POV;
import frc.team2974.robot.Robot;
import frc.team2974.robot.subsystems.AbstractIntake.ArmMovement;
import frc.team2974.robot.subsystems.AbstractIntake.ArmPosition;

/**
 * Makes the intake go to the flat position.
 */
public class IntakeFlat extends Command {

  public IntakeFlat() {
    requires(Robot.intake);
  }

  // Called just before this Command runs the first time
  protected final void initialize() {
    Robot.intake.setMotorPower(0);
    Robot.intake.setArmMovement(ArmMovement.fall);

  }

  // Called repeatedly when this Command is scheduled to run
  protected final void execute() {
    //todo display value on smartdashbord to show how much the arm has fallen
    if (Robot.oi.dribbleButton.get()) {
      new IntakeDribble().start();
    }
    if (Robot.oi.loadButton.get()) {
      new IntakeLoad().start();
    }
    if (Robot.oi.xbox.getPOVButton(POV.N) || Robot.oi.xbox.getPOVButton(POV.S)) {
      new IntakeManual().start();
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  protected final boolean isFinished() {
    return Robot.intake.getArmPosition() == ArmPosition.flat;
  }

  public void endFlat() {
  }

  // Called once after isFinished returns true
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  protected void interrupted() {
  }
}
