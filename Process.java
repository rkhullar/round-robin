public class Process
{
    private int id;
    // arrival, burst, run, waiting, completion
    private int at, bt, rt, wt, ct;
    private boolean done;

    public Process(int id, int at, int bt)
    {
        this.id = id;
        this.at = at;
        this.bt = bt;
        this.rt = 0;
        this.wt = 0;
        this.ct = -1;
        this.done = false;
    }

    public int getArrivalTime()
    {
        return at;
    }

    public int getBurstTime()
    {
        return bt;
    }

    public void decBurstTime(int t)
    {
        bt -= t;
    }

    public boolean isComplete()
    {
        return this.done;
    }

    public void markComplete(int t)
    {
        this.ct = t;
        this.done = true;
    }

    public void Wait()
    {
        this.wt++;
    }

    public int totalTime()
    {
        return ct - at;
    }

    public String toString()
    {
        return id+"";
    }
}
