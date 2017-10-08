// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc2974.SoccerBot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc2974.SoccerBot.commands.*;
import org.usfirst.frc2974.SoccerBot.subsystems.*;

public class Robot extends IterativeRobot {

	CameraServer server;
	
	public static OI oi;
	public static DriveTrain driveTrain;
	public static AbstractIntake intake;
	public static Inputs inputs;
	public static Kicker kicker;


	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		RobotMap.init();

		driveTrain = new DriveTrain();
		intake = new Intake();
		inputs = new Inputs();
		kicker = new Kicker();
		// OI must be constructed after subsystems. If the OI creates Commands
		// (which it very likely will), subsystems are not guaranteed to be
		// constructed yet. Thus, their requires() statements may grab null
		// pointers. Bad news. Don't move it.
		oi = new OI();
		
		//code to make camera work
//		server = CameraServer.getInstance();
//        server.setQuality(50);
//        server.startAutomaticCapture("cam0");
	}

	/**
	 * This function is called when the disabled button is hit. You can use it
	 * to reset subsystems before shutting down.
	 */
	public void disabledInit() {

	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		inputs.updateSmartDashboard();
    	SmartDashboard.putNumber("Angle sensor", RobotMap.intakeAngleSensor.get());
	}

	public void autonomousInit() {
		// this robot should never be in autonomous lol
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		SmartDashboard.putString("current intake command", intake.getCurrentCommand().toString());
		//SmartDashboard.putData("Enable Charging", NewKickSequence.enableCharging());
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		intake.updateSmartDashboard();
    	SmartDashboard.putNumber("Angle sensor", RobotMap.intakeAngleSensor.get());
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}
