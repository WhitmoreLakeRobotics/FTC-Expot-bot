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

public class Parthenon extends OpMode {

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
             /*
        switch (currentStage){
            case  _unknown:
                currentStage = stage._00_preStart;
                break;
            case _00_preStart:
                currentStage = stage._10_marker_down1;
                break;
            case _10_marker_down1:
                robot.driveTrain.CmdDrive(8,0,0.35,0);
                currentStage = stage._20_drive_right1;
                break;
            case _20_drive_right1:
                if(robot.driveTrain.getCmdComplete()){
                    robot.driveTrain.CmdDrive(0,0,0.0,0);
                    currentStage = stage._30_marker_up1;

                }
                break;
            case _30_marker_up1:
                if (robot.driveTrain.getCmdComplete())     {
                    robot.driveTrain.CmdDrive(12,-90,0.35,0);
                    currentStage = stage._40_forward1;
                }
                break;
            case _40_forward1:
                if (robot.driveTrain.getCmdComplete())  {
                    robot.driveTrain.CmdDrive(12,90,0.35,90);
                    currentStage = stage._50_drive_left1;
                }
                break;
            case _50_drive_left1:
                if (robot.driveTrain.getCmdComplete()){
                    robot.driveTrain.CmdDrive(0,0,0.0,0);
                    currentStage = stage._60_marker_down2;
                }

                break;
            case _60_marker_down2:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(10,-180,0.35,0);
                    currentStage = stage._70_drive_left2;
                }
                break;
            case _70_drive_left2:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(10,-180,0.35,0);
                    currentStage = stage._80_marker_up2;
                }
                break;
            case _80_marker_up2:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(10,-180,0.35,0);
                    currentStage = stage._100_forward2;
                }
                break;
            case _100_forward2:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(10,-180,0.35,0);
                    currentStage = stage._110_drive_right2;
                }
                break;
            case _110_drive_right2:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(10,-180,0.35,0);
                    currentStage = stage._120_marker_down3;
                }
                break;
            case _120_marker_down3:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(10,-180,0.35,0);
                    currentStage = stage._130_drive_right3;
                }
                break;
            case _130_drive_right3:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(10,-180,0.35,0);
                    currentStage = stage._140_marker_up3;
                }
                break;
            case _140_marker_up3:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(10,-180,0.35,0);
                    currentStage = stage._150_forward3;
                }
                break;
            case _150_forward3:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(10,-180,0.35,0);
                    currentStage = stage._160_drive_left3;
                }
                break;
            case _160_drive_left3:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(10,-180,0.35,0);
                    currentStage = stage._170_marker_down4;
                }
                break;
            case _170_marker_down4:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(10,-180,0.35,0);
                    currentStage = stage._180_backward1;
                }
                break;
            case _180_backward1:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(10,-180,0.35,0);
                    currentStage = stage._190_marker_up4;
                }
                break;
            case _190_marker_up4:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(10,-180,0.35,0);
                    currentStage = stage._200_drive_left4;
                }
                break;
            case _200_drive_left4:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(10,-180,0.35,0);
                    currentStage = stage._210_marker_down5;
                }
                break;
            case _220_forward4:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(10,-180,0.35,0);
                    currentStage = stage._230_marker_up5;
                }
                break;
            case _230_marker_up5:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(10,-180,0.35,0);
                    currentStage = stage._240_drive_left5;
                }
                break;
            case _240_drive_left5:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(10,-180,0.35,0);
                    currentStage = stage._250_marker_down6;
                }
                break;
            case _250_marker_down6:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(10,-180,0.35,0);
                    currentStage = stage._260_backward2;
                }
                break;
            case _260_backward2:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(10,-180,0.35,0);
                    currentStage = stage._270_marker_up6;
                }
                break;
            case _270_marker_up6:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(10,-180,0.35,0);
                    currentStage = stage._280_drive_left6;
                }
                break;
            case _280_drive_left6:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(10,-180,0.35,0);
                    currentStage = stage._290_marker_down7;
                }
                break;
            case _290_marker_down7:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(10,-180,0.35,0);
                    currentStage = stage._300_forward5;
                }
                break;
            case _300_forward5:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(10,-180,0.35,0);
                    currentStage = stage._310_marker_up7;
                }
                break;
            case _310_marker_up7:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(10,-180,0.35,0);
                    currentStage = stage._320_drive_left7;
                }
                break;
            case _320_drive_left7:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(10,-180,0.35,0);
                    currentStage = stage._330_marker_down8;
                }
                break;
            case _330_marker_down8:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(10,-180,0.35,0);
                    currentStage = stage._340_backward3;
                }
                break;
            case _340_backward3:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(10,-180,0.35,0);
                    currentStage = stage._350_marker_up8;
                }
                break;
            case _350_marker_up8:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(10,-180,0.35,0);
                    currentStage = stage._360_drive_left8;
                }
                break;
            case _360_drive_left8:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(10,-180,0.35,0);
                    currentStage = stage._370_marker_down9;
                }
                break;
            case _370_marker_down9:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(10,-180,0.35,0);
                    currentStage = stage._380_forward6;
                }
                break;
            case _380_forward6:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(10,-180,0.35,0);
                    currentStage = stage._390_marker_up9;
                }
                break;
            case _390_marker_up9:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(10,-180,0.35,0);
                    currentStage = stage._400_drive_left9;
                }
                break;
            case _400_drive_left9:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(10,-180,0.35,0);
                    currentStage = stage._410_marker_down10;
                }
                break;
            case _410_marker_down10:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(10,-180,0.35,0);
                    currentStage = stage._420_backward4;
                }
                break;
            case _420_backward4:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(10,-180,0.35,0);
                    currentStage = stage._430_marker_up10;
                }
                break;
            case _430_marker_up10:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(10,-180,0.35,0);
                    currentStage = stage._440_drive_left10;
                }
                break;
            case _440_drive_left10:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(10,-180,0.35,0);
                    currentStage = stage._450_forward7;
                }
                break;
            case _450_forward7:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(10,-180,0.35,0);
                    currentStage = stage._455_marker_down;
                }
                break;
            case _455_marker_down:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(10,-180,0.35,0);
                    currentStage = stage._460_drive_right4;
                }
                break;
            case _460_drive_right4:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(10,-180,0.35,0);
                    currentStage = stage._470_marker_up11;
                }
                break;
            case _470_marker_up11:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(10,-180,0.35,0);
                    currentStage = stage._480_drive_right5;
                }
                break;
            case _480_drive_right5:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(10,-180,0.35,0);
                    currentStage = stage._490_forward8;
                }
                break;
            case _490_forward8:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(10,-180,0.35,0);
                    currentStage = stage._500_marker_down;
                }
                break;
            case _500_marker_down:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(10,-180,0.35,0);
                    currentStage = stage._510_leftward1;
                }
                break;
            case _510_leftward1:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(10,-180,0.35,0);
                    currentStage = stage._520_leftdown1;
                }
                break;
            case _520_leftdown1:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(10,-180,0.35,0);
                    currentStage = stage._530_drive_right6;
                }
                break;
            case _530_drive_right6:
                if (robot.driveTrain.getCmdComplete()) {
                    robot.driveTrain.CmdDrive(10,-180,0.35,0);
                    currentStage = stage._540_End;
                }





                break;
            case _540_End:
                if(robot.driveTrain.getCmdComplete()){
                    robot.stop();


                }







                break;
        }

*/

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
        _10_marker_down1,
        _20_drive_right1,
        _30_marker_up1,
        _40_forward1,
        _50_drive_left1,
        _60_marker_down2,
        _70_drive_left2,
        _80_marker_up2,
        _100_forward2,
        _110_drive_right2,
        _120_marker_down3,
        _130_drive_right3,
        _140_marker_up3,
        _150_forward3,
        _160_drive_left3,
        _170_marker_down4,
        _180_backward1,
        _190_marker_up4,
        _200_drive_left4,
        _210_marker_down5,
        _220_forward4,
        _230_marker_up5,
        _240_drive_left5,
        _250_marker_down6,
        _260_backward2,
        _270_marker_up6,
        _280_drive_left6,
        _290_marker_down7,
        _300_forward5,
        _310_marker_up7,
        _320_drive_left7,
        _330_marker_down8,
        _340_backward3,
        _350_marker_up8,
        _360_drive_left8,
        _370_marker_down9,
        _380_forward6,
        _390_marker_up9,
        _400_drive_left9,
        _410_marker_down10,
        _420_backward4,
        _430_marker_up10,
        _440_drive_left10,
        _450_forward7,
        _455_marker_down,
        _460_drive_right4,
        _470_marker_up11,
        _480_drive_right5,
        _490_forward8,
        _500_marker_down,
        _510_leftward1,
        _520_leftdown1,
        _530_drive_right6,
        _540_End
    }
}