import de.tudresden.sumo.cmd.Trafficlight;
import it.polito.appeal.traci.SumoTraciConnection;

import java.util.List;

public class Steps {
    public SumoTraciConnection conn;
    public List<String> TrafficLightsIds;

    public Steps(SumoTraciConnection conn, List<String> TrafficLightsIds)
    {
        this.conn = conn;
        this.TrafficLightsIds = TrafficLightsIds;

    }

   public void StepCounter()
    {

        Thread steps = new Thread(() -> {

            int i = 0;
            try
            {
                    while (conn.isClosed() == false) {


                    synchronized (LOCK.CONN_LOCK)
                    {
                        conn.do_timestep();
                    }
                        for (int j = 0; j < TrafficLightsIds.size(); j++) {
                            String s = TrafficLightsIds.get(j);
                            System.out.println(conn.do_job_get(Trafficlight.getRedYellowGreenState(s)));
                        }
                    System.out.println("Step " + i);
                    i++;
                    }
            }
            catch  (Exception e) {
                e.printStackTrace();
            }

        });
    steps.start();
    }
}
