package frc.robot.subsystems;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;

public class ExampleSingleMotor extends SubsystemBase{

    ///////////////// 
   //  Variables  //
  /////////////////
    private final CANSparkMax canspark = new CANSparkMax(14, MotorType.kBrushless);
    private final RelativeEncoder rEnc;
    private final PIDController pid = new PIDController(0.1, 0.0, 0.0);
    private double before;
    private double setpoint;
    private boolean pidOn = true;
    private double manualSpeed = 0;
    private double encoderValue;


    
    /////////////////////////////////////////
   ///  Pivot Arm Subsystem Constructor  ///
  /////////////////////////////////////////
    public ExampleSingleMotor(){ // Instantiates the Talon Encoder variable and sets the tolerance for the PID
        canspark.setIdleMode(IdleMode.kBrake);
        canspark.setInverted(true);
        rEnc = canspark.getEncoder();
        setpoint = rEnc.getPosition();
    }

    
    public void enablePid(){
        pidOn = true; 
      }
    
      public void disablePid(){
        pidOn = false;
      }

      public boolean isPIDOn(){
        return pidOn;
      }

      public void newSetpoint(double setpoint){
        this.setpoint = setpoint;
    }
    
    /////////////////////////
   ///  Encoder Methods  ///
  /////////////////////////
    public double getEncoder(){ // Return the Encoder Value
        return rEnc.getPosition();
    }

    public void resetEncoder(){ // Resets the Encoder to a Position of 0
        rEnc.setPosition(0);
    }
    public void currentEncValtoSetpoint(){
        setpoint = getEncoder();
      }

    /////////////////////////////////
   ///  Set Pivot Speed Methods  ///
  /////////////////////////////////
    public void pivotUp(DoubleSupplier speed){ // Pivots the arm up based on its speed
        canspark.set(speed.getAsDouble());
     }

    public void pivotArm(double speed){ // Pivots the arm based on its speed
        canspark.set(speed);
    }

    public void pivotStop(){ // Stops the Pivot Arm Motor 
        canspark.stopMotor();
    }
                
    public void setManualSpeed(double inputSpeed){
        manualSpeed = inputSpeed;
      }
    
    ////////////////////////
   ///  Printing Method ///
  ////////////////////////
  
    public void periodic(){
        encoderValue = getEncoder();
        double calcSpeed = 0;
      
        if(pidOn){
          calcSpeed = pid.calculate(encoderValue, setpoint); // SETS MOTOR SPEED TO CALCULATED PID SPEED 
        }
        else{
          calcSpeed = manualSpeed;
        }
        
                
        //Max Speeds
        if(calcSpeed > .5){ 
          calcSpeed = .5;
        }
        else if(calcSpeed < -0.5){ 
          calcSpeed = -0.5;
        }
        canspark.set(calcSpeed);

        SmartDashboard.putNumber("[M] ENCODER", getEncoder()); // Prints out the encoder values
        SmartDashboard.putNumber("[M] SETPOINT", setpoint );
        SmartDashboard.putBoolean("[M] PID", isPIDOn());
    }
}