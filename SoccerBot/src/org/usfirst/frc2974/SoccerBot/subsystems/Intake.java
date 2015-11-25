// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc2974.SoccerBot.subsystems;

import org.usfirst.frc2974.SoccerBot.RobotMap;
import org.usfirst.frc2974.SoccerBot.commands.*;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Solenoid;

import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Intake extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS
	public enum ArmMovement {
		up, fall
	}
	private ArmMovement action;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final Solenoid armSolenoid = RobotMap.intakeArmSolenoid;
    private final AnalogPotentiometer angleSensor = RobotMap.intakeAngleSensor;
    private final CANTalon armTalon = RobotMap.intakeArmTalon;
    //private final Compressor armCompressor = RobotMap.intakeArmCompressor;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS


    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new IntakeManual());

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        
    }
    
    public void setArmMovement(ArmMovement move)
    {	
    	switch(move){
    	case up:
    		armSolenoid.set(true);
    		break;
    	case fall:
    		armSolenoid.set(false);
    		break;  		
    	
    	}
    	action = move;
    }
    
    
    
    public ArmMovement getAction()
    {
    	return action;
    }
    
    public void setMotorPower(double speed)
    {
    	armTalon.set(speed);
    }
    

}

