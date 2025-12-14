import com.sun.jdi.event.StepEvent;
import de.tudresden.sumo.cmd.Vehicle;
import it.polito.appeal.traci.SumoTraciConnection;
import de.tudresden.sumo.cmd.Trafficlight;
import trafficparticipants.Car;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        String SUMO_BIN = "\\home\\abdulrehman\\sumo\\bin\\sumo-gui.exe";
        String SUMO_CFG = "\\home\\abdulrehman\\Downloads\\Text-Java\\TestM2.sumocfg";

        SumoTraciConnection conn = new SumoTraciConnection(SUMO_BIN, SUMO_CFG);
        conn.runServer();

        List<String> TrafficLightsIds = (List<String>) conn.do_job_get(Trafficlight.getIDList());
        System.out.println(TrafficLightsIds + "\nTrafficLights: " + TrafficLightsIds.size());

        List<String> CarIds = (List<String>) conn.do_job_get(Vehicle.getIDList());
        System.out.println(CarIds + "\nCars: " + TrafficLightsIds.size());

        List<TrafficlightModul> trafficlights = new ArrayList<>();
        for(String id : TrafficLightsIds){
            TrafficlightModul tl = new TrafficlightModul(id);
            trafficlights.add(tl);
        }
        for (int i = 0; i < 500 ; i++)
        {
            for (int j = 0; j < TrafficLightsIds.size(); j++) {
                String s = TrafficLightsIds.get(j);
                System.out.println("Trafficlight " + (j+1) + ": " + conn.do_job_get(Trafficlight.getRedYellowGreenState(s)));
            }
            conn.do_timestep();
            System.out.println("Step: " + (i+1));
        }

        trafficlights.get(0).setMultiPhase(conn,"GRrRRgGrrrrr");


        Steps Zaehler = new Steps(conn);
        Zaehler.StepCounter();





    }

}

