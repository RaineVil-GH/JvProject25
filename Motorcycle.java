import de.tudresden.sumo.cmd.Vehicle;
import it.polito.appeal.traci.SumoTraciConnection;

public class Motorcycle extends VehicleModul {


    public String Color;
    public Motorcycle (String id)
    {
        super(id);
        this.speed = 50;
        this.type = "Motorcycle";
        this.Color = "blue";

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
