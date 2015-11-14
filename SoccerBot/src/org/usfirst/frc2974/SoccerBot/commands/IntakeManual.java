package org.usfirst.frc2974.SoccerBot.commands;

import org.usfirst.frc2974.SoccerBot.Robot;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc2974.SoccerBot.subsystems.*;;
/**
 *
 */
public class IntakeManual extends Command {

    public IntakeManual() {
    	super();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
 protected void execute() {
    	
    	boolean isPressed = Robot.oi.joystickLeft.getRawButton(BUTTON_UP);
    	
    	switch (Robot.intake.getAction())
    	{
    	case up:
    		if(!isPressed)
    		break;
    		
    	case fall:
    		if(isPressed)
    		break;
    	
    	if(Robot.oi.joystickLeft.getRawButton(BUTTON_UP))
    	{
    		Robot.intake.setArmMovement(Intake.ArmMovement.up);
    	}
    	else
    	{
    		Robot.intake.setArmMovement(Intake.ArmMovement.fall);
    	}
    	
    	
    	}
    	
    	
    }


    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
