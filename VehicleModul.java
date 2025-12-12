package trafficparticipants;

import it.polito.appeal.traci.SumoTraciConnection;
import de.tudresden.sumo.cmd.Vehicle;

public class VehicleModul {


    protected double speed;
    protected String id;
    protected String type;
    protected double position;
    protected String lane;
    protected String routeId;



    public VehicleModul(String id) {
        this.id = id;
        this.speed = 0.0;
        this.position = 0.0;
        this.lane = "";
        this.type = "";
        this.routeId = "";
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getPosition() {
        return position;
    }

    public void setPosition(double position) {
        this.position = position;
    }

public void updateFromSumo(SumoTraciConnection conn) throws Exception {
    this.speed = (double) conn.do_job_get(
            Vehicle.getSpeed(this.id)
    );
    this.position = (double) conn.do_job_get(
            Vehicle.getPosition(this.id)
    );
    this.lane = (String) conn.do_job_get(
            Vehicle.getLaneID(this.id)
    );
}
public void setSpeedINSumo(SumoTraciConnection conn, double newSpeed) throws Exception{
    this.speed = newSpeed;
    conn.do_job_set(
            Vehicle.setSpeed(this.id, newSpeed)
    );
    }

}
//T0= Bus , T1= Motorrad, T2 = Fahrrad, T3= Menschen, T4= Auto

