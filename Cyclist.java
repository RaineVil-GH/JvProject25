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
        this.departLane = (byte) -2; //1

    }
    
    static int count = 0;
    public void createCyclist(int amount, SumoTraciConnection conn, String route, String color) throws Exception {
        Thread cyclist = new Thread(() -> {
            try {
                for (int i = 0; i < amount; i++) {
                    String id2 = "Cyclist" + count;
                    count++;
                    Cyclist c = new Cyclist(id2);
                    c.routeID = route;

                    synchronized (LOCK.CONN_LOCK) {
                        conn.do_job_set(Vehicle.add(c.id, c.type, c.routeID, c.depart, c.position, c.speed, c.departLane));
                        conn.do_job_set(Vehicle.setColor(c.id, VehicleModul.getColor(color)));
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        cyclist.start();
    }

    public void setCyclistSpeed(SumoTraciConnection conn) throws Exception{
        setSpeedINSumo(conn, 5.0);
    }
}
