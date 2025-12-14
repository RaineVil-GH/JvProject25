import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.SwingWorker;

public class ParseNetwork extends SwingWorker<ParseElmt,Void> {

	private final JPanel panel;
	private final File file;
	private final GUI_JavaProjekt gui;
	private final VehicleManager vMngr;
	
	ParseNetwork(JPanel pnl, File fl,GUI_JavaProjekt gui2,VehicleManager vm){ 
		this.panel = pnl;
		this.file = fl; 
		this.gui = gui2;
		this.vMngr = vm;}
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
					prs.getTrffcLght(),
					prs.getMinX(),
					prs.getMinY(),
					prs.getMaxX(),
					prs.getMaxY(),	
					vMngr
					);		
			gui.setMapVisual(mvPanel);
			//Adding Vehicle
			if(!prs.getEdges().isEmpty() && !prs.getEdges().get(0).lnlst.isEmpty()) {
				VehiclExtnd v = new VehiclExtnd("veh0", prs.getEdges().get(0),0,10);
				vMngr.addVehicle(v);				
			}
			
			panel.removeAll();
			panel.add(mvPanel,BorderLayout.CENTER);
			panel.revalidate();
			panel.repaint();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}	
}
