package frc.robot;

//import edu.wpi.first.wpilibj.XboxController;
//import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX; //imports the correct victor API
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
//import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.first.cameraserver.CameraServer;


// the robot class create all the parts of the robot, ie the motors and sensors
public class Robot extends TimedRobot 
{
  // private MecanumDrive m_robotDrive;

   private Joystick m_stick;
    //temp
   JoystickButton Button1 = new JoystickButton(m_stick, 2);

   //sets up the ports of motors
   private static final int kMotorPortLeftFront = 10;
   private static final int kMotorPortLeftRear = 11; 
   private static final int kMotorPortRightFront = 20;
   private static final int kMotorPortRightRear = 21;
   private static final int kMotorPortColorWH = 2; 
   private static final int kMotorPortShooter = 3;
   private static final int kJoystickChannel = 0;

   UsbCamera Front;
   UsbCamera back;
   UsbCamera shooter;
   VideoSink server;

 
   // the doubles control the left and right drive train values, speed
   Double LDTMotor; //Left Drive Train
   Double RDTMotor; //Right Drive Train
   Double scaleFactor; // this is the value that scales the speed of the motors
   WPI_VictorSPX m_LeftFront = new WPI_VictorSPX(kMotorPortLeftFront); //identifys VictorSPX to Motors 
   WPI_VictorSPX m_LeftRear = new WPI_VictorSPX(kMotorPortLeftRear);
   WPI_VictorSPX m_RightFront = new WPI_VictorSPX(kMotorPortRightFront);
   WPI_VictorSPX m_RightRear = new WPI_VictorSPX(kMotorPortRightRear); 
   SpeedControllerGroup m_LeftGroup = new SpeedControllerGroup(m_LeftFront, m_LeftRear);
   SpeedControllerGroup m_RightGroup = new SpeedControllerGroup(m_RightFront, m_RightRear);
   WPI_VictorSPX m_ColorWH = new WPI_VictorSPX(kMotorPortColorWH);
   WPI_VictorSPX m_Shooter = new WPI_VictorSPX(kMotorPortShooter);
   DifferentialDrive m_drive = new DifferentialDrive(m_LeftGroup, m_RightGroup);
   double stick1val,stick2val;

  Compressor air = new Compressor(); //sets up a compressor for pistons
  private final Timer m_timer = new Timer(); //identifys a timer 



@Override
public void robotInit(){
    WPI_VictorSPX m_LeftFront = new WPI_VictorSPX(kMotorPortLeftFront);
    WPI_VictorSPX m_RightFront = new WPI_VictorSPX(kMotorPortLeftRear);
    WPI_VictorSPX m_LeftRear = new WPI_VictorSPX(kMotorPortRightFront);
    WPI_VictorSPX m_RightRear = new WPI_VictorSPX(kMotorPortRightRear);
    WPI_VictorSPX m_Shooter = new WPI_VictorSPX(kMotorPortShooter);
    m_LeftFront.setInverted(true);
    m_RightRear.setInverted(true);

   Front = CameraServer.getInstance().startAutomaticCapture();
   back = CameraServer.getInstance().startAutomaticCapture();
   shooter = CameraServer.getInstance().startAutomaticCapture();
    server = CameraServer.getInstance().getServer();
    //m_robotDrive = new MecanumDrive(m_leftFront, m_leftRear, m_rightFront, m_rightRear);

    m_stick = new Joystick(kJoystickChannel);

scaleFactor = 0.5;
} 

@Override
public void robotPeriodic(){
}


@Override
public void autonomousInit(){

}


@Override 
public void autonomousPeriodic() {

  //if (m_timer < 5){
   //  m_LeftGroup =  ;
}
   


//runs teleoperated 
@Override
  public void teleopPeriodic() {
    //m_robotDrive.driveCartesian(m_stick.getX(), m_stick.getY(),m_stick.getZ(), 0.0)
    m_drive.arcadeDrive(m_stick.getY(), m_stick.getX());

        if (m_stick.getRawButtonPressed(4)) 
        {
          System.out.println("Setting camera 2");
          server.setSource(back);
         } 
      else if (m_stick.getRawButtonPressed(3)) 
      {
          System.out.println("Setting camera 1");
          server.setSource(Front);
      }
      if (m_stick.getRawButtonPressed(2))
      {
        System.out.println("Setting camera 1");
          server.setSource(shooter);
      }

  
    m_drive.setSafetyEnabled(false);

   
    
 
  }
 

@Override
public void testPeriodic() {
   
  
    m_drive.setSafetyEnabled(false);
    
    
    m_LeftFront.set(stick1val);
    m_LeftRear.set(stick1val);
    m_RightFront.set(stick2val);
    m_RightRear.set(stick2val);
    
    if(Timer.getMatchTime() > 10.1 && Timer.getMatchTime() < 10.9)
    {
      //frontSol.set(DoubleSolenoid.Value.kReverse);
      //rearSol.set(DoubleSolenoid.Value.kReverse);
    }  
    //System.out.println(timer.getMatchTime());

    
     
    


}

}



