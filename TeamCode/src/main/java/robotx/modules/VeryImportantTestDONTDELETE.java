package robotx.modules;

/*Example*/

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import robotx.libraries.XModule;
import robotx.opmodes.autonomous.OpMode2021v2;

public class VeryImportantTestDONTDELETE extends XModule {
    DcMotor MyMotor;

    public VeryImportantTestDONTDELETE(OpMode op) {
        super(op);
    }

    public void init() {
        MyMotor = opMode.hardwareMap.dcMotor.get("MyMotor");
    }
    public void myMotor() {
        if (xGamepad1().x.isDown()){
            MyMotor.setPower(20);
        }
    }
}