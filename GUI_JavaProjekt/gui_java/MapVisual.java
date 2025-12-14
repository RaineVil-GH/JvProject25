import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MapVisual extends JPanel{
	private List<Edges> edglst;
	private Map<String,Junction> juncmap;
	private Map<String,TrafficLights> tlmap;
	
	double minX, minY, maxX, maxY;
	
	private VehicleManager vm;
	
	
	public MapVisual(List<Edges> edglstOUT, Map<String, Junction>junctmapOUT, Map<String,TrafficLights> tlmapOUT , double minOUTX,double minOUTY,double maxOUTX,double maxOUTY, VehicleManager vehicmangr) {
	
		this.edglst = edglstOUT != null ? edglstOUT : Collections.emptyList();
		this.juncmap = junctmapOUT;
		this.tlmap = tlmapOUT;
		this.minX = minOUTX;
		this.minY = minOUTY;
		this.maxX = maxOUTX;
		this.maxY = maxOUTY;
		this.vm = vehicmangr != null ? vehicmangr : new VehicleManager();
		setBackground(Color.WHITE);
	}
	
		
	@Override
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		if(edglst == null || edglst.isEmpty()) {System.out.println("No edges to draw");return;};
		if(maxX <= minX || maxY <= minY) {System.out.println("Invalid bounds");return;}
		
		Graphics2D g2D = (Graphics2D) g;
		g2D.setRenderingHint(	RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);		

		double sclX = getWidth() / (maxX - minX);
	    double sclY = getHeight() / (maxY - minY);
	    double scl = Math.min(sclX, sclY);	
	    drawMap(g2D,scl);
	    drawJunc(g2D,scl);
	    drawTrafLght(g2D,scl);
	    drawVehicles(g2D, scl);
	}
	
	private void drawMap(Graphics2D g2, Double scl2) {
				
		g2.setStroke(new BasicStroke((float) (3*scl2)));
		g2.setColor(Color.BLACK);
		//Drawing Lanes
		for(Edges edg: edglst) {
			
			for(Lanes ln: edg.lnlst) {
				
				Path2D line = new Path2D.Double();
				boolean bool = true;
				
				for(Point2D pnt: ln.shape) {
					double x2 = (pnt.getX() - minX) * scl2;
					double y2 = getHeight() - ((pnt.getY() - minY) * scl2);

					if(bool) {
						line.moveTo(x2,y2);
						bool = false;
					} else { line.lineTo(x2, y2);}	
				}
				g2.draw(line);
			}
		}
	}
	
	private void drawJunc(Graphics g3, Double scl3) {
		g3.setColor(Color.RED);
		
		for(Junction junc: juncmap.values()) {
			//Must Redraw the junction, better with Arc
			double x3 = (junc.x - minX) * scl3;
			double y3 = getHeight() - ((junc.y - minY) * scl3);
			g3.fillOval((int) x3 -3, (int) y3 - 3, 15, 15);
		}	
	}		
	private void drawTrafLght(Graphics2D g4, double scl4) {
		int r = 5;
		int spc = 12;
		
		for(TrafficLights jTL: tlmap.values()) {
			if(jTL == null) continue;
			
			String state = jTL.getCrntState();
			if(state == null || state.isEmpty()) continue;
			if(jTL.juncTL == null) continue;
			
			double bX = (jTL.juncTL.x - minX) * scl4;
			double bY = getHeight() - (jTL.juncTL.y - minY) * scl4;
			
			for(int i = 0; i < state.length(); i++) {
				char s = state.charAt(i);
				
				switch(s) {
				case 'G': g4.setColor(Color.GREEN); break;
				case 'y': g4.setColor(Color.YELLOW); break;
				case 'r': g4.setColor(Color.RED); break;
				default: g4.setColor(Color.GRAY); break;
				}
				
				int x = (int) bX + i * spc;
				int y = (int) bY - 15;
				
				g4.fillOval(x - r, y - r, r*2, r*2);
				
				g4.setColor(Color.BLACK);
				g4.drawOval(x - r, y - r, r*2, r*2);
			}
			
		}
	}

	private void drawVehicles(Graphics2D g5, double scl5) {
		int r = 5;
		g5.setColor(Color.MAGENTA);
		
		if(vm.getVehicle() == null) return;
		
		for(VehiclExtnd v : vm.getVehicle()) {
			if(v.crntEdg == null || v.crntEdg.lnlst.isEmpty()) continue;
			
			Lanes lshp = v.crntEdg.lnlst.get(v.crntLnIdx);
			if(lshp.shape.isEmpty()) continue;
			
			int idx = Math.min((int)(v.pos), lshp.shape.size() - 1);
			Point2D vp = lshp.shape.get(idx);
			
			double vX = (vp.getX() - minX) * scl5;
			double vY = getHeight() - (vp.getY() - minY) * scl5;
			
			g5.fillOval((int)vX - r,(int) vY - r, r*2, r*2);
		}
	}
	
	public void stepSimulation(double deltatime) {
		
		if(tlmap != null) {
			for(TrafficLights tlsim : tlmap.values()) {
				tlsim.step(deltatime);
			}			
		}		
		if(vm.getVehicle() != null) {vm.step(deltatime);}
		repaint();
	}
	
	public List<Edges> getEdges(){ return edglst;}
}
