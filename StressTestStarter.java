package src;

import it.polito.appeal.traci.SumoTraciConnection;

public class StressTestStarter {
    public SumoTraciConnection conn;

    /*
    public int Camount;
    public int Mamount;
    public int Bamount;
    public int CCamount;
    public int Pamount;
    */

    public StressTestStarter(SumoTraciConnection conn )
    {
        this.conn = conn;
    }




    public void StressTestDefaultStarter(int amount) {


        Thread T_STD = new Thread(() -> {


            try {

                int neueID = LOCK.nextId();

                Car c1 = new Car("c");
                c1.createCar(amount, conn, neueID );

                Pedestrian p1 = new Pedestrian("p");
                p1.createPedestrian(amount, conn, neueID);

                Motorcycle m1 = new Motorcycle("m");
                m1.createMotorcycle(amount, conn, neueID);

                Cyclist cc1 = new Cyclist("cc");
                cc1.createCyclist(amount, conn, neueID);

                Bus b1 = new Bus("b");
                b1.createBus(amount, conn, neueID);
            } catch (Exception exep) {exep.printStackTrace();}

        });
        T_STD.start();
    }

    public void StressTestConfigurationStarter(int Camount, int Mamount, int Bamount, int CCamount, int Pamount) {


        Thread T_STC = new Thread(() -> {


            try {

                int neueID = LOCK.nextId();
                


                Car c2 = new Car("c");
                c2.createCar(Camount, conn, neueID );

                Pedestrian p2 = new Pedestrian("p");
                p2.createPedestrian(Pamount, conn, neueID);

                Motorcycle m2 = new Motorcycle("m");
                m2.createMotorcycle(Mamount, conn, neueID);

                Cyclist cc2 = new Cyclist("cc");
                cc2.createCyclist(CCamount, conn, neueID);

                Bus b2 = new Bus("b");
                b2.createBus(Bamount, conn, neueID);


            } catch (Exception exep) {exep.printStackTrace();}

        });
        T_STC.start();
    }
}