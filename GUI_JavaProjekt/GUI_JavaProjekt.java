import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.*;

import java.lang.String;
public class GUI_JavaProjekt {
	
	int höhe = 250;
	int breite = 500;
	
	public GUI_JavaProjekt(Object[][] data,String map){
		
		JFrame frame = new JFrame("GUI_JavaProjekt");
		//Basic frame Settings
		frame.setLayout(new CardLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(breite, höhe);
		
		//add Panels
		JPanel pnl_main = new JPanel(new BorderLayout()); //
		JPanel pnl_db = new JPanel(new BorderLayout());
		JPanel pnl_mv = new JPanel(new BorderLayout());
		JPanel pnl_cp = new JPanel(new GridLayout(2,2));
		
		frame.add(pnl_main);
		//DashboardPanel
		pnl_main.add(pnl_db,BorderLayout.CENTER);
		pnl_db.setVisible(true);
		//DashboardTabele
		String[] colName = {"What","HowMuch"};
		JTable table_db = new JTable(data, colName);
		pnl_db.add(table_db,BorderLayout.CENTER);
		table_db.setFillsViewportHeight(true);
		//table_db.setSize(1, 1);
		
		
		//MapViewPanel
		pnl_main.add(pnl_mv, BorderLayout.EAST);
		pnl_mv.setVisible(true);
		//MapViewContent
		JScrollPane scrl_mv = new JScrollPane();
		pnl_mv.add(scrl_mv,BorderLayout.CENTER);

		//ControlPanel
		pnl_mv.add(pnl_cp,BorderLayout.SOUTH);		
		pnl_cp.add(new JButton(map));
		pnl_cp.add(new JButton("B"));
		pnl_cp.add(new JButton("C"));
		pnl_cp.setVisible(true);

		//Labels
		pnl_db.add(new JLabel("Dashboard"),BorderLayout.NORTH);
		pnl_mv.add(new JLabel("MapView"),BorderLayout.NORTH);
		pnl_cp.add(new JLabel("Controlpanel"),BorderLayout.NORTH);
		
		//add Menu
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("file");
		JMenuItem openItem = new JMenuItem("Open");
		JMenuItem exitItem = new JMenuItem("Exit");
		fileMenu.add(openItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		menuBar.add(fileMenu);
		frame.setJMenuBar(menuBar);
		
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Object[][] tabele = {
				{"What","HowMuch"},
				{"CurrentSpeed", 10},
				{"MaxSpeed",3000},
				{"TrafficLightsNumber",0}
		};
		String s = "A";
		new GUI_JavaProjekt(tabele,s);
	}

}
