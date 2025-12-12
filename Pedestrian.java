package trafficparticipants;

import it.polito.appeal.traci.SumoTraciConnection;

public class Pedestrian extends VehicleModul {
    public Pedestrian(String id) {
        super(id);
        this.speed = 2.5;
    }

public void setPedestrianSpeed(SumoTraciConnection conn) throws Exception{
    setSpeedINSumo(conn, 2.5);
    }
}
