package frc.team2974.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2974.robot.Robot;

public class ShowInputs extends Command {

  public ShowInputs() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.inputs);
  }

  // Called just before this Command runs the first time
  protected final void initialize() {
    Robot.inputs.updateSmartDashboard();
  }

  // Called repeatedly when this Command is scheduled to run
  protected final void execute() {
    Robot.inputs.updateSmartDashboard();
    //Robot.forklift.updateSmartDashboard();
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
    Robot.inputs.updateSmartDashboard();
  }
}
