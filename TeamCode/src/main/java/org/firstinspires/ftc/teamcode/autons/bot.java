package org.firstinspires.ftc.teamcode.autons;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.common.Settings;
import org.firstinspires.ftc.teamcode.hardware.Robot;


@Disabled
@Autonomous(name = "bot", group = "Auton")
// @Autonomous(...) is the other common choice

public class bot extends OpMode {

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
                currentStage = stage._10_MarkOn1;
                break;
            case _10_MarkOn1:
                robot.picasso.PAINT();
                runtime.reset();
                currentStage = stage._20_Back1;
                break;
            case _20_Back1:
                if(runtime.milliseconds()>= 600){
                    robot.driveTrain.CmdDrive(8,-180,0.10,0);
                    currentStage = stage._30_Right1;

                }
                break;
            case _30_Right1:
                if (robot.driveTrain.getCmdComplete())     {
                    robot.driveTrain.CmdDrive(4,90,0.10,0);
                    currentStage = stage._40_Forward1;
                }
                break;
            case _40_Forward1:
                if (robot.driveTrain.getCmdComplete())  {
                    robot.driveTrain.CmdDrive(4,0,0.10,0);
                    currentStage = stage._50_Left1;
                }
                break;
            case _50_Left1:
                if (robot.driveTrain.getCmdComplete()){
                    robot.driveTrain.CmdDrive(4,-90,0.10,0);
                    currentStage = stage._60_MarkOff1;
                }

                break;
            case _60_MarkOff1:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.picasso.LIFT();
                    runtime.reset();
                    currentStage = stage._70_Right2;
                }
                    break;
            case _70_Right2:
                if(runtime.milliseconds()>= 600){
                    robot.driveTrain.CmdDrive(6,90,0.10,0);
                    currentStage = stage._80_Markon2;

                }
                break;


            case _80_Markon2:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.picasso.PAINT();
                    runtime.reset();
                    currentStage = stage._90_Right3;
                }
                break;
            case _90_Right3:
                if(runtime.milliseconds()>= 600){
                    robot.driveTrain.CmdDrive(4,90,0.10,0);
                    currentStage = stage._100_Back2;

                }
                break;

            case _100_Back2:
                if (robot.driveTrain.getCmdComplete()){
                    robot.driveTrain.CmdDrive(4,-180,0.10,0);
                    currentStage = stage._110_Left2;
                }

                break;


            case _110_Left2:
                if (robot.driveTrain.getCmdComplete()){
                    robot.driveTrain.CmdDrive(4,-90,0.10,0);
                    currentStage = stage._120_Forward2;
                }

                break;

            case _120_Forward2:
                if (robot.driveTrain.getCmdComplete()){
                    robot.driveTrain.CmdDrive(4,0,0.10,0);
                    currentStage = stage._130_MarkOff2;
                }

                break;

            case _130_MarkOff2:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.picasso.LIFT();
                    runtime.reset();
                    currentStage = stage._140_Right4;
                }
                break;

            case _140_Right4:
                if(runtime.milliseconds()>= 600){
                    robot.driveTrain.CmdDrive(6,90,0.10,0);
                    currentStage = stage._150_MarkOn;

                }
                break;

            case _150_MarkOn:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.picasso.PAINT();
                    runtime.reset();
                    currentStage = stage._160_Right5;
                }
                break;

            case _160_Right5:
                if(runtime.milliseconds()>= 600){
                    robot.driveTrain.CmdDrive(4,90,0.10,0);
                    currentStage = stage._170_MarkOff3;

                }
                break;

            case _170_MarkOff3:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.picasso.LIFT();
                    runtime.reset();
                    currentStage = stage._180_Forward3;
                }
                break;

            case _180_Forward3:
                if(runtime.milliseconds()>= 600){
                    robot.driveTrain.CmdDrive(4,0,0.10,0);
                    currentStage = stage._190_Left3;

                }
                break;

            case _190_Left3:
                if (robot.driveTrain.getCmdComplete()){
                    robot.driveTrain.CmdDrive(2,-90,0.10,0);
                    currentStage = stage._200_MarkOn;
                }

                break;

            case _200_MarkOn:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.picasso.PAINT();
                    runtime.reset();
                    currentStage = stage._210_Back3;
                }
                break;

            case _210_Back3:
                if(runtime.milliseconds()>= 600){
                    robot.driveTrain.CmdDrive(8,-180,0.10,0);
                    currentStage = stage._220_MarkOff4;

                }
                break;

            case _220_MarkOff4:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.picasso.LIFT();
                    runtime.reset();
                    currentStage = stage._230_ExitStageRight1;
                }
                break;

            case _230_ExitStageRight1:
                if(runtime.milliseconds()>= 600){
                    robot.driveTrain.CmdDrive(24,90,0.10,0);
                    currentStage = stage._240_End;

                }
                break;

            case _240_End:
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
        _10_MarkOn1,
        _20_Back1,
        _30_Right1,
        _40_Forward1,
        _50_Left1,
        _60_MarkOff1,
        _70_Right2,
        _80_Markon2,
        _90_Right3,
        _100_Back2,
        _110_Left2,
        _120_Forward2,
        _130_MarkOff2,
        _140_Right4,
        _150_MarkOn,
        _160_Right5,
        _170_MarkOff3,
        _180_Forward3,
        _190_Left3,
        _200_MarkOn,
        _210_Back3,
        _220_MarkOff4,
        _230_ExitStageRight1,
        _240_End


    }
}
