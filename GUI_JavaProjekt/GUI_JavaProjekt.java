
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.lang.String;
public class GUI_JavaProjekt
{

	private JFrame frame;
	private JPanel center;

	private VehicleManager vMngr = new VehicleManager();
	private MapVisual mv;
	
	Timer simTimer = new Timer(100, e -> {
		if(mv != null)
		{mv.stepSimulation(0.1); }});
	
	public GUI_JavaProjekt() {
		

	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(() -> {
			new GUI_JavaProjekt().start();
			
		});
	}
	
	private void start() {
        frame = new JFrame("GUI Network");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);

        JPanel mainpnl = new JPanel(new BorderLayout());
        JPanel dbP = new JPanel(new BorderLayout());
    	JPanel ctrP = new JPanel();
    	ctrP.setLayout(new BoxLayout(ctrP,BoxLayout.Y_AXIS));
        center = new JPanel(new BorderLayout());
        
        dbP.setPreferredSize(new Dimension(100,0));
        ctrP.setPreferredSize(new Dimension(100,0));

        
        mainpnl.add(dbP,BorderLayout.WEST);
       	mainpnl.add(center,BorderLayout.CENTER);
        mainpnl.add(ctrP,BorderLayout.EAST);

        frame.setContentPane(mainpnl);
        //Titles
        dbP.add(new JLabel("Dashboarder"),BorderLayout.NORTH);
        ctrP.add(new JLabel("ControlPanel"),BorderLayout.NORTH);
        
        //ControlPanel
        //Add Vehicle
        JButton veh = new JButton("Vehicles");  
        ctrP.add(veh);
		veh.addActionListener(e -> {
			if(mv == null) return;
			
			Edges edg = mv.getEdges().get(0);
			VehiclExtnd v = new VehiclExtnd("veh" + vMngr.getVehicle().size() , edg ,0 ,10);
			vMngr.addVehicle(v);
			mv.repaint();		
		});
		JButton start = new JButton("Start"); 
        ctrP.add(start);
		start.addActionListener(e -> {
			if(mv != null) simTimer.start(); });        
        //Stop
        JButton stop = new JButton("Stop");  
        ctrP.add(stop);
		stop.addActionListener(e -> simTimer.stop());        

        
        JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		//FileMenu
		JMenu fileMenu = new JMenu("file");
		JMenuItem openItem = new JMenuItem("Open");
		JMenuItem exitItem = new JMenuItem("Exit");
		fileMenu.add(openItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		menuBar.add(fileMenu);
		

		//FileChooser
		openItem.addActionListener(e -> openNtwrkFile());        
	    exitItem.addActionListener(e -> System.exit(0));

		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}
	
	private void openNtwrkFile() {
		JFileChooser filechoose = new JFileChooser();
		filechoose.setDialogTitle("Open SUMO network");
		filechoose.setFileFilter(new FileNameExtensionFilter("SUMO network (*.net.xml)","xml"));
		
		int rslt = filechoose.showOpenDialog(frame);
		if(rslt == JFileChooser.APPROVE_OPTION)
			{	
				File selectedFile = filechoose.getSelectedFile();
				new ParseNetwork(center,selectedFile,this,vMngr).execute();
				
				
			}
		if(rslt == JFileChooser.CANCEL_OPTION) {}	
	}
	public void setMapVisual(MapVisual mv2) {
		this.mv = mv2;
	}

}
