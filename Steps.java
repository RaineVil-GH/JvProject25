package src;
import de.tudresden.sumo.cmd.Trafficlight;
import de.tudresden.sumo.cmd.Vehicle;
import it.polito.appeal.traci.SumoTraciConnection;

import java.util.List;

public class Steps {
    public SumoTraciConnection conn;
    public List<String> TrafficLightsIds;
    public Boolean play;


    public Steps(SumoTraciConnection conn, List<String> trafficLightsIds, boolean play)
    {
        this.conn = conn;
        this.TrafficLightsIds = trafficLightsIds;
        this.play = play;

    }

    public void Play_stop(boolean ps)
    {
        play = ps;
    }





    public void StepCounter()
    {


        Thread steps = new Thread(() -> {

            int i = 0;
            int Highest_Amount_Cars = 0;

            try
            {
                while (conn.isClosed() == false) {
                    while (play == false) {
                        System.out.print("");
                    }



                    synchronized (LOCK.CONN_LOCK)
                    {
                        conn.do_timestep();
                    }

                    for (int j = 0; j < TrafficLightsIds.size(); j++) {
                        String s = TrafficLightsIds.get(j);
                        System.out.println(conn.do_job_get(Trafficlight.getRedYellowGreenState(s)));
                    }


                    List<String> VehicleIds = (List<String>) conn.do_job_get(Vehicle.getIDList());
                    System.out.println(VehicleIds + "\nVehicleIds: " + VehicleIds.size());

                    if(Highest_Amount_Cars < VehicleIds.size())
                    {
                        Highest_Amount_Cars = VehicleIds.size();
                    }

                    System.out.println("\nCurrent Highest Amount of Vehicles: " + Highest_Amount_Cars);
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
