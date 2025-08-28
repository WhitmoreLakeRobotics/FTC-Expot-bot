package org.firstinspires.ftc.teamcode.autons;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.common.Settings;
import org.firstinspires.ftc.teamcode.hardware.Robot;



@Disabled
@Autonomous(name = "Test_drive", group = "Auton")
// @Autonomous(...) is the other common choice

public class FTC extends OpMode {

    //RobotComp robot = new RobotComp();
    Robot robot = new Robot();




    private stage currentStage = stage._unknown;
    // declare auton power variables
    //private double AUTO_DRIVE_TURBO_SPEED = DriveTrain.DRIVETRAIN_TURBOSPEED;
    //private double AUTO_DRIVE_SLOW_SPEED = DriveTrain.DRIVETRAIN_SLOWSPEED;
    // private double AUTO_DRIVE_NORMAL_SPEED = DriveTrain.DRIVETRAIN_NORMALSPEED;
    // private double AUTO_TURN_SPEED = DriveTrain.DRIVETRAIN_TURNSPEED;

    private String RTAG = "8492-Auton";

    /* Declare OpMode members. */
    private ElapsedTime runtime = new ElapsedTime();

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        //----------------------------------------------------------------------------------------------
        // These constants manage the duration we allow for callbacks to user code to run for before
        // such code is considered to be stuck (in an infinite loop, or wherever) and consequently
        // the robot controller application is restarted. They SHOULD NOT be modified except as absolutely
        // necessary as poorly chosen values might inadvertently compromise safety.
        //----------------------------------------------------------------------------------------------
        msStuckDetectInit = Settings.msStuckDetectInit;
        msStuckDetectInitLoop = Settings.msStuckDetectInitLoop;
        msStuckDetectStart = Settings.msStuckDetectStart;
        msStuckDetectLoop = Settings.msStuckDetectLoop;
        msStuckDetectStop = Settings.msStuckDetectStop;

        robot.hardwareMap = hardwareMap;
        robot.telemetry = telemetry;
        robot.init();
        telemetry.addData("Test Auton", "Initialized");

        //Initialize Gyro
        robot.driveTrain.ResetGyro();
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
        // initialize robot
        robot.init_loop();

    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        // start robot
        runtime.reset();
        robot.start();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {

        telemetry.addData("Auton_Current_Stage ", currentStage);
        robot.loop();

        switch (currentStage){
            case  _unknown:
                currentStage = stage._00_preStart;
                break;
            case _00_preStart:
                currentStage = stage._05_MarkOn_0;
                break;
            case _05_MarkOn_0:
                robot.picasso.PAINT();
                runtime.reset();
                currentStage = stage._10_Forward_1;
                break;
            case _10_Forward_1:
                if (runtime.milliseconds() >= 600){
                    robot.driveTrain.CmdDrive(8,0,0.10,0);
                    currentStage = stage._20_Right_1;

                }
                break;
            case _20_Right_1:
                if (robot.driveTrain.getCmdComplete())     {
                    robot.driveTrain.CmdDrive(4,-90,0.10,0);
                    currentStage = stage._30_MarkOff_1;
                }
                break;
            case _30_MarkOff_1:
                if (robot.driveTrain.getCmdComplete())  {
                    robot.picasso.LIFT();
                    runtime.reset();
                    currentStage = stage._40_Backward_1;
                }
                break;
            case _40_Backward_1:
                if (runtime.milliseconds() >= 600){
                    robot.driveTrain.CmdDrive(4,-180,0.10,0);
                    currentStage = stage._50_MarkOn_1;
                }

                break;
            case _50_MarkOn_1:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.picasso.PAINT();
                    runtime.reset();
                    currentStage = stage._60_Left_1;
                }

                break;
            case _270_End:
                if(robot.driveTrain.getCmdComplete()){
                    robot.stop();


                }







                break;
        }



    }  //  loop

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        robot.stop();
    }

    private enum stage {
        _unknown,
        _00_preStart,
        _05_MarkOn_0,
        _10_Forward_1,
        _20_Right_1,
        _30_MarkOff_1,
        _40_Backward_1,
        _50_MarkOn_1,
        _60_Left_1,
        _70_MarkOff_2,
        _80_Forward_2,
        _90_Right_2,
        _100_MarkOn_2,
        _110_Right_3,
        _120_MarkOff_3,
        _130_Left_2,
        _140_MarkOn_3,
        _150_Backward_2,
        _160_MarkOff_4,
        _170_Left_3,
        _180_MarkOn_4,
        _190_Forward_3,
        _200_Left_4,
        _210_MarkOff_5,
        _220_Backward_3,
        _230_MarkOn_5,
        _240_Right_4,
        _250_MarkOff_6,
        _260_ExitStageRight_1,
        _270_End


    }
}