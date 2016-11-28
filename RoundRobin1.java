public class RoundRobin1
{
    private final static int MAX_TIME = 1000;

    // quantum, clock, schedule
    private int tq, tc, ts;
    private Process[] plist;
    // ready, waiting
    private Queue<Process> q1, q2;
    private Queue<TimeBlock> schedule;
    private boolean handled = false;

    public void setTimeQuantum(int tq)
    {
        this.tq = tq;
    }

    public void setProcessList(Process[] pl)
    {
        plist = pl;
    }

    private boolean isComplete()
    {
        for(Process p: plist)
            if(!p.isComplete())
                return false;
        return true;
    }

    private void catchArrivingProcesses()
    {
        for(Process p: plist)
            if(p.getArrivalTime() == tc)
                q1.enqueue(p);
        Process p;
        Node<Process> node = q2.getHead();
        while(node != null)
        {
            p = node.getData();
            if(p.getArrivalTime() == tc)
                q1.enqueue(p);
            node = node.getNext();
        }
    }

    private void handleProcess()
    {
        if(!handled)
            ts = tc;
        handled = true;
        Process p = q1.dequeue();
        int bt = p.getBurstTime();
        int dt = bt > tq ? tq : bt;
        TimeBlock tb = TimeBlock.fromDelta(p, ts, dt);
        schedule.enqueue(tb);
        p.decBurstTime(dt);
        ts += dt;
        if(p.isComplete())
            p.markComplete(ts);
        else
        {
            Process m = p.clone();
            m.setArrivalTime(ts);
            q2.enqueue(m);
        }
    }

    public void startSimulation()
    {
        tc = 0; ts = -1;
        q1 = new Queue<>(); q2 = new Queue<>(); schedule = new Queue<>();
        while(!isComplete() && tc < MAX_TIME)
        {
            catchArrivingProcesses();
            if(!q1.isEmpty())
                handleProcess();
            tc++;
        }
    }

    public void printSchedule()
    {
        Node<TimeBlock> node = schedule.getHead();
        while(node != null)
        {
            TimeBlock tb = node.getData();
            System.out.printf("%02d-%02d: %s\n", tb.getStart(), tb.getStop(), tb.getProcess().toString());
            node = node = node.getNext();
        }
    }

    public void test01()
    {
        q1 = new Queue<>();
        System.out.println(q1);
        for(Process p: plist)
        {
            System.out.println("adding: "+p);
            q1.enqueue(p);
            System.out.println(q1);
        }
        for(Process p: plist)
        {
            Process t = q1.dequeue();
            System.out.println("removed: "+t);
            System.out.println(q1);
        }
    }
}
