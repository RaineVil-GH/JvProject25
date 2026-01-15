package src;
import de.tudresden.sumo.cmd.Simulation;
import de.tudresden.sumo.cmd.Vehicle;
import it.polito.appeal.traci.SumoTraciConnection;
public class Motorcycle extends VehicleModul {

    public Motorcycle(String id) {
        super(id);
        this.type = "t_3";
        this.routeID = "r_3";
        this.speed = 15.0;
        this.depart = 0;
        this.position = 0.0;
        this.departLane = (byte) -2;

    }

    static int count = 0;
    public void createMotorcycle(int amount, SumoTraciConnection conn, String route, String color) throws Exception {
        Thread motorcycle = new Thread(() -> {
            try {
                for (int i = 0; i < amount; i++) {
                    String id2 = "Motorcycle" + count;
                    count++;
                    Motorcycle m = new Motorcycle(id2);
                    m.routeID = route;

                    synchronized (LOCK.CONN_LOCK) {
                        conn.do_job_set(Vehicle.add(m.id, m.type, m.routeID, m.depart, m.position, m.speed, m.departLane));
                        conn.do_job_set(Vehicle.setColor(m.id, VehicleModul.getColor(color)));
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        motorcycle.start();
    }

    public void setMotorcycleSpeedLow(SumoTraciConnection conn) throws Exception {
        setSpeedINSumo(conn, 30);
    }

    public void setMotorcycleSpeedMedium(SumoTraciConnection conn) throws Exception {
        setSpeedINSumo(conn, 100);

    }

    public void setMotorcycleSpeedHigh(SumoTraciConnection conn) throws Exception {
        setSpeedINSumo(conn, 150);
    }


}
