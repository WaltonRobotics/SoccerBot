package org.usfirst.frc2974.SoccerBot.commands;

import org.usfirst.frc2974.SoccerBot.Robot;
import org.usfirst.frc2974.SoccerBot.subsystems.Kicker;
import org.usfirst.frc2974.SoccerBot.subsystems.AbstractIntake.ArmPosition;
import org.usfirst.frc2974.SoccerBot.subsystems.Kicker.LatchPosition;
import org.usfirst.frc2974.SoccerBot.subsystems.Kicker.Position;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class NewKickSequence extends Command {

	private static boolean fullCharge = true;
	private static boolean disabledCharging = false;
	private static double timeSinceFlat = 0;
	final private static double TIMEOUT = 3;

	private State state;

	public enum State {
		RESTING {
			@Override
			public void init(NewKickSequence nks) {
				Robot.kicker.setLatch(LatchPosition.unlatched);
				Robot.kicker.setRetract(true);
			}

			@Override
			public State run(NewKickSequence nks) {
				if (Robot.kicker.getPosition() == Position.retracted) {
					Robot.kicker.setLatch(LatchPosition.latched);
					Robot.kicker.setRetract(false);
					if (!disabledCharging) {
						return CHARGING;
					}
				}
				return this;
			}
		},
		CHARGING {
			@Override
			public void init(NewKickSequence nks) {
				if (fullCharge) {
					Robot.kicker.setCharge2();
				} else {
					Robot.kicker.setCharge1();
				}
			}

			@Override
			public State run(NewKickSequence nks) {
				if (Robot.oi.discharge.get()) {
					Robot.kicker.deactivateKickPistons();
					nks.disableCharging();
					return RESTING;
				}
				if (Robot.oi.kickButton.get()) {
					return KICKING;
				}
				return this;
			}
		},
		KICKING {
			@Override
			public void init(NewKickSequence nks) {
				Robot.intake.startFlat();
			}

			@Override
			public State run(NewKickSequence nks) {
				if (Robot.intake.getArmPosition() == ArmPosition.flat) {
					timeSinceFlat = nks.getTime();
				}
				if (Robot.kicker.getPosition() == Kicker.Position.extended || nks.getTime() - timeSinceFlat > TIMEOUT) {
					return RESTING;
				}
				return this;
			}
		};
		public State run(NewKickSequence nks) {
			return this;
		}

		public void init(NewKickSequence nks) {

		}
	}

	private double getTime() {
		return timeSinceInitialized();
	}

	public void disableCharging() {
		disabledCharging = true;
	}

	public static Sendable enableCharging() {
		disabledCharging = false;
		return null;
	}

	public void chargeSwitch() {
		if (fullCharge) {
			fullCharge = false;
		} else {
			fullCharge = true;
		}
	}

	public NewKickSequence() {
		requires(Robot.kicker);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		state = State.RESTING;
		state.init(this);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		State newState = state.run(this);
		if (newState != state) {
			state = newState;
			state.init(this);
		}
		if (Robot.oi.chargeSwitch.get()) {
			chargeSwitch();
		}
		SmartDashboard.putBoolean("disabledCharging", disabledCharging);
		SmartDashboard.putString("Kicker state", state.toString());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.kicker.setOff();
		Robot.intake.endFlat();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}

}
