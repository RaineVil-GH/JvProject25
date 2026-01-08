package src;
import de.tudresden.sumo.cmd.Simulation;
import de.tudresden.sumo.cmd.Vehicle;
import it.polito.appeal.traci.SumoTraciConnection;
public class Motorcycle extends VehicleModul {

    public Motorcycle (String id)
    {
        super(id);
        this.type = "t_3";
        this.routeID = "r_3";
        this.speed = 15.0;
        this.depart = 0;
        this.position = 0.0;
        this.departLane = (byte) 2;

    }

    int count = 0;

    public void createMotorcycle(int amount, SumoTraciConnection conn) throws Exception
    {
        createMotorcycle(amount, conn, -1);
    }

    public void createMotorcycle(int amount, SumoTraciConnection conn, int STD_ID) throws Exception {
        for(int i = 0; i < amount; i++) {
            String id2 = STD_ID +  "Motorcycle" + count;
            count++;
            Motorcycle m = new Motorcycle(id2);
            synchronized (LOCK.CONN_LOCK) {
                conn.do_job_set(Vehicle.add(m.id, m.type, m.routeID, m.depart, m.position, m.speed, m.departLane));
            }
        }
    }

}
