package src.GUI_Java;
import java.awt.BorderLayout;
import java.io.File;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import it.polito.appeal.traci.SumoTraciConnection;
import src.GUI_Java.GUI_JavaProjekt;
import src.GUI_Java.MapVisual;
import src.GUI_Java.ParseElmt;
import src.GUI_Java.VehicleManager;

public class ParseNetwork extends SwingWorker<ParseElmt,Void> {

    private final JPanel panel;
    private final File file;
//	private final GUI_JavaProjekt gui;
//	private final VehicleManager vMngr;
    public SumoTraciConnection conn;
    public List<String> TrafficLightsIds;


    ParseNetwork(JPanel pnl, File fl, SumoTraciConnection conn, List<String> trafficLightsIds) {
        this.panel = pnl;
        this.file = fl;
        //	this.gui = gui2;
        //	this.vMngr = vm;
        this.conn = conn;
        this.TrafficLightsIds = trafficLightsIds;

    }

    @Override
    protected ParseElmt doInBackground() throws Exception {
        return new ParseElmt(file);
    }

    @Override
    protected void done() {
        try {
            ParseElmt prs = get();
            MapVisual mvPanel = new MapVisual(
                    prs.getEdges(),
                    prs.getJunc(),
                    prs.getMinX(),
                    prs.getMinY(),
                    prs.getMaxX(),
                    prs.getMaxY()
            );
            new GUI_JavaProjekt(conn, TrafficLightsIds).setMapVisual(mvPanel);
			
		/*	//Adding Vehicle
			if(!prs.getEdges().isEmpty() && !prs.getEdges().get(0).lnlst.isEmpty()) {
				VehiclExtnd v = new VehiclExtnd("veh0", prs.getEdges().get(0),0,10);
				vMngr.addVehicle(v);				
			}
		*/
            panel.removeAll();
            panel.add(mvPanel, BorderLayout.CENTER);
            panel.revalidate();
            panel.repaint();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
