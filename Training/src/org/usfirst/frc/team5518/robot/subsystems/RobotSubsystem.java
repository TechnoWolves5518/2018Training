package org.usfirst.frc.team5518.robot.subsystems;

import org.usfirst.frc.team5518.robot.RobotMap;
import org.usfirst.frc.team5518.robot.commands.RobotCommand;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class RobotSubsystem extends Subsystem {
	
	// Defines driver motor controllers
	WPI_TalonSRX leftRearMotor = new WPI_TalonSRX(RobotMap.LEFT_REAR_MOTOR);
	WPI_TalonSRX rightRearMotor = new WPI_TalonSRX(RobotMap.RIGHT_REAR_MOTOR);
	WPI_TalonSRX leftFrontMotor = new WPI_TalonSRX(RobotMap.LEFT_FRONT_MOTOR);
	WPI_TalonSRX rightFrontMotor = new WPI_TalonSRX(RobotMap.RIGHT_FRONT_MOTOR);
	
	VictorSP topShootMotor = new VictorSP(RobotMap.TOP_SHOOT_MTR);
	VictorSP btmShootMotor = new VictorSP(RobotMap.BTM_SHOOT_MTR);
	
	VictorSP intakeMotor = new VictorSP(RobotMap.INTAKE_MTR); // motor controller

	SpeedControllerGroup leftSide = new SpeedControllerGroup(leftRearMotor , leftFrontMotor); // left side motor group
	SpeedControllerGroup rightSide = new SpeedControllerGroup(rightRearMotor , rightFrontMotor); // right side motor group
	
	DifferentialDrive driveBase = new DifferentialDrive(leftSide , rightSide); // i did it
    
	private Encoder leftEnc, rightEnc;
	// Set up encoder values
	private double wheelCircumference = 8;
	private double kDistancePerRevolution = wheelCircumference * Math.PI; // Distance traveled in one wheel rotation (circumference)
	private double kPulsesPerRevolution = 360; // Encoder pulses in one shaft revolution
	private double kDistancePerPulse = kDistancePerRevolution / kPulsesPerRevolution; // Distance in inches per pulse
	
    public RobotSubsystem() {
    		intakeMotor.setInverted(true);
    		leftEnc.setDistancePerPulse(kDistancePerPulse);
    		rightEnc.setDistancePerPulse(kDistancePerPulse);
    		leftEnc.setMaxPeriod(0.1);
    		rightEnc.setMaxPeriod(0.1);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new RobotCommand());
    }
    
    public void drive(double speed , double turn) {
    		driveBase.arcadeDrive(speed, turn);
    }
    
    public void shoot(double speed) {
    		topShootMotor.setSpeed(speed);
    		btmShootMotor.setSpeed(-speed);
    }
    
    public void intake(double speed) {
		intakeMotor.setSpeed(speed);
    }
    
    public int getLeftEncoderPos() {
    		return leftEnc.get();
    }
    
    public int getRightEncoderPos() {
		return rightEnc.get();
    }
    
    public double getWheelCircumference() {
    		// converts inches to meters because pathfinder is dumb
    		return wheelCircumference * 0.0254;
    }
    
}




