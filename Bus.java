import de.tudresden.sumo.util.SumoCommand;
import it.polito.appeal.traci.SumoTraciConnection;
import de.tudresden.sumo.cmd.Vehicle;

public class Bus extends VehicleModul {

    public String Color;
    public Bus (String id)
    {
        super(id);
        this.speed = 50;
        this.type = "Bus";
        this.Color = "blue";

    }

    public void setBusSpeedLow(SumoTraciConnection conn) throws Exception {
        setSpeedINSumo(conn, 30);
    }

    public void setBusSpeedMedium(SumoTraciConnection conn) throws Exception {
        setSpeedINSumo(conn, 100);
    }


    public void setBusSpeedHigh(SumoTraciConnection conn) throws Exception {
        setSpeedINSumo(conn, 150);
    }


}
