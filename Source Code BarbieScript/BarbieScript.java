package Os_FakeNatty;
import robocode.*;
import java.awt.Color;

public class BarbieScript extends AdvancedRobot
{
  int moveDirection=1;
    public void run() {
        setAdjustRadarForRobotTurn(true);
        setBodyColor(Color.magenta);
        setGunColor(Color.white);
        setRadarColor(Color.magenta);
        setScanColor(Color.white);
        setBulletColor(Color.magenta);
        setAdjustGunForRobotTurn(true);
        turnRadarRightRadians(Double.POSITIVE_INFINITY); 
    }

   
    public void onScannedRobot(ScannedRobotEvent e) {
        double absBearing=e.getBearingRadians()+getHeadingRadians();
        double latVel=e.getVelocity() * Math.sin(e.getHeadingRadians() -absBearing);
        double gunTurnAmt;
        setTurnRadarLeftRadians(getRadarTurnRemainingRadians());
        if(Math.random()>.9){
            setMaxVelocity((12*Math.random())+12);
        }
        if (e.getDistance() > 150) {
            gunTurnAmt = robocode.util.Utils.normalRelativeAngle(absBearing- getGunHeadingRadians()+latVel/22);
            setTurnGunRightRadians(gunTurnAmt);  
            setTurnRightRadians(robocode.util.Utils.normalRelativeAngle(absBearing-getHeadingRadians()+latVel/getVelocity()));
            setAhead((e.getDistance() - 140)*moveDirection);
            setFire(3);
        }
        else{
            gunTurnAmt = robocode.util.Utils.normalRelativeAngle(absBearing- getGunHeadingRadians()+latVel/15);
            setTurnGunRightRadians(gunTurnAmt);
            setTurnLeft(-90-e.getBearing());
            setAhead((e.getDistance() - 140)*moveDirection);
            setFire(3);
        }
    }
    public void onHitWall(HitWallEvent e){
        moveDirection=-moveDirection;
    }
   
    public void onWin(WinEvent e) {
        for (int i = 0; i < 50; i++) {
            turnRight(30);
            turnLeft(30);
        }
    }
}