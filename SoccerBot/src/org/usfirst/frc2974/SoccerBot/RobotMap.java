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

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;

import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static SpeedController driveTrainFrontL;
    public static SpeedController driveTrainFrontR;
    public static SpeedController driveTrainBackL;
    public static SpeedController driveTrainBackR;
    public static Solenoid intakeArmSolenoid;
    public static Solenoid intakeArmBlock; 
    public static AnalogPotentiometer intakeAngleSensor;
    public static CANTalon intakeArmTalon;
    public static Compressor compressor;
    public static Solenoid kickerLeft;
    public static Solenoid kickerRight;
    public static Solenoid retract;
    public static Solenoid latch;
    
    public static DigitalInput limitSwitchForward;
    public static DigitalInput limitSwitchBackward;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
   
    //all inputs
    public static DigitalInput digital0;
    public static DigitalInput digital1;
    public static DigitalInput digital2;
    public static DigitalInput digital3;
    public static DigitalInput digital4;
    public static DigitalInput digital5;
    public static DigitalInput digital6;
    public static DigitalInput digital7;
    public static DigitalInput digital8;
    public static DigitalInput digital9;
    
    public static AnalogInput analog0;
    public static AnalogInput analog1;
    public static AnalogInput analog2;
    public static AnalogInput analog3;
    
    public static void init() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveTrainFrontL = new Talon(0);
        LiveWindow.addActuator("DriveTrain", "Speed Controller 1", (Talon) driveTrainFrontL);
        
        driveTrainFrontR = new Talon(0);
        LiveWindow.addActuator("DriveTrain", "Speed Controller 2", (Talon) driveTrainFrontR);
        
        driveTrainBackL = new Talon(0);
        LiveWindow.addActuator("DriveTrain", "Speed Controller 3", (Talon) driveTrainBackL);
        
        driveTrainBackR = new Talon(0);
        LiveWindow.addActuator("DriveTrain", "Speed Controller 4", (Talon) driveTrainBackR);
        
        intakeArmSolenoid = new Solenoid(0, 4);
        LiveWindow.addActuator("intake", "ArmSolenoid", intakeArmSolenoid);
        
        intakeArmBlock = new Solenoid(0, 5);
        LiveWindow.addActuator("intake", "ArmBlock", intakeArmBlock);
        
        intakeAngleSensor = new AnalogPotentiometer(0, 1.0, 0.0);
        LiveWindow.addSensor("intake", "AngleSensor", intakeAngleSensor);
        
        intakeArmTalon = new CANTalon(0);
        LiveWindow.addActuator("intake", "ArmTalon", intakeArmTalon);
        
        kickerLeft = new Solenoid(0,0);
        LiveWindow.addActuator("kicker", "KickerLeftSolenoid", kickerLeft);
        
        kickerRight = new Solenoid(0,1);
        LiveWindow.addActuator("kicker", "KickerRightSolenoid", kickerRight);
        
        retract = new Solenoid(0,2);
        LiveWindow.addActuator("kicker", "retractSolenoid", retract);
        
        latch = new Solenoid(0,3);
        LiveWindow.addActuator("kicker", "latchSolenoid", latch);
        
        compressor = new Compressor(0);
        
        limitSwitchForward = new DigitalInput(0);
        LiveWindow.addSensor("kicker","forward limitswitch", limitSwitchForward);
        
        limitSwitchBackward = new DigitalInput(1);
        LiveWindow.addSensor("kicker","backward limitswitch", limitSwitchBackward);
        
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        
        //all inputs
        digital0 = new DigitalInput(0);
        digital1 = new DigitalInput(1);
        digital2 = new DigitalInput(2);
        digital3 = new DigitalInput(3);
        digital4 = new DigitalInput(4);
        digital5 = new DigitalInput(5);
        digital6 = new DigitalInput(6);
        digital7 = new DigitalInput(7);
        digital8 = new DigitalInput(8);
        digital9 = new DigitalInput(9);
        
        analog0 = new AnalogInput(0);
        analog1 = new AnalogInput(1);
        analog2 = new AnalogInput(2);
        analog3 = new AnalogInput(3);
    }
}
