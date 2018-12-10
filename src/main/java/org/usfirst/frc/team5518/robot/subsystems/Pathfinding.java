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
	EncoderFollower leftFollower, rightFollower;

	public Pathfinding(Waypoint[] path) {
		
		points = path;

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
				0.05, RobotMap.MAX_SPEED, RobotMap.MAX_ACCEL, RobotMap.MAX_JERK);

		// Generate the trajectory
		trajectory = Pathfinder.generate(points, config);

		// The distance between the left and right sides of the wheelbase is 0.6m
		double wheelbase_width = 0.6;

		// Create the Modifier Object
		modifier = new TankModifier(trajectory);
		modifier.modify(wheelbase_width);

		leftFollower = new EncoderFollower(modifier.getLeftTrajectory());
		rightFollower = new EncoderFollower(modifier.getRightTrajectory());

		leftFollower.configurePIDVA(1.0, 0.0, 0.0, 1 / RobotMap.MAX_SPEED, 0);
		rightFollower.configurePIDVA(1.0, 0.0, 0.0, 1 / RobotMap.MAX_SPEED, 0);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
	}

	public void calculatePath() {
		// Encoder Position is the current, cumulative position of your encoder. If you're using an SRX, this will be the
		// 'getEncPosition' function.
		// 1000 is the amount of encoder ticks per full revolution
		// Wheel Diameter is the diameter of your wheels (or pulley for a track system) in meters
		leftFollower.configureEncoder(Robot.sub.getLeftEncoderPos(), 360, Robot.sub.getWheelCircumference());
		rightFollower.configureEncoder(Robot.sub.getRightEncoderPos(), 360, Robot.sub.getWheelCircumference());

		double l = leftFollower.calculate(Robot.sub.getLeftEncoderPos());
		double r = rightFollower.calculate(Robot.sub.getRightEncoderPos());

		double gyro_heading = Robot.sub.getAngle();  // Assuming the gyro is giving a value in degrees
		double desired_heading = Pathfinder.r2d(leftFollower.getHeading());  // Should also be in degrees

		double angleDifference = Pathfinder.boundHalfDegrees(desired_heading - gyro_heading);
		double turn = 0.8 * (-1.0/80.0) * angleDifference;
		
		Robot.sub.tankDrive(l + turn, r - turn);
	}

}