package org.usfirst.frc.team5518.robot.commands;

import org.usfirst.frc.team5518.robot.subsystems.Pathfinding;

import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Waypoint;

/**
 *
 */
public class AutoPath extends Command {
	
	Waypoint[] path = new Waypoint[] {
			new Waypoint(0, 0, 0),
			new Waypoint(16, 4, Pathfinder.d2r(45)),
			new Waypoint(22, 14, Pathfinder.d2r(20))
	};
	
	Pathfinding pathfinding = new Pathfinding(path);
	
    public AutoPath() {
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    		pathfinding.calculatePath();
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
