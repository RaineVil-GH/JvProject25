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


	int count = 0;
    public void spawnVehicle (int amount, SumoTraciConnection conn, Object trafficParticipant) throws Exception
    {
        if (trafficParticipant instanceof Bus)
        {
            for(int i = 0; i < amount; i++) {
                String id2 = "Bus" + count;
                count++;
                Bus b = new Bus(id2);

                synchronized (LOCK.CONN_LOCK) {
                    conn.do_job_set(Vehicle.add(b.id, b.type, b.routeID, b.depart, b.position, b.speed, b.departLane));
                }
               // vehicles.add(b.posistion);
            }

        }
        
        count = 0;
        if (trafficParticipant instanceof Car)
        {
            for(int i = 0; i < amount; i++) {
                String id2 = "Car" + count;
                count++;
                Car c = new Car(id2);
                synchronized (LOCK.CONN_LOCK) {
                    conn.do_job_set(Vehicle.add(c.id, c.type, c.routeID, c.depart , c.position, c.speed, c.departLane));
                }
                //vehicles.add(c.po);
            }


        }
        
        count = 0;
        if (trafficParticipant instanceof Cyclist)
        {
            for(int i = 0; i < amount; i++) {
                String id2 = "Cyclist" + count;
                count++;
                Cyclist cc = new Cyclist(id2);
                synchronized (LOCK.CONN_LOCK) {
                    conn.do_job_set(Vehicle.add(cc.id, cc.type, cc.routeID, cc.depart , cc.position, cc.speed, cc.departLane));
                }
                //vehicles.add(cc.id,cc.position);

            }

        }
       
        count = 0;
        if (trafficParticipant instanceof Motorcycle)
        {
            for(int i = 0; i < amount; i++) {
                String id2 = "Motorcycle" + count;
                count++;
                Motorcycle m = new Motorcycle(id2);
                synchronized (LOCK.CONN_LOCK) {
                    conn.do_job_set(Vehicle.add(m.id, m.type, m.routeID, m.depart, m.position, m.speed, m.departLane));
                }
            }

        }
        
        count = 0;
        if (trafficParticipant instanceof Pedestrian)
        {
            for(int i = 0; i < amount; i++) {
                String id2 = "Pedestrian" + count;
                count++;
                Pedestrian p = new Pedestrian(id2);
                synchronized (LOCK.CONN_LOCK) {
                    conn.do_job_set(Vehicle.add(p.id, p.type, p.routeID, p.depart, p.position, p.speed, p.departLane));
                }
            }


        }

    }

	
}

