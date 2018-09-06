package frc.team2974.robot.commands;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.command.Command;
import frc.team2974.robot.Gamepad.POV;
import frc.team2974.robot.Robot;
import frc.team2974.robot.RobotMap;
import frc.team2974.robot.subsystems.AbstractIntake;
import frc.team2974.robot.subsystems.AbstractIntake.ArmMovement;
import frc.team2974.robot.subsystems.AbstractIntake.ArmPosition;
import frc.team2974.robot.subsystems.Intake;

/**
 * Makes the intake go to the dribble position, turns the motor to load a ball
 */
class IntakeLoad extends Command {

  private final AnalogPotentiometer angleSensor = RobotMap.intakeAngleSensor;
  private boolean initialized = false;

  IntakeLoad() {
    requires(Robot.intake);
  }

  // Called just before this Command runs the first time
  // sets the required and acceptable angles of loader
  protected final void initialize() {
    initialized = false;
    Robot.intake.setMotorPower(-0.35);
    if ((Robot.intake.getArmPosition() == ArmPosition.up) || (Robot.intake.getArmPosition() == ArmPosition.high)) {
      Robot.intake.setArmMovement(ArmMovement.fall);
    } else if ((Robot.intake.getArmPosition() == ArmPosition.low)
        || (Robot.intake.getArmPosition() == ArmPosition.flat)) {
      Robot.intake.setArmMovement(ArmMovement.up);
    }
  }

  // Called repeatedly when this Command is scheduled to run
  // controls the angle and offset of loading arm
  protected final void execute() {
    if (Robot.intake.getArmPosition() == ArmPosition.dribble) {
      initialized = true;
    }
    if (initialized) {
      moveIntake();
    }
    if (Robot.oi.dribbleButton.get()) {
      new IntakeDribble().start();
    }
    if (Robot.oi.flatButton.get()) {
      new IntakeFlat().start();
    }
    if (Robot.oi.loadButton.get()) {
      initialize();
    }
  }

  private static void moveIntake() {
    boolean isUpPressed = Robot.oi.xbox.getPOVButton(POV.N);
    boolean isFallPressed = Robot.oi.xbox.getPOVButton(POV.S);
    if (isUpPressed) {
      Robot.intake.setArmMovement(AbstractIntake.ArmMovement.up);
    } else if (isFallPressed) {
      Robot.intake.setArmMovement(AbstractIntake.ArmMovement.fall);
    } else {
      Robot.intake.setArmMovement(AbstractIntake.ArmMovement.block);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  protected final boolean isFinished() {
    return Robot.oi.manualButton.get();
  }

  // Called once after isFinished returns true
  protected final void end() {
    Robot.intake.setMotorPower(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  protected final void interrupted() {
    end();
  }
}
