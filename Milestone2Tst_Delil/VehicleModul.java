import it.polito.appeal.traci.SumoTraciConnection;
import de.tudresden.sumo.cmd.Vehicle;

public class VehicleModul {


    protected String id;
    protected String type;
    protected String routeID;
    protected int depart;
    protected double position;
    protected double speed;
    protected byte departLane;



    public VehicleModul(String id) {
        this.id = id;
        this.speed = 0.0;
        this.position = 0.0;
        this.routeID = "";
        this.depart = 0;
        this.departLane = 0;
        this.type = "";
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
    /*this.lane = (String) conn.do_job_get(
            Vehicle.getLaneID(this.id)
    );*/
}
public void setSpeedINSumo(SumoTraciConnection conn, double newSpeed) throws Exception{
    this.speed = newSpeed;
    conn.do_job_set(
            Vehicle.setSpeed(this.id, newSpeed)
    );
    }

}
//T0= Bus , T1= Motorrad, T2 = Fahrrad, T3= Menschen, T4= Auto

