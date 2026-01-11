package src.GUI_Java;
import it.polito.appeal.traci.SumoTraciConnection;
import src.*;

import java.awt.*;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.lang.String;
import java.util.List;


public class GUI_JavaProjekt
{

	private JFrame frame;
	private JPanel center;
	
	private boolean fileLoaded = false;
	int steps = 0;
	
	//private VehicleManager vMngr = new VehicleManager();
	public MapVisual mv;
//	public SimuVisual smV;


    public boolean pressed;
    public SumoTraciConnection conn;
    public List<String> TrafficLightsIds;
    public int ticks;

    public int Camount;
    public int Mamount;
    public int Bamount;
    public int CCamount;
    public int Pamount;


    private Car cCar;

	public GUI_JavaProjekt(SumoTraciConnection conn, List<String> trafficLightsIds) {
    this.conn = conn;
    this.TrafficLightsIds = trafficLightsIds;

	}
	
	//Starting Method for the GUI
	public void startFrame() {
        frame = new JFrame("GUI Network");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);

        JPanel mainpnl = new JPanel(new BorderLayout());
        JPanel dbP = new JPanel(new BorderLayout());
        JPanel ctrP = new JPanel();
        ctrP.setLayout(new BoxLayout(ctrP,BoxLayout.Y_AXIS));
        center = new JPanel(new BorderLayout());

        dbP.setPreferredSize(new Dimension(100,0));
        ctrP.setPreferredSize(new Dimension(200,0));


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
        if(true) {
            veh.addActionListener(e -> {
                JFrame vFrm = new JFrame("Choose Vehicle");
                JButton car = new JButton("Add Car");
                vFrm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                vFrm.setSize(500, 300);
                vFrm.add(car);
                vFrm.setVisible(true);


                car.addActionListener(f -> {
                    if(mv == null) return;

                    //	Edges edg = mv.getEdges().get(0);
                    //	vMngr.spawnVehicle(1, conn, 'C');
                    //smV.repaint();
                });

            });
        }
        //Start



        Steps s1 = new Steps(conn,TrafficLightsIds, true, 400);
        JButton start = new JButton("Start");
        ctrP.add(start);

        start.addActionListener(e -> {
            if(pressed == false)
            {
                s1.StepCounter();
            }
            pressed = true;
            s1.Play_stop(true);

        });



        //Stop
        JButton stop = new JButton("Stop");  
        ctrP.add(stop);
		stop.addActionListener(e -> {
            s1.Play_stop(false);

        });

        JButton vehtype = new JButton("Filter");
        ctrP.add(vehtype);

        //Filter
        vehtype.addActionListener(e -> {
            JFrame vFrm = new JFrame("Choose Vehicle Type");
            vFrm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            //JToggleButton car = new JToggleButton("Car");
            //JButton car1 = new JButton("Car");
            JCheckBox car = new JCheckBox("Car");
            JCheckBox bus = new JCheckBox("Bus");
            JCheckBox motorcycle = new JCheckBox("Motorcycle");
            JCheckBox pedestrian = new JCheckBox("Pedestrian");
            JCheckBox cyclist = new JCheckBox("Cyclist");
            JCheckBox allV = new JCheckBox("All Vehicles");

            Font sizeFont = new Font("Dialog", Font.PLAIN, 20);

            car.setFont(sizeFont);
            bus.setFont(sizeFont);
            motorcycle.setFont(sizeFont);
            pedestrian.setFont(sizeFont);
            cyclist.setFont(sizeFont);
            allV.setFont(sizeFont);

            var distance = BorderFactory.createEmptyBorder(10, 20, 10, 20);

            car.setBorder(distance);
            bus.setBorder(distance);
            motorcycle.setBorder(distance);
            pedestrian.setBorder(distance);
            cyclist.setBorder(distance);
            allV.setBorder(distance);


            car.setSelected(true);
            bus.setSelected(true);
            motorcycle.setSelected(true);
            pedestrian.setSelected(true);
            cyclist.setSelected(true);
            allV.setSelected(true);

            JPanel panel = new JPanel();
            panel.setLayout(new java.awt.GridLayout(6, 1, 10, 10));

            panel.add(car);
            panel.add(bus);
            panel.add(motorcycle);
            panel.add(pedestrian);
            panel.add(cyclist);
            panel.add(allV);

            vFrm.setContentPane(panel);
            vFrm.setSize(500, 300);
            vFrm.setVisible(true);



            car.addActionListener(e1 -> {
                if(car.isSelected() == false) {
                    allV.setSelected(false);
                }
            });

            bus.addActionListener(e1 -> {
                if(bus.isSelected() == false) {
                    allV.setSelected(false);
                }
            });

            motorcycle.addActionListener(e1 -> {
                if(motorcycle.isSelected() == false) {
                    allV.setSelected(false);
                }
            });

            pedestrian.addActionListener(e1 -> {
                if(pedestrian.isSelected() == false) {
                    allV.setSelected(false);
                }
            });

            cyclist.addActionListener(e1 -> {
                if(cyclist.isSelected() == false) {
                    allV.setSelected(false);
                }
            });

            car.addActionListener(e1 -> {
                if((car.isSelected() == true) && (bus.isSelected() == true) && (motorcycle.isSelected() == true) && (pedestrian.isSelected() == true) && (cyclist.isSelected() == true)) {
                    allV.setSelected(true);
                }
            });

            bus.addActionListener(e1 -> {
                if((car.isSelected() == true) && (bus.isSelected() == true) && (motorcycle.isSelected() == true) && (pedestrian.isSelected() == true) && (cyclist.isSelected() == true)) {
                    allV.setSelected(true);
                }
            });

            motorcycle.addActionListener(e1 -> {
                if((car.isSelected() == true) && (bus.isSelected() == true) && (motorcycle.isSelected() == true) && (pedestrian.isSelected() == true) && (cyclist.isSelected() == true)) {
                    allV.setSelected(true);
                }
            });

            pedestrian.addActionListener(e1 -> {
                if((car.isSelected() == true) && (bus.isSelected() == true) && (motorcycle.isSelected() == true) && (pedestrian.isSelected() == true) && (cyclist.isSelected() == true)) {
                    allV.setSelected(true);
                }
            });

            cyclist.addActionListener(e1 -> {
                if((car.isSelected() == true) && (bus.isSelected() == true) && (motorcycle.isSelected() == true) && (pedestrian.isSelected() == true) && (cyclist.isSelected() == true)) {
                    allV.setSelected(true);
                }
            });

            allV.addActionListener(e1 -> {
                if(allV.isSelected() == false) {
                    car.setSelected(false);
                    bus.setSelected(false);
                    motorcycle.setSelected(false);
                    pedestrian.setSelected(false);
                    cyclist.setSelected(false);
                } else if (allV.isSelected() == true) {
                    car.setSelected(true);
                    bus.setSelected(true);
                    motorcycle.setSelected(true);
                    pedestrian.setSelected(true);
                    cyclist.setSelected(true);
                }
            });

        });

        //Stress Test Default
        JButton StressTestDefault = new JButton("Stress Test Default");
        StressTestDefault.setPreferredSize(new Dimension(200,200));

        ctrP.add(StressTestDefault);

        StressTestStarter STS1 = new StressTestStarter(conn);
        StressTestDefault.addActionListener(e -> {
            STS1.StressTestDefaultStarter(1000);
        });


        //Stress test

        JButton StressTestConfig = new JButton("Stress Test Configuration");
        StressTestConfig.setPreferredSize(new Dimension(200,200));

        ctrP.add(StressTestConfig);

        StressTestConfig.addActionListener(e -> {

            JFrame STC = new JFrame("Stress Test Menu");
            JButton Spawn = new JButton("Start spawning");



            JLabel CLabel = new JLabel("Amount of Cars: ");
            JLabel MLabel = new JLabel("Amount of Motorcycles: ");
            JLabel BLabel = new JLabel("Amount of Bus: ");
            JLabel CCLabel = new JLabel("Amount of Cyclist: ");
            JLabel PLabel = new JLabel("Amount of Pedestrian: ");


            CLabel.setMaximumSize(new Dimension(200,50));
            MLabel.setMaximumSize(new Dimension(200,50));
            BLabel.setMaximumSize(new Dimension(200,50));
            CCLabel.setMaximumSize(new Dimension(200,50));
            PLabel.setMaximumSize(new Dimension(200,50));


            JPanel NameOptions  =  new JPanel();
            NameOptions.setLayout(new javax.swing.BoxLayout(NameOptions, BoxLayout.Y_AXIS));


            NameOptions.add(CLabel);
            NameOptions.add(Box.createVerticalStrut(20));
            NameOptions.add(MLabel);
            NameOptions.add(Box.createVerticalStrut(20));
            NameOptions.add(BLabel);
            NameOptions.add(Box.createVerticalStrut(20));
            NameOptions.add(CCLabel);
            NameOptions.add(Box.createVerticalStrut(20));
            NameOptions.add(PLabel);


            JPanel SpinnerOptions  =  new JPanel();
            SpinnerOptions.setLayout(new javax.swing.BoxLayout(SpinnerOptions, BoxLayout.Y_AXIS));

            SpinnerNumberModel CSM = new SpinnerNumberModel(0, 0, null, 10);
            JSpinner CarSpinner = new JSpinner(CSM);
            ((JSpinner.DefaultEditor) CarSpinner.getEditor()).getTextField().setColumns(10);
            CarSpinner.setMaximumSize(new Dimension(100,50));

            SpinnerNumberModel MSM = new SpinnerNumberModel(0, 0, null, 10);
            JSpinner MotorcycleSpinner = new JSpinner(MSM);
            ((JSpinner.DefaultEditor) MotorcycleSpinner.getEditor()).getTextField().setColumns(10);
            MotorcycleSpinner.setMaximumSize(new Dimension(100,50));

            SpinnerNumberModel BSM = new SpinnerNumberModel(0, 0, null, 10);
            JSpinner BusSpinner = new JSpinner(BSM);
            ((JSpinner.DefaultEditor) BusSpinner.getEditor()).getTextField().setColumns(10);
            BusSpinner.setMaximumSize(new Dimension(100,50));

            SpinnerNumberModel CCSM = new SpinnerNumberModel(0, 0, null, 10);
            JSpinner CyclistSpinner = new JSpinner(CCSM);
            ((JSpinner.DefaultEditor) CyclistSpinner.getEditor()).getTextField().setColumns(10);
            CyclistSpinner.setMaximumSize(new Dimension(100,50));

            SpinnerNumberModel PSM = new SpinnerNumberModel(0, 0, null, 10);
            JSpinner PedestrianSpinner = new JSpinner(PSM);
            ((JSpinner.DefaultEditor) PedestrianSpinner.getEditor()).getTextField().setColumns(10);
            PedestrianSpinner.setMaximumSize(new Dimension(100,50));

            SpinnerOptions.add(CarSpinner);
            SpinnerOptions.add(Box.createVerticalStrut(15));
            SpinnerOptions.add(MotorcycleSpinner);
            SpinnerOptions.add(Box.createVerticalStrut(15));
            SpinnerOptions.add(BusSpinner);
            SpinnerOptions.add(Box.createVerticalStrut(15));
            SpinnerOptions.add(CyclistSpinner);
            SpinnerOptions.add(Box.createVerticalStrut(15));
            SpinnerOptions.add(PedestrianSpinner);


            JPanel Megapanel = new JPanel(new FlowLayout());

            Megapanel.add(Box.createVerticalGlue());
            Megapanel.add(NameOptions);
            Megapanel.add(Box.createHorizontalStrut(50));
            Megapanel.add(SpinnerOptions);
            Megapanel.add(Box.createVerticalGlue());


            JPanel Center = new JPanel(new java.awt.GridBagLayout());
            Center.add(Megapanel);

            JPanel SpawnPanel = new JPanel();
            SpawnPanel.setLayout(new javax.swing.BoxLayout(SpawnPanel, BoxLayout.Y_AXIS));

            SpawnPanel.add(Center);
            SpawnPanel.add(Spawn);


            CarSpinner.addChangeListener(e1 -> {
                Camount = (int) CarSpinner.getValue();
            });

            MotorcycleSpinner.addChangeListener(e1 -> {
                Mamount = (int) MotorcycleSpinner.getValue();
            });

            BusSpinner.addChangeListener(e1 -> {
                Bamount = (int) BusSpinner.getValue();
            });

            CyclistSpinner.addChangeListener(e1 -> {
                CCamount = (int) CyclistSpinner.getValue();
            });

            PedestrianSpinner.addChangeListener(e1 -> {
                Pamount = (int) PedestrianSpinner.getValue();;
            });

            StressTestStarter STS2 = new StressTestStarter(conn);
            Spawn.addActionListener(ex -> {
                STS2.StressTestConfigurationStarter(Camount, Mamount, Bamount, CCamount, Pamount);
            });



            STC.setContentPane(SpawnPanel);
            STC.pack();
            STC.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            STC.setLocationRelativeTo(null);
            STC.setVisible(true);



        });


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



        //Delay Slider

        JPanel DelayPanel = new JPanel();
        DelayPanel.setLayout(new BoxLayout(DelayPanel, BoxLayout.X_AXIS));



        JSlider DelaySlider = new JSlider(0, 1000, 400);
        JLabel ms = new JLabel("Delay(ms): ");

        DelaySlider.setMaximumSize(new Dimension(300,50));
        ticks = DelaySlider.getValue();
        SpinnerNumberModel Anzeige = new SpinnerNumberModel(400, 0, 1000, 10);

        JSpinner DelaySpinner = new JSpinner(Anzeige);
        ((JSpinner.DefaultEditor) DelaySpinner.getEditor()).getTextField().setColumns(4);


        DelayPanel.add(Box.createHorizontalStrut(500));
        DelayPanel.add(Box.createHorizontalGlue());
        DelayPanel.add(ms);
        DelayPanel.add(DelaySlider);
        DelaySlider.addChangeListener(e -> {
            DelaySpinner.setValue(DelaySlider.getValue());
            ticks = DelaySlider.getValue();
            s1.delay(ticks);

        });
        DelaySpinner.addChangeListener(e -> {
            DelaySlider.setValue((int) DelaySpinner.getValue());
            ticks = (int) DelaySpinner.getValue();
            s1.delay(ticks);
        });

        DelayPanel.add(DelaySlider);
        DelayPanel.add(DelaySpinner);
        DelayPanel.add(Box.createHorizontalGlue());

        DelayPanel.add(Box.createHorizontalStrut(200));
        menuBar.add(DelayPanel);



        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
		

	}
		
	//For choosing your network
	private void openNtwrkFile() {
		JFileChooser filechoose = new JFileChooser();
		filechoose.setDialogTitle("Open SUMO network");
		filechoose.setFileFilter(new FileNameExtensionFilter("SUMO network (*.net.xml)","xml"));
		
		int rslt = filechoose.showOpenDialog(frame);
		if(rslt == JFileChooser.APPROVE_OPTION)
			{	
				File selectedFile = filechoose.getSelectedFile();
				new ParseNetwork(center,selectedFile, conn,TrafficLightsIds).execute();
				fileLoaded = true;
			}
		if(rslt == JFileChooser.CANCEL_OPTION) {}	
	}
	
	public void startVisual() {

        //smV.repaint();
	}
	
	public void simulateMapVisual()
	{

		//smV.repaint();
	}	
	
	public void setMapVisual(MapVisual mv2) {
		this.mv = mv2;
	}
	
}
