package robotx.modules;

import android.view.KeyEvent;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

import robotx.libraries.XModule;

public class GolfMotorServo extends XModule {

    DcMotor liftMotor;
    DcMotor dropperMotor;

    Servo liftServo;
    Servo dropperServo;
    Servo pushServo;
    Servo plasticServo;
    DigitalChannel liftTouch;
    DigitalChannel upTouch;
    DigitalChannel downTouch;

    double power = .7;
    double power2 = .4;

    public boolean slowMode = false;
    boolean touched = false;
    boolean upTouched = false;
    boolean downTouched = false;
    boolean closed3 = true;
    boolean closed2 = true;
    boolean closed = true;
    boolean pushed = true;

    public GolfMotorServo(OpMode op) {
        super(op);
    }

    public void init() {
        liftMotor = opMode.hardwareMap.dcMotor.get("LiftMotor");
        dropperMotor = opMode.hardwareMap.dcMotor.get("DropperMotor");
        pushServo = opMode.hardwareMap.servo.get("PushMotor");
        liftServo = opMode.hardwareMap.servo.get("liftServo");
        dropperServo = opMode.hardwareMap.servo.get("DropperServo");
        plasticServo = opMode.hardwareMap.servo.get("PlasticServo");

        liftTouch = opMode.hardwareMap.get(DigitalChannel.class, "liftTouch");
        liftTouch.setMode(DigitalChannel.Mode.INPUT);
        upTouch = opMode.hardwareMap.get(DigitalChannel.class, "upTouch");
        upTouch.setMode(DigitalChannel.Mode.INPUT);
        downTouch = opMode.hardwareMap.get(DigitalChannel.class, "downTouch");
        downTouch.setMode(DigitalChannel.Mode.INPUT);
    }

    public void toggleSlow() {
        if (slowMode) {
            slowMode = false;
        } else {
            slowMode = true;
        }
    }

    public void LiftServo() {
        if (!closed) {
            liftServo.setPosition(1);
            closed = true;
        } else {
            liftServo.setPosition(0.7);
            closed = false;
        }
    }

    public void DropperServo() {
        if (!closed2) {
            dropperServo.setPosition(0.5);
            closed2 = true;
        } else {
            dropperServo.setPosition(0.07);
            closed2 = false;
        }
    }

    public void PushServo() {
        if (!closed3) {
            pushServo.setPosition(.75);
            closed3 = true;
        } else {
            pushServo.setPosition(.25);
            closed3 = false;
        }
    }



    public void loop() {
        if (xGamepad2().start.wasPressed()){
            toggleSlow();
        }

        if (liftTouch.getState() == true){

            liftMotor.setPower(0);
            liftMotor.setPower(-0.3);
            touched = false;
        }

        if (upTouch.getState() == true){

            dropperMotor.setPower(0);
            dropperMotor.setPower(0.3);
            upTouched = false;

        }

        if (downTouch.getState() == true){

            dropperMotor.setPower(0);
            dropperMotor.setPower(-0.3);
            downTouched = false;

        }


        if (liftTouch.getState() == true){

            liftMotor.setPower(0);
            liftMotor.setPower(-0.3);

        }

        if (xGamepad2().x.isDown()) {
            if (slowMode){
                liftMotor.setPower(power2 / 2);
            }
            else {
                liftMotor.setPower(power2);
            }
        }

        else if (xGamepad2().y.isDown()) {
            if (slowMode){
                liftMotor.setPower(-power2 / 2);
            }
            else {
                liftMotor.setPower(-power2);
            }
        }

        else {
            liftMotor.setPower(0.0);
        }

        if (xGamepad2().a.isDown()) {
            dropperMotor.setPower(power);
        }

        else if (xGamepad2().b.isDown()) {
            dropperMotor.setPower(-power);
        }

        else {
            dropperMotor.setPower(0.0);
        }

        if (xGamepad2().right_stick_button.wasPressed()){
            LiftServo();
        }
        if (xGamepad2().right_bumper.wasPressed()){
            DropperServo();
        }
        if (xGamepad2().dpad_up.wasPressed()) {
            if (pushed = true) {
                plasticServo.setPosition(0.1);
                pushed = false;
            }
            else{
                plasticServo.setPosition(0.25);
                pushed = true;
                }
            }
        }
    }
