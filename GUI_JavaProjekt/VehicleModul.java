package trafficparticipants;

import it.polito.appeal.traci.SumoTraciConnection;
import de.tudresden.sumo.cmd.Vehicle;

public class VehicleModul {


    public double speed;
    protected String id;
    protected String type;
    publie double position;
    protected String lane;
    protected String routeId;
    public String id;
    public Edges crntEdge;
    public int crntLaneIdx;
    
    
    
    public VehicleModul(String id, Edges edge, int laneIdx, double maxSpd) {
        this.id = id;
        this.speed = 0.0;
        this.position = 0;
        this.crntLaneIdx = laneIdx;
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

