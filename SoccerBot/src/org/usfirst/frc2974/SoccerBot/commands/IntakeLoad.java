package org.usfirst.frc2974.SoccerBot.commands;

import org.usfirst.frc2974.SoccerBot.Robot;
import org.usfirst.frc2974.SoccerBot.RobotMap;
import org.usfirst.frc2974.SoccerBot.subsystems.Intake.ArmMovement;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class IntakeLoad extends Command {
	private final AnalogPotentiometer angleSensor = RobotMap.intakeAngleSensor;
    public IntakeLoad() {
        requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	SmartDashboard.putNumber("angle", 0);
    	SmartDashboard.putNumber("offset", .01);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("analog pot",angleSensor.get());
    	Robot.intake.setMotorPower(1);
    	if(Math.abs(angleSensor.get()-SmartDashboard.getNumber("offset"))>SmartDashboard.getNumber("angle"))
    	{
    		Robot.intake.setArmMovement(ArmMovement.up);
    	}
    	else if(Math.abs(angleSensor.get()-SmartDashboard.getNumber("offset"))<SmartDashboard.getNumber("angle"))
    	{
    		Robot.intake.setArmMovement(ArmMovement.fall);
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
