package org.usfirst.frc.team5518.robot.subsystems;

import org.usfirst.frc.team5518.robot.RobotMap;
import org.usfirst.frc.team5518.robot.commands.RobotCommand;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class RobotSubsystem extends Subsystem {

    VictorSP intakeMotor = new VictorSP(RobotMap.INTAKE_MTR);

    public void initDefaultCommand() {
        setDefaultCommand(new RobotCommand());
    }
    
    public void intake() {
    		intakeMotor.setSpeed(1);
    }
    
}

