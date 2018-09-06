package frc.team2974.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2974.robot.Gamepad;
import frc.team2974.robot.Gamepad.POV;
import frc.team2974.robot.Robot;
import frc.team2974.robot.subsystems.AbstractIntake;
import frc.team2974.robot.subsystems.Intake;

/**
 * Moves the intake based off of the D-Pad
 */
public class IntakeManual extends Command {

  public IntakeManual() {

    requires(Robot.intake);
  }

  // Called just before this Command runs the first time

  /**
   * Called just before this Command runs the first time
   */
  protected final void initialize() {
    Robot.intake.setArmMovement(AbstractIntake.ArmMovement.block);
  }

  // Called repeatedly when this Command is scheduled to run

  /**
   * Runs the robot. Moves the loader up and down. Also runs the loaders TALON motors.
   */
  protected final void execute() {

    if (Robot.oi.dribbleButton.get()) {
      new IntakeDribble().start();
    }

    if (Robot.oi.loadButton.get()) {
      new IntakeLoad().start();
    }

    if (Robot.oi.flatButton.get()) {
      new IntakeFlat().start();
    }

    boolean isUpPressed = Robot.oi.xbox.getPOVButton(POV.N);
    boolean isFallPressed = Robot.oi.xbox.getPOVButton(POV.S);

    switch (Robot.intake.getAction()) {
      // if not pressed, block
      case up:
        if (!isUpPressed) {
          Robot.intake.setArmMovement(AbstractIntake.ArmMovement.block);
        }
        break;
      // if not pressed, block
      case fall:
        if (!isFallPressed) {
          Robot.intake.setArmMovement(AbstractIntake.ArmMovement.block);
        }
        break;
      // if pressed and in block, go up or down
      case block:
        if (isUpPressed) {
          Robot.intake.setArmMovement(AbstractIntake.ArmMovement.up);
        } else if (isFallPressed) {
          Robot.intake.setArmMovement(AbstractIntake.ArmMovement.fall);
        }
        break;
    }

    // if(Robot.oi.xbox.getLeftTrigger()>.01)
    // Robot.intake.setMotorPower(-.35);
    //
    // else if(Robot.oi.xbox.getRightTrigger()>.01)
    // Robot.intake.setMotorPower(Robot.oi.xbox.getRightTrigger());
    //
    // else
    // Robot.intake.setMotorPower(0);
  }

  // Make this return true when this Command no longer needs to run execute()
  protected final boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  protected final void interrupted() {
    end();
  }
}
