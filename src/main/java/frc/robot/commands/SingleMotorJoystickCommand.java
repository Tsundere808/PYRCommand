// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ExampleSingleMotor;

public class SingleMotorJoystickCommand extends CommandBase {
  /** Creates a new Test. */
  ExampleSingleMotor sMotorsubsystem;
  DoubleSupplier speed;
  public SingleMotorJoystickCommand(ExampleSingleMotor smotorSub, DoubleSupplier speed) {
    sMotorsubsystem = smotorSub;
    this.speed = speed;
    addRequirements(sMotorsubsystem);
  }

  @Override
  public void initialize() {
    sMotorsubsystem.disablePid();
  }

  @Override
  public void execute() {
    SmartDashboard.putString("Current Command", getName());
    sMotorsubsystem.setManualSpeed(speed.getAsDouble());
  }

  @Override
  public void end(boolean interrupted) {
    sMotorsubsystem.enablePid();
    sMotorsubsystem.currentEncValtoSetpoint();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}