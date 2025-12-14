import de.tudresden.sumo.cmd.Vehicle;
import it.polito.appeal.traci.SumoTraciConnection;

public class Car extends VehicleModul {


    public int depart;
    double position;
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
    int count = 0;
    public void createCar(int amount, SumoTraciConnection conn) throws Exception {
        for(int i = 0; i < amount; i++) {
            String id2 = "Car" + count;
            count++;
            Car c = new Car(id2);
            conn.do_job_set(Vehicle.add(c.id, c.type, c.routeID, c.depart, c.position, c.speed, c.departLane));
        }
    }

    /*public void setCarSpeedLow(SumoTraciConnection conn) throws Exception {
        setSpeedINSumo(conn, 50);
    }

    public void setCarSpeedMedium(SumoTraciConnection conn) throws Exception {
        setSpeedINSumo(conn, 100);
    }


    public void setCarSpeedHigh(SumoTraciConnection conn) throws Exception {
        setSpeedINSumo(conn, 150);
    }*/

}
