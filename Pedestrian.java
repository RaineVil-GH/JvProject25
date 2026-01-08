package src;
import de.tudresden.sumo.cmd.Simulation;
import de.tudresden.sumo.cmd.Vehicle;
import it.polito.appeal.traci.SumoTraciConnection;

public class Pedestrian extends VehicleModul {

    public String edge;

    public Pedestrian(String id) {
        super(id);
        this.type = "t_1";
        this.speed = 1.0;
        this.depart = 0;
        this.position = 0.0;
        this.edge = "edge_start_ped";
        this.departLane = (byte) 0;
    }

    int count = 0;

    public void createPedestrian(int amount, SumoTraciConnection conn) throws Exception
    {
        createPedestrian(amount, conn, -1);
    }

    public void createPedestrian(int amount, SumoTraciConnection conn, int STD_ID) throws Exception {
        for(int i = 0; i < amount; i++) {
            String id2 = STD_ID + "Pedestrian" + count;
            count++;
            Pedestrian p = new Pedestrian(id2);
            synchronized (LOCK.CONN_LOCK) {
                conn.do_job_set(Vehicle.add(p.id, p.type, p.routeID, p.depart, p.position, p.speed, p.departLane));
            }
        }
    }
}
