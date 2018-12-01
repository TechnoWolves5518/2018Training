/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5518.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	public static double MAX_SPEED = 1.7; // m/s
	public static double MAX_ACCEL = 2.0; // m/s/s
	public static double MAX_JERK = 60.0; // m/s/s
	
	// Drive Train Ports
	public static int LEFT_REAR_MOTOR = 0;
	public static int RIGHT_REAR_MOTOR = 1;
	public static int LEFT_FRONT_MOTOR = 2;
	public static int RIGHT_FRONT_MOTOR = 3;

	// Special Functions Ports
	public static int BTM_SHOOT_MTR = 8;
	public static int TOP_SHOOT_MTR = 4;
	public static int INTAKE_MTR = 7;
	
	// Sensor Ports
	public static int LEFT_ENC_A = 0;
	public static int LEFT_ENC_B = 1;
	public static int RIGHT_ENC_A = 2;
	public static int RIGHT_ENC_B = 3;
	
	// Wingman Axes
	// axes
	public static int JOYSTICK_XAXIS = 0;
	public static int JOYSTICK_YAXIS = 1;
	public static int JOYSTICK_ZAXIS = 2;

	// Xbox 360/One Controller Mapping
		// axes
	public static int XBOX_LSTICKX = 0;
	public static int XBOX_LSTICKY = 1; 
	public static int XBOX_RSTICKX = 4;
	public static int XBOX_RSTICKY = 5;
	public static int XBOX_LTRIGGER = 2;
	public static int XBOX_RTRIGGER = 3;
		// buttons
	public static int XBOX_YBTN = 4;
	public static int XBOX_XBTN = 3;
	public static int XBOX_ABTN = 1;
	public static int XBOX_BBTN = 2;
	public static int XBOX_RBUMPER = 6;
	public static int XBOX_LBUMPER = 5;
	public static int XBOX_LSTICK = 9;
	public static int XBOX_RSTICK = 10;
	public static int XBOX_START = 8;
	public static int XBOX_BACK = 7;
	
}
