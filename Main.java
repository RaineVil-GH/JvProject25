
import de.tudresden.sumo.cmd.Vehicle;
import it.polito.appeal.traci.SumoTraciConnection;
import de.tudresden.sumo.cmd.Trafficlight;
import java.util.List;

import javax.swing.SwingUtilities;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        String SUMO_BIN = "C:\\Program Files (x86)\\Eclipse\\Sumo\\bin\\sumo-gui.exe";
        String SUMO_CFG = "C:\\Users\\delil\\Java_SUMO_Projekt_Final\\TestM2.sumocfg";

        SumoTraciConnection conn = new SumoTraciConnection(SUMO_BIN, SUMO_CFG);
        conn.runServer();

        List<String> TrafficLightsIds = (List<String>) conn.do_job_get(Trafficlight.getIDList());
        System.out.println(TrafficLightsIds + "\nTrafficLights: " + TrafficLightsIds.size());


        Car c1 = new Car("c");
        c1.createCar(10, conn);

        Pedestrian p1 = new Pedestrian("p");
        p1.createPedestrian(10, conn);

        Motorcycle m1 = new Motorcycle("m");
        m1.createMotorcycle(10, conn);

        Cyclist cc1 = new Cyclist("cc");
        cc1.createCyclist(10, conn);

        Bus b1 = new Bus("b");
        b1.createBus(10, conn);


        List<TrafficlightModul> trafficlights = new ArrayList<>();
        for(String id : TrafficLightsIds) {
            TrafficlightModul tl = new TrafficlightModul(id);
            trafficlights.add(tl);
        }
        trafficlights.get(0).setMultiPhase(conn,"GGGGGGGGGGGG");
        
        //Starting the GUI and the Visualisation
		new GuiSumoBridge().startGui();
        
        Steps s1 = new Steps(conn, TrafficLightsIds);
        s1.StepCounter();
       
        //Simulating the trafficlights and cars
        new GuiSumoBridge().ongoingGui();
        
        synchronized (LOCK.CONN_LOCK) {
            List<String> CarIds = (List<String>) conn.do_job_get(Vehicle.getIDList());
            System.out.println(CarIds + "\nVehicles: " + CarIds.size());

        }
			
    }
}