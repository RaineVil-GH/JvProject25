package src;
import de.tudresden.sumo.cmd.Simulation;
import de.tudresden.sumo.cmd.Trafficlight;
import de.tudresden.sumo.cmd.Vehicle;
import it.polito.appeal.traci.SumoTraciConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;


import java.awt.*;

public class Car extends VehicleModul {


    public static List<String> carIds = Collections.synchronizedList(new ArrayList<>());
    public int depart;
    public double position;
    public Car (String id)
    {
        super(id);
        this.type = "t_5";
        this.routeID = "r_0";
        this.speed = 13;
        this.depart = 0;
        this.position = 0.0;
        this.departLane = (byte) -2;
    }

    static int count = 0;
    public void createCar(int amount, SumoTraciConnection conn, String route, String color) throws Exception {
        Thread cars = new Thread(() -> {
            try
            {
                for(int i = 0; i < amount; i++) {
                    String id2 = "Car" + count;
                    count++;
                    Car c = new Car(id2);
                    c.routeID = route;

                    synchronized (LOCK.CONN_LOCK) {
                        conn.do_job_set(Vehicle.add(c.id, c.type, c.routeID, c.depart , c.position, c.speed, c.departLane));
                        conn.do_job_set(Vehicle.setColor(c.id, VehicleModul.getColor(color)));
                    }
                    Car.carIds.add(c.id);
                }

            }
            catch  (Exception e) {
                e.printStackTrace();
            }

        });

        cars.start();
    }

}
