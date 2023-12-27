package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ExamplePneumaticsSubsystem;

//TOGGLE Pneumatics (CLOSE AND OPEN)
public class Pneumatics1 extends CommandBase {
  private ExamplePneumaticsSubsystem pTestSubsytem;

  public Pneumatics1(ExamplePneumaticsSubsystem ptest) {
    pTestSubsytem = ptest;
    addRequirements(ptest);
  }

  @Override
  public void initialize() {
    pTestSubsytem.toggleTest1();
  }

  @Override
  public void execute() {
    SmartDashboard.putString("Current Command", getName());

  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return true;
  }
}