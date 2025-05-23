package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.hardware.bosch.BHI260IMU;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.ImuOrientationOnRobot;

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

import java.util.Locale;

public class CommonGyro extends BaseHardware {

    // The IMU sensor object
    IMU imu;
    // BHI260IMU imu;
    // State used for updating telemetry
    Orientation angles;
    int gyroHeading_Current = 0;
    //BHI260IMU.Parameters parameters = new BHI260IMU.Parameters(orientationOnRobot);

    RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.UP;
    RevHubOrientationOnRobot.UsbFacingDirection  usbDirection  = RevHubOrientationOnRobot.UsbFacingDirection.FORWARD;
    RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logoDirection, usbDirection);
    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {

        // Set up the parameters with which we will use our IMU. Note that integration
        // algorithm here just reports accelerations to the logcat log; it doesn't actually
        // provide positional information.
        //BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();



        imu = hardwareMap.get(IMU.class, "imu");
        imu.initialize(new IMU.Parameters(orientationOnRobot));


        // Retrieve and initialize the IMU. We expect the IMU to be attached to an I2C port
        // on a Core Device Interface Module, configured to be a sensor of type "AdaFruit IMU",
        // and named "imu".
        //imu = hardwareMap.get(BNO055IMU.class, "imu");
       // imu = hardwareMap.get(BHI260IMU.class, "imu");
        //imu.initialize(parameters);

    }

    //*********************************************************************************************
    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    //*********************************************************************************************
    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {

    }

    //*********************************************************************************************
    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        // It has been discovered that we do not need to continually retrive the value from
        // the gyro in the loop function.  When needed the calling class will get the most
        // current gyro heading with getGyroHeading.

        //updateGyroHeading();

    }

    //*********************************************************************************************
    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

    //*********************************************************************************************
    public int gyroNormalize(int heading) {
        // takes the full turns out of heading
        // gives us values from 0 to 180 for the right side of the robot
        // and values from 0 to -179 degrees for the left side of the robot

        int degrees = heading % 360;

        if (degrees > 180) {
            degrees = degrees - 360;
        }

        if (degrees < -179) {
            degrees = degrees + 360;
        }

        return (degrees);
    }

    //*********************************************************************************************
    public boolean gyroInTol(int currHeading, int desiredHeading, int tol) {

        int upperTol = gyroNormalize(desiredHeading + tol);
        int lowerTol = gyroNormalize(desiredHeading - tol);
        int normalCurr = gyroNormalize(currHeading);

        float signumUpperTol = Math.signum(upperTol);
        float signumLowerTol = Math.signum(lowerTol);

        boolean retValue = false;
        // works for all positive numbers direction values
        if (signumUpperTol > 0 && signumLowerTol > 0) {
            if ((normalCurr >= lowerTol) && (normalCurr <= upperTol)) {
                retValue = true;
            }
        }

        // works for negative values
        else if (signumUpperTol < 0 && signumLowerTol < 0) {
            if ((normalCurr >= lowerTol) && (normalCurr <= upperTol)) {
                retValue = true;
            }
        }
        // mixed values -tol to + tol  This happens at 180 degrees
        else if ((signumUpperTol < 0) && (signumLowerTol > 0)) {
            // System.out.println("upperTol " + upperTol + " Current " +
            // normalCurr + " lowerTol " + lowerTol);
            if ((Math.abs(normalCurr) >= Math.abs(lowerTol)) &&
                    (Math.abs(normalCurr) >= Math.abs(upperTol))) {
                retValue = true;
            }

        }
        // mixed values -tol to + tol  This happens at 0 degrees
        else if ((signumUpperTol > 0) && (signumLowerTol < 0)) {
            // System.out.println("upperTol " + upperTol + " Current " +
            // normalCurr + " lowerTol " + lowerTol);
            if ((Math.abs(normalCurr) <= Math.abs(lowerTol)) &&
                    (Math.abs(normalCurr) <= Math.abs(upperTol))) {
                retValue = true;
            }

        }
        return (retValue);
    }  // end gyroInTol()

    // create command to be called from auton to reset encoders at end of auton
    //*********************************************************************************************
    private void updateGyroHeading() {
        //Read the gyro and return its reading in degrees

        //this should pull heading angle from onboard IMU Gyro
        //https://ftcforum.usfirst.org/forum/ftc-technology/49904-help-with-rev-expansion-hub-integrated-gyro
        //hint: composeTelemetry() also captures this information below.

        angles = imu.getRobotOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        //return formatAngle(angles.angleUnit, angles.firstAngle);
        gyroHeading_Current = -1 * (int) (angles.firstAngle);
    }

    //*********************************************************************************************
    public int getGyroHeading() {
        updateGyroHeading();
        return gyroHeading_Current;
    }
    public double getGyroHeadingRadian() {
        angles = imu.getRobotOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS);
        return(-1 * (angles.firstAngle));
    }

    //*********************************************************************************************
    public int deltaHeading(int currHeading, int targetHeading) {
        int returnValue = 0;
        if (currHeading >= 0 && targetHeading >= 0) {
            returnValue = targetHeading - currHeading;
        } else if (currHeading >= 0 && targetHeading <= 0) {
            returnValue = targetHeading + currHeading;
        } else if (currHeading <= 0 && targetHeading >= 0) {
            returnValue = -1 * (targetHeading + currHeading);
        } else if (currHeading <= 0 && targetHeading <= 0) {
            returnValue = (targetHeading - currHeading);
        }

        return returnValue;
    }

    //*********************************************************************************************
    void composeTelemetry() {

        // At the beginning of each telemetry update, grab a bunch of data
        // from the IMU that we will then display in separate lines.
        telemetry.addAction(new Runnable() {
            @Override
            public void run() {
                // Acquiring the angles is relatively expensive; we don't want
                // to do that in each of the three items that need that info, as that's
                // three times the necessary expense.
                angles = imu.getRobotOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
            }
        });

        telemetry.addLine()
                .addData("status", new Func<String>() {
                    @Override
                    public String value() {
                        return imu.getConnectionInfo().toString();
                    }
                })
                .addData("calib", new Func<String>() {
                    @Override
                    public String value() {
                        return imu.getConnectionInfo().toString();
                    }
                });

        telemetry.addLine()
                .addData("heading", new Func<String>() {
                    @Override
                    public String value() {
                        return formatAngle(angles.angleUnit, angles.firstAngle);
                    }
                })
                .addData("roll", new Func<String>() {
                    @Override
                    public String value() {
                        return formatAngle(angles.angleUnit, angles.secondAngle);
                    }
                })
                .addData("pitch", new Func<String>() {
                    @Override
                    public String value() {
                        return formatAngle(angles.angleUnit, angles.thirdAngle);
                    }
                });
    }

    //*********************************************************************************************
    String formatAngle(AngleUnit angleUnit, double angle) {
        return formatDegrees(AngleUnit.DEGREES.fromUnit(angleUnit, angle));
    }

    //----------------------------------------------------------------------------------------------
    // Formatting
    //----------------------------------------------------------------------------------------------
    String formatDegrees(double degrees) {
        return String.format(Locale.getDefault(), "%.1f", AngleUnit.DEGREES.normalize(degrees));
    }
    //*********************************************************************************************
    public void GyroInt (){
        imu.initialize(new IMU.Parameters(orientationOnRobot));
    }
}
