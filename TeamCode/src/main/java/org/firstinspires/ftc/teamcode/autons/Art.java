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

public class Art extends OpMode {

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

        switch (currentStage) {
            case _unknown:
                currentStage = stage._00_preStart;
                break;
            case _00_preStart:
                currentStage = stage._10_MarkOn1;
                break;
            case _10_MarkOn1:
                robot.driveTrain.CmdDrive(0, 0, 0.0, 0);
                currentStage = stage._20_Forward1;
                break;
            case _20_Forward1:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(4, 0, 0.2, 0);
                    currentStage = stage._30_Right1;

                }
                break;
            case _30_Right1:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(3, 90, 0.2, 0);
                    currentStage = stage._40_Backward1;
                }
                break;
            case _40_Backward1:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(4, -180, 0.2, 90);
                    currentStage = stage._50_MarkOff1;
                }
                break;
            case _50_MarkOff1:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(0, 0, 0.0, 0);
                    currentStage = stage._60_Forward2;
                }

                break;
            case _60_Forward2:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(2, 0, 0.2, 0);
                    currentStage = stage._70_MarkOn2;
                }

                break;
            case _70_MarkOn2:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(0, 0, 0, 0);
                    currentStage = stage._80_Left1;

                }


                break;
            case _80_Left1:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(3, -90, 0.2, 0);
                    currentStage = stage._100_MarkOff2;

                }

                break;
            case _100_MarkOff2:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(0, 0, 0.0, 0);
                    currentStage = stage._110_Right2;

                }

                break;
            case _110_Right2:
                if(robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(4, -180, 0.2, 0);
                    currentStage = stage._120_MarkOn3;

                }

                break;
            case _120_MarkOn3:
                if(robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(0, 0, 0.0, 0);
                    currentStage = stage._130_Right3;

                }

                break;
            case _130_Right3:
                if(robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(0, 90, 0.2, 0);
                    currentStage = stage._140_Forward3;

                }

                break;
            case _140_Forward3:
                if(robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(0, 0, 0.2, 0);
                    currentStage = stage._150_Left2;

                }

                break;
            case _150_Left2:
                if(robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(0, -90, 0.2, 0);
                    currentStage = stage._160_Backward2;

                }

                break;
            case _160_Backward2:
                if(robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(0, -180, 0.2, 0);
                    currentStage = stage._170_MarkOff3;

                }

                break;
            case _170_MarkOff3:
                if(robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(0, 0, 0.0, 0);
                    currentStage = stage._180_Forward4;

                }

                break;
            case _180_Forward4:
                if(robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(0, 0, 0.2, 0);
                    currentStage = stage._190_Right4;

                }

                break;
            case _190_Right4:
                if(robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(0, 90, 0.2, 0);
                    currentStage = stage._195_MarkOn4;

                }

                break;
            case _195_MarkOn4:
                if(robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(0, 0, 0.0, 0);
                    currentStage = stage._200_HeadingRight;

                }

                break;
            case _200_HeadingRight:
                if(robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(0, 135, 0.2, 0);
                    currentStage = stage._210_MarkOff4;

                }

                break;
            case _210_MarkOff4:
                if(robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(0, 0, 0.0, 0);
                    currentStage = stage._220_Right5;

                }

                break;
            case _220_Right5:
                if(robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(0, 90, 0.2, 0);
                    currentStage = stage._230_MarkOn5;

                }

                break;
            case _230_MarkOn5:
                if(robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(0, 0, 0.0, 0);
                    currentStage = stage._240_Forward5;

                }

                break;
            case _240_Forward5:
                if(robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(0, 0, 0.2, 0);
                    currentStage = stage._245_MarkOff5;

                }

                break;
            case _245_MarkOff5:
                if(robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(0, 0, 0.0, 0);
                    currentStage = stage._250_Left3;

                }

                break;
            case _250_Left3:
                if(robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(0, -90, 0.2, 0);
                    currentStage = stage._260_MarkOn6;

                }

                break;
            case _260_MarkOn6:
                if(robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(0, 0, 0.0, 0);
                    currentStage = stage._270_Right6;

                }

                break;
            case _270_Right6:
                if(robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(0, 90, 0.2, 0);
                    currentStage = stage._280_ExitLeft;

                }

                break;
            case _280_ExitLeft:
                if(robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(0, -90, 0.2, 0);
                    currentStage = stage._290_End;

                }

                break;
            case _290_End:
            {

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
        _20_Forward1,
        _30_Right1,
        _40_Backward1,
        _50_MarkOff1,
        _60_Forward2,
        _70_MarkOn2,
        _80_Left1,
        _100_MarkOff2,
        _110_Right2,
        _120_MarkOn3,
        _130_Right3,
        _140_Forward3,
        _150_Left2,
        _160_Backward2,
        _170_MarkOff3,
        _180_Forward4,
        _190_Right4,
        _195_MarkOn4,
        _200_HeadingRight,
        _210_MarkOff4,
        _220_Right5,
        _230_MarkOn5,
        _240_Forward5,
        _245_MarkOff5,
        _250_Left3,
        _260_MarkOn6,
        _270_Right6,
        _280_ExitLeft,
        _290_End;


    }
}