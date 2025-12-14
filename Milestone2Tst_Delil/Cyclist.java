import de.tudresden.sumo.cmd.Vehicle;
import it.polito.appeal.traci.SumoTraciConnection;

public class Cyclist extends VehicleModul {

    public Cyclist(String id) {
        super(id);
        this.type = "t_4";
        this.routeID = "r_4";
        this.speed = 5.0;
        this.depart = 0;
        this.position = 0.0;
        this.departLane = (byte) 1;

    }

    int count = 0;
    public void createCyclist(int amount, SumoTraciConnection conn) throws Exception {
        for(int i = 0; i < amount; i++) {
            String id2 = "Cyclist" + count;
            count++;
            Cyclist cc = new Cyclist(id2);
            conn.do_job_set(Vehicle.add(cc.id, cc.type, cc.routeID, cc.depart, cc.position, cc.speed, cc.departLane));
        }
    }

    public void setCyclistSpeed(SumoTraciConnection conn) throws Exception{
        setSpeedINSumo(conn, 5.0);
    }
}
