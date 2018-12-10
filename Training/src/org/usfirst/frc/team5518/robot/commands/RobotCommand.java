package org.usfirst.frc.team5518.robot.commands;

import org.usfirst.frc.team5518.robot.OI;
import org.usfirst.frc.team5518.robot.Robot;
import org.usfirst.frc.team5518.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RobotCommand extends Command {
	
	// driveSpeed - forward/backwards movement
	// turnSpeed - clockwise/anti-clockwise movement
	double driveSpeed, turnSpeed;
	
	double shootSpeed;
	
	boolean isIntakePressed, isOuttakePressed;
	
    public RobotCommand() {
        requires(Robot.sub);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    		
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
	    	
	    	driveSpeed = OI.driverController.getRawAxis(RobotMap.XBOX_LSTICK);
	    	turnSpeed = OI.driverController.getRawAxis(RobotMap.XBOX_RSTICK);
	    	
	    	shootSpeed = OI.driverController.getRawAxis(RobotMap.XBOX_RTRIGGER);
	    	
	    	isIntakePressed = OI.driverController.getRawButton(RobotMap.XBOX_ABTN);
	    	isOuttakePressed = OI.driverController.getRawButton(RobotMap.XBOX_BBTN);
	    	
	    	if (isIntakePressed == true)
	    		Robot.sub.intake(1);
	    	else if (isOuttakePressed == true)
	    		Robot.sub.intake(-1);
	    	else
	    		Robot.sub.intake(0);
	    	
	    	Robot.sub.drive(driveSpeed, turnSpeed);
	    	Robot.sub.shoot(shootSpeed);
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
