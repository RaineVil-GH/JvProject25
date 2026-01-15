import de.tudresden.sumo.cmd.Simulation;
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

    public static String[] routes = {"r_0", "r_1", "r_2", "r_3"};
    public static String[] color = {"Red", "Blue", "Green", "Yellow", "Purple"};

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
    synchronized (LOCK.CONN_LOCK)
    {
        this.speed = (double) conn.do_job_get(
                Vehicle.getSpeed(this.id)
        );
    }
    synchronized (LOCK.CONN_LOCK)
    {
        this.position = (double) conn.do_job_get(
                Vehicle.getPosition(this.id)
        );
    }

    public static SumoColor getColor(String color) {
        switch (color) {
            case "Red":
                return new SumoColor(255, 0, 0, 255);
            case "Blue":
                return new SumoColor(0, 0, 255, 255);
            case "Green":
                return new SumoColor(0, 255, 0, 255);
            case "Yellow":
                return new SumoColor(255, 255, 0, 255);
            case "Purple":
                return new SumoColor(128, 0, 128, 255);
            default:
                return new SumoColor(255, 255, 255, 255);
        }

    }
    /*this.lane = (String) conn.do_job_get(
            Vehicle.getLaneID(this.id)
    );*/
}
public void setSpeedINSumo(SumoTraciConnection conn, double newSpeed) throws Exception{
    this.speed = newSpeed;
    synchronized (LOCK.CONN_LOCK)
    {
    conn.do_job_set(
            Vehicle.setSpeed(this.id, newSpeed)
    );
    }
    }

}
//T0= Bus , T1= Motorrad, T2 = Fahrrad, T3= Menschen, T4= Auto

