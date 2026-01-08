package src;
import de.tudresden.sumo.cmd.Simulation;
import it.polito.appeal.traci.SumoTraciConnection;
import de.tudresden.sumo.cmd.Vehicle;

public class Bus extends VehicleModul {

    public Bus (String id)
    {
        super(id);
        this.type = "t_2";
        this.routeID = "r_2";
        this.speed = 13;
        this.depart = 0;
        this.position = 0.0;
        this.departLane = (byte) -2;

    }

    int count = 0;

    public void createBus(int amount, SumoTraciConnection conn) throws Exception
    {
        createBus(amount, conn, -1);
    }

    public void createBus(int amount, SumoTraciConnection conn, int STD_ID) throws Exception {
        for(int i = 0; i < amount; i++) {
            String id2 = STD_ID + "Bus" + count;
            count++;
            Bus b = new Bus(id2);

            synchronized (LOCK.CONN_LOCK) {
                conn.do_job_set(Vehicle.add(b.id, b.type, b.routeID, b.depart, b.position, b.speed, b.departLane));
            }
        }
    }


}
