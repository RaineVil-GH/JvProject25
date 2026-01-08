package src;
import de.tudresden.sumo.cmd.Simulation;
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

    public void createCyclist(int amount, SumoTraciConnection conn) throws Exception
    {
        createCyclist(amount, conn, -1);
    }

    public void createCyclist(int amount, SumoTraciConnection conn, int STD_ID) throws Exception {
        for(int i = 0; i < amount; i++) {
            String id2 = STD_ID + "Cyclist" + count;
            count++;
            Cyclist cc = new Cyclist(id2);
            synchronized (LOCK.CONN_LOCK) {
                conn.do_job_set(Vehicle.add(cc.id, cc.type, cc.routeID, cc.depart , cc.position, cc.speed, cc.departLane));
            }
        }
    }
}
