package org.usfirst.frc.team5518.robot.subsystems;

import org.usfirst.frc.team5518.robot.RobotMap;
import org.usfirst.frc.team5518.robot.commands.RobotCommand;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class RobotSubsystem extends Subsystem {
	
	// Defines driver motor controllers
	VictorSP leftRearMotor = new VictorSP(RobotMap.LEFT_REAR_MOTOR);
	VictorSP rightRearMotor = new VictorSP(RobotMap.RIGHT_REAR_MOTOR);
	VictorSP leftFrontMotor = new VictorSP(RobotMap.LEFT_FRONT_MOTOR);
	VictorSP rightFrontMotor = new VictorSP(RobotMap.RIGHT_FRONT_MOTOR);
	
	VictorSP topShootMotor = new VictorSP(RobotMap.TOP_SHOOT_MTR);
	VictorSP btmShootMotor = new VictorSP(RobotMap.BTM_SHOOT_MTR);
	
	VictorSP intakeMotor = new VictorSP(RobotMap.INTAKE_MTR); // motor controller

	SpeedControllerGroup leftSide = new SpeedControllerGroup(leftRearMotor , leftFrontMotor); // left side motor group
	SpeedControllerGroup rightSide = new SpeedControllerGroup(rightRearMotor , rightFrontMotor); // right side motor group
	
	DifferentialDrive driveBase = new DifferentialDrive(leftSide , rightSide); // i did it
    
    public RobotSubsystem() {
    		intakeMotor.setInverted(true);
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
}




