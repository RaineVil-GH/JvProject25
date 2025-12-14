import java.util.ArrayList;
import java.util.List;

public class VehicleManager {
	
	
	private List<VehiclExtnd> vehicles = new ArrayList<>();
	
	public VehicleManager() {}
	
	public void step(double dt) {
	    for (VehiclExtnd v : vehicles) {
	        if (v.crntEdg == null || v.crntEdg.lnlst.isEmpty()) continue;
	        v.step(dt);

	        if(v.crntLnIdx < 0 || v.crntLnIdx >= v.crntEdg.lnlst.size()) continue;
	        Lanes ln = v.crntEdg.lnlst.get(v.crntLnIdx);
	        if (v.pos >= ln.shape.size() - 1) {
	            v.pos = ln.shape.size() - 1; 
	        }
	    }
	}
	
	public void addVehicle(VehiclExtnd v) 
	{ vehicles.add(v);}

	public List<VehiclExtnd> getVehicle()
	{return vehicles;}

	
}
