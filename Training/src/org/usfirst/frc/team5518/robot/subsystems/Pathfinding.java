package org.usfirst.frc.team5518.robot.subsystems;

import org.usfirst.frc.team5518.robot.Robot;
import org.usfirst.frc.team5518.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;

/**
 *
 */
public class Pathfinding extends Subsystem {
	
	private Waypoint[] points = null;
	private Trajectory.Config config = null;
	private Trajectory trajectory = null;
	private TankModifier modifier = null;
	private Trajectory leftTrajectory, rightTrajectory;
	EncoderFollower leftFollower, rightFollower;
    
    public Pathfinding() {
    		
	    points = new Waypoint[] {
	    		    new Waypoint(-4, -1, Pathfinder.d2r(-45)),      // Waypoint @ x=-4, y=-1, exit angle=-45 degrees
	    		    new Waypoint(-2, -2, 0),                        // Waypoint @ x=-2, y=-2, exit angle=0 radians
	    		    new Waypoint(0, 0, 0)                           // Waypoint @ x=0, y=0,   exit angle=0 radians
	    	};
	    	
	    	// Create the Trajectory Configuration
	    	//
	    	// Arguments:
	    	// Fit Method:          HERMITE_CUBIC or HERMITE_QUINTIC
	    	// Sample Count:        SAMPLES_HIGH (100 000)
	    	//	                    SAMPLES_LOW  (10 000)
	    	//	                    SAMPLES_FAST (1 000)
	    	// Time Step:           0.05 seconds
	    	// Max Velocity:        1.7 m/s
	    	// Max Acceleration:    2.0 m/s/s
	    	// Max Jerk:            60.0 m/s/s/s
	    config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH,
	    									0.02, RobotMap.MAX_SPEED, RobotMap.MAX_ACCEL, RobotMap.MAX_JERK);
	    	
	    	// Generate the trajectory
	    	trajectory = Pathfinder.generate(points, config);
	    	
	    	// The distance between the left and right sides of the wheelbase is 0.6m
	    	double wheelbase_width = 0.6;
	    	
	    	// Create the Modifier Object
	    	modifier = new TankModifier(trajectory);
	    	modifier.modify(wheelbase_width);
	    	
	    	leftTrajectory  = modifier.getLeftTrajectory();       // Get the Left Side
	    	rightTrajectory = modifier.getRightTrajectory();      // Get the Right Side
	    	
	    leftFollower = new EncoderFollower(modifier.getLeftTrajectory());
	    	rightFollower = new EncoderFollower(modifier.getRightTrajectory());
    		
	    	// Encoder Position is the current, cumulative position of your encoder. If you're using an SRX, this will be the
	    	// 'getEncPosition' function.
	    	// 1000 is the amount of encoder ticks per full revolution
	    	// Wheel Diameter is the diameter of your wheels (or pulley for a track system) in meters
	    	leftFollower.configureEncoder(Robot.sub.getLeftEncoderPos(), 360, Robot.sub.getWheelCircumference());
	    	rightFollower.configureEncoder(Robot.sub.getRightEncoderPos(), 360, Robot.sub.getWheelCircumference());
	    	
	    	leftFollower.configurePIDVA(1.0, 0.0, 0.0, 1 / RobotMap.MAX_SPEED, 0);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
}

