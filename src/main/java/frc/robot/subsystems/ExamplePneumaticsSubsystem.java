// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;



public class ExamplePneumaticsSubsystem extends SubsystemBase {

  // Class Variables
  private DoubleSolenoid pTest1;
  private final int pTest1_FORWARD_CHANNEL = 0;
  private final int pTest1_REVERSE_CHANNEL = 8;
  //Second One
  //private DoubleSolenoid pTest2;
  //private final int pTest2_FORWARD_CHANNEL = 1;
  //private final int pTest2_REVERSE_CHANNEL = 9;

  /** Creates a new ExampleSubsystem. */
  public ExamplePneumaticsSubsystem() {
        //forward=open    reverse=closed
        pTest1 = new DoubleSolenoid(PneumaticsModuleType.REVPH, pTest1_FORWARD_CHANNEL,pTest1_REVERSE_CHANNEL);
        //Set Initial Direction
        pTest1.set(Value.kForward); 
  }

  /**
   * Example command factory method.
   *
   * @return a command
   */
  public CommandBase exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  public void toggleTest1(){
    pTest1.toggle();
  }

  public void openTest1(){
    pTest1.set(Value.kForward);
  }

  public void closeTest1(){
    pTest1.set(Value.kReverse);
  }

  /*
   *public void toggleTest2(){
    pTest2.toggle();
  }

  public void openTest2(){
    pTest2.set(Value.kForward);
  }

  public void closeTest2(){
    pTest2.set(Value.kReverse);
  }
   */

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("P1 Forward?", pTest1.get().toString().equals("kForward"));
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
