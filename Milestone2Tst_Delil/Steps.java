import it.polito.appeal.traci.SumoTraciConnection;

public class Steps {
    public SumoTraciConnection conn;

    public Steps(SumoTraciConnection conn)
    {
        this.conn = conn;

    }

   public void StepCounter()
    {

        Thread steps = new Thread(() -> {
            int i = 0;
            try
            {
                    while (conn.isClosed() == false) {

                    conn.do_timestep();
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
