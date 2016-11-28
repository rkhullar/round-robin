public class RoundRobin
{
    private final static int MAX_TIME = 1000;

    private int tq, t;
    private Process[] plist;
    private Queue<Process> q;
    private Queue<TimeBlock> schedule;

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
            if(p.getArrivalTime() == t)
                q.enqueue(p);
    }

    private void handleProcess()
    {
        Process p = q.dequeue();
        int bt = p.getBurstTime();
        TimeBlock tb = new TimeBlock(p);
        tb.setStart(t);
        if(bt > tq)
            tb.setDelta(tq);
        else {
            tb.setDelta(bt);
            p.markComplete(tb.getStop());
        }
        schedule.enqueue(tb);
        p.decBurstTime(tb.getDelta());
        if(!p.isComplete())
            q.enqueue(p);
    }

    public void startSimulation()
    {
        q = new Queue<>(); t = 0; schedule = new Queue<>();
        while(!isComplete() && t < MAX_TIME)
        {
            catchArrivingProcesses();
            //System.out.println(t+": "+q.toString());
            if(!q.isEmpty())
                handleProcess();
            //System.out.println(t+": "+q.toString());
            t++;
        }
    }

    public void printSchedule()
    {
        Node<TimeBlock> node = schedule.getHead();
        while(node != null)
        {
            TimeBlock tb = node.getData();
            System.out.printf("%02d-%02d: P%s\n", tb.getStart(), tb.getStop(), tb.getProcess().toString());
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
