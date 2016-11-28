public class RoundRobin
{
    private final static int MAX_TIME = 1000;

    // quantum, clock, schedule
    private int tq, tc, ts;
    private Process[] plist;
    private Queue<Process> q;
    private Queue<TimeBlock> schedule;
    private Process m;

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
                q.enqueue(p);
    }

    private void handleProcess()
    {
        Process p = q.dequeue();
        int bt = p.getBurstTime();
        int dt = bt > tq ? tq : bt;
        TimeBlock tb = TimeBlock.fromDelta(p, ts, dt);
        schedule.enqueue(tb);
        p.decBurstTime(dt);
        ts += dt;
        if(p.isComplete())
        {
            p.markComplete(ts);
            m = null;
        }
        else
            m = p;
    }

    public void startSimulation()
    {
        tc = 0; ts = 0;
        q = new Queue<>(); schedule = new Queue<>();
        while(!isComplete() && tc < MAX_TIME)
        {
            catchArrivingProcesses();
            if(m != null)
                q.enqueue(m);
            if(!q.isEmpty())
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
        q = new Queue<>();
        System.out.println(q);
        for(Process p: plist)
        {
            System.out.println("adding: "+p);
            q.enqueue(p);
            System.out.println(q);
        }
        for(Process p: plist)
        {
            Process t = q.dequeue();
            System.out.println("removed: "+t);
            System.out.println(q);
        }
    }
}
