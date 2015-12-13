package org.usfirst.frc2974.SoccerBot.commands;

import org.usfirst.frc2974.SoccerBot.Robot;
import org.usfirst.frc2974.SoccerBot.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeFlat extends Command {
	
    public IntakeFlat() {
        requires(Robot.intake); 
    }

    // Called just before this Command runs the first time
    protected void initialize() {
       	Robot.intake.setMotorPower(0);
    	Robot.intake.setArmMovement(Intake.ArmMovement.fall);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//todo display value on smartdashbord to show how much the arm has fallen
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
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
