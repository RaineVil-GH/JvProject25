
import it.polito.appeal.traci.SumoTraciConnection;
import de.tudresden.sumo.cmd.Trafficlight;


public class TrafficlightModul {

    protected String id;
    protected int phase; // aktuelle Ampelphase
    protected double phasetime;
    protected double nextPhase; //Zeipunkt nächster Wechsel der Phase
    protected String multiPhase; //Steuert in einem String mehrere Ampelphasen. Mehrerer Lanes z.B geradeaus, linksabbieger etc.

    TrafficlightModul(String id){
        this.id = id;
    }
    public String getId(){
        return id;
    }
    public int getPhase(){
        return phase;
    }
    public String getMultiPhase(){
        return multiPhase;
    }

    public void updateFromSumo(SumoTraciConnection conn) throws Exception {
        this.phase = (int) conn.do_job_get(
                Trafficlight.getPhase(this.id)
        );
        this.phasetime = (double) conn.do_job_get(
                Trafficlight.getPhaseDuration(this.id)
        );
        this.nextPhase = (double) conn.do_job_get(
                Trafficlight.getNextSwitch(this.id)
        );
        this.multiPhase = (String) conn.do_job_get(
                Trafficlight.getRedYellowGreenState(this.id)
        );
    }

    public void setPhase(SumoTraciConnection conn, int neuePhase) throws Exception{
        conn.do_job_get(
                Trafficlight.setPhase(this.id, neuePhase)
        );
        this.phase = neuePhase;
    }
    public void setPhasetime(SumoTraciConnection conn, double neuePhasetime ) throws Exception{
        conn.do_job_get(
                Trafficlight.setPhaseDuration(this.id, neuePhasetime)
        );
    }
    public void setMultiPhase(SumoTraciConnection conn, String neueMultiphase) throws Exception{
        conn.do_job_get(
                Trafficlight.setRedYellowGreenState(this.id, neueMultiphase)
        );
    }



    }








