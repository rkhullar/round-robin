public class Demo
{
    private static Process[] plist;
    private static RoundRobin1 rr;

    public static void main(String[] args)
    {
        init();
        simulate();
        rr.printSchedule();
    }

    private static void init()
    {
        plist = new Process[6];
        plist[0] = new Process(1, 0, 4);
        plist[1] = new Process(2, 1, 5);
        plist[2] = new Process(3, 2, 2);
        plist[3] = new Process(4, 3, 1);
        plist[4] = new Process(5, 4, 6);
        plist[5] = new Process(6, 6, 3);
    }

    private static void simulate()
    {
        rr = new RoundRobin1();
        rr.setTimeQuantum(2);
        rr.setProcessList(plist);
        rr.startSimulation();
    }
}
