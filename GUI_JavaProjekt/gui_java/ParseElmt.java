import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class ParseElmt {
	private List<Edges> edglst;
	private Map<String,Junction> jmap = new HashMap<>();
	private Map<String, TrafficLights> trffclght = new HashMap<>();
	
	
	private double minX = Double.MAX_VALUE;
	private double minY = Double.MAX_VALUE;
	private double maxX = -Double.MAX_VALUE;
	private double maxY = -Double.MAX_VALUE;
	
	public ParseElmt(File fl) throws ParserConfigurationException, SAXException, IOException {
		parse(fl);
	}
	public void parse(File file) throws ParserConfigurationException, SAXException, IOException {
		
		DocumentBuilder docbld = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document d = (Document) docbld.parse(file);
		d.getDocumentElement().normalize();
	
		
		//Parsing Junctions
		jmap = new HashMap<>();
		
		NodeList jncNd = d.getElementsByTagName("junction");
		for(int k = 0; k < jncNd.getLength(); k++) {
			Element e = (Element) jncNd.item(k);
			
			if(e.getAttribute("type").equals("internal")) continue;
			
			Junction junc = new Junction();
			junc.id = e.getAttribute("id");
			junc.x = Double.parseDouble(e.getAttribute("x"));
			junc.y = Double.parseDouble(e.getAttribute("y"));
						
			junc.tlId = e.getAttribute("tl");
			jmap.put(junc.id, junc);
		}
		
		

		//Parsing TrafficLights
		NodeList tlNd = d.getElementsByTagName("tlLogic");
		for(int l = 0; l < tlNd.getLength();l++) {
			Element tlEl = (Element) tlNd.item(l);
	
			String tlid = tlEl.getAttribute("id");
			TrafficLights terrific = new TrafficLights(tlid);	
			
			NodeList phsNd = tlEl.getElementsByTagName("phase");
			for(int m = 0; m < phsNd.getLength(); m++) {
				Element phsEl = (Element) phsNd.item(m);
						
				double duracell = Double.parseDouble(phsEl.getAttribute("duration"));
				String status = phsEl.getAttribute("state");
						
				terrific.phs.add(new TLPhase(duracell,status));
			}
			trffclght.put(tlid, terrific);
		}
		
		for (Junction junc1 : jmap.values()) {
			if(junc1.tljunc == null || junc1.tlId.isEmpty()) continue;
		    TrafficLights tl = trffclght.get(junc1.tljunc);
		    if (tl != null) {
		        tl.juncTL = junc1;
		    }
		}
		//Parsing Edges
		edglst = new ArrayList<>();
		
		NodeList edgNd = d.getElementsByTagName("edge");
		
		for(int i = 0; i < edgNd.getLength(); i++) {
			Element edgEl = (Element) edgNd.item(i);
			
			if("internal".equals(edgEl.getAttribute("function"))) continue;
			
			Edges edg = new Edges();
			edg.id = edgEl.getAttribute("id");
			
			//Parsing Lanes
			NodeList lnNd = edgEl.getElementsByTagName("lane");
			
			for(int j = 0; j < lnNd.getLength();j++) {
				
				Element lnEl = (Element) lnNd.item(j);
				
				Lanes ln = new Lanes();
				ln.id = lnEl.getAttribute("id");		
				
				
				//Parsing shape
				String shp = lnEl.getAttribute("shape").trim();
				
				String[] punk = shp.split("\\s+");
				String[] ptlst;
				for(String pt : punk) {
					ptlst = pt.split(",");
					if(ptlst.length != 2) continue;
					
					double px = Double.parseDouble(ptlst[0]);
					double py = Double.parseDouble(ptlst[1]);
					
					ln.shape.add(new Point2D.Double(px,py));

					minX = Math.min(minX, px);
					minY = Math.min(minY, py);
					maxX = Math.max(maxX, px);
					maxY = Math.max(maxY, py);
					
				}
				if(!ln.shape.isEmpty()) {edg.lnlst.add(ln);}
			}
			
			edglst.add(edg);
		}
	
	}
	
	public List<Edges> getEdges() { return edglst;}
	public Map<String,Junction> getJunc() { return jmap;}
	public Map<String,TrafficLights> getTrffcLght(){return trffclght;}

	public double getMinX( ) {return minX;}
	public double getMinY( ) {return minY;}
	public double getMaxX( ) {return maxX;}
	public double getMaxY( ) {return maxY;}

}
