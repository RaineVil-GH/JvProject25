package trafficparticipants;

import it.polito.appeal.traci.SumoTraciConnection;

public class Cyclist extends VehicleModul {
    public Cyclist(String id) {
        super(id);
        this.speed = 5.0;
    }

    public void setCyclistSpeed(SumoTraciConnection conn) throws Exception{
        setSpeedINSumo(conn, 5.0);
    }
}
