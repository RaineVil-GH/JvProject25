
public class VehiclExtnd {

	    public String id;
	    public Edges crntEdg;
	    public int crntLnIdx;
	    public double pos; // distance along the lane
	    public double maxSpd;

	    public VehiclExtnd(String id, Edges edge, int laneIdx, double maxSpeed) {
	        this.id = id;
	        this.crntEdg = edge;
	        this.crntLnIdx = laneIdx;
	        this.pos = 0;       // start at beginning of lane
	        this.maxSpd = maxSpeed; // can be set later
	    }
	    
	    public void step(double dt) {
	    	pos += maxSpd * dt;
	    }

}
