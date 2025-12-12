import it.polito.appeal.traci.SumoTraciConnection;

public class Car extends VehicleModul {


    public String Color;
    public Car (String id)
    {
        super(id);
        this.speed = 50;
        this.type = "car";
        this.Color = "blue";

    }

    public void setCarSpeedLow(SumoTraciConnection conn) throws Exception {
        setSpeedINSumo(conn, 30);
    }

    public void setCarSpeedMedium(SumoTraciConnection conn) throws Exception {
        setSpeedINSumo(conn, 100);
    }


    public void setCarSpeedHigh(SumoTraciConnection conn) throws Exception {
        setSpeedINSumo(conn, 150);
    }

}
