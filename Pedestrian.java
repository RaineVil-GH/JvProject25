package src;
import de.tudresden.sumo.cmd.Simulation;
import de.tudresden.sumo.cmd.Vehicle;
import it.polito.appeal.traci.SumoTraciConnection;

public class Pedestrian extends VehicleModul {

    public String edge;

    public Pedestrian(String id) {
        super(id);
        this.type = "t_1";
        this.routeID = "r_5";
        this.speed = 0.1;
        this.depart = 0;
        this.position = 0.0;
        this.edge = "edge_start_ped";
        this.departLane = (byte) 0; //0
    }
    
    static int count = 0;
    public void createPedestrian(int amount, SumoTraciConnection conn, String route, String color) throws Exception {
        Thread pedestrian = new Thread(() -> {
            try {
                for (int i = 0; i < amount; i++) {
                    String id2 = "Pedestrian" + count;
                    count++;
                    Pedestrian p = new Pedestrian(id2);
                    p.routeID = route;

                    synchronized (LOCK.CONN_LOCK) {
                        conn.do_job_set(Vehicle.add(p.id, p.type, p.routeID, p.depart, p.position, p.speed, p.departLane));
                        conn.do_job_set(Vehicle.setColor(p.id, VehicleModul.getColor(color)));
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        pedestrian.start();
    }

/*public void setPedestrianSpeed(SumoTraciConnection conn) throws Exception{
    setSpeedINSumo(conn, 2.5);
    }*/
}
