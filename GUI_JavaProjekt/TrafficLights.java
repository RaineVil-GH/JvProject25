import java.util.ArrayList;
import java.util.List;

public class TrafficLights {
	public final String juncID;
	public final List<TLPhase> phs =new ArrayList<>();
	
	public Junction juncTL;
	
	private int crntPhs = 0;
	private double timer = 0;
	
	public TrafficLights(String junctiondID) { this.juncID = junctiondID;}

	public void step(double dt) {

		if(phs.isEmpty()) return;
		
		timer += dt;
		TLPhase phaser = phs.get(crntPhs);
		
		if(timer >= phaser.drt){
			timer = 0;
			crntPhs = (crntPhs + 1) % phs.size();			
		}
	}
	
	public char getSgnlState(int idx) { 
		if(phs.isEmpty() || idx >= phs.get(crntPhs).st.length()) return 'r';
		return phs.get(crntPhs).st.charAt(idx);
		}
	public String getCrntState() { 
		if(phs.isEmpty() || juncTL == null) return "";
		return phs.get(crntPhs).st;}
	
	public void reset() {
		crntPhs = 0;
		timer = 0;
		
	}


}
