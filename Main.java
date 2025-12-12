import de.tudresden.sumo.util.SumoCommand;
import it.polito.appeal.traci.SumoTraciConnection;
import de.tudresden.sumo.cmd.Trafficlight;
import java.util.List;



public class Main {
    public static void main(String[] args) throws Exception {

        String SUMO_BIN = "C:\\Program Files (x86)\\Eclipse\\Sumo\\bin\\sumo-gui.exe";
        String SUMO_CFG = "C:\\Users\\Gulo3\\Desktop\\Melih_Kocatuerk\\Java_SUMO_Projekt\\Projekt.sumocfg";

        SumoTraciConnection conn = new SumoTraciConnection(SUMO_BIN, SUMO_CFG);
        conn.runServer();

        List<String> TrafficLightsIds = (List<String>) conn.do_job_get(Trafficlight.getIDList());
        System.out.println(TrafficLightsIds + "\nTrafficLights: " + TrafficLightsIds.size());

        Steps Zähler = new Steps(conn);
        Zähler.StepCounter();







    }
}