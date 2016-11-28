public class Process
{
    private int id;
    // arrival, burst, run, waiting, completion
    private int at, bt, rt, wt, ct;

    public Process(int id, int at, int bt)
    {
        this.id = id;
        this.at = at;
        this.bt = bt;
        this.rt = 0;
        this.wt = 0;
        this.ct = -1;
    }

    public Process clone()
    {
        return new Process(id, at, bt);
    }

    public int getArrivalTime()
    {
        return at;
    }
    public void setArrivalTime(int at)
    {
        this.at = at;
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
        return bt == 0;
    }

    public void markComplete(int t)
    {
        ct = t;
    }

    public void Wait()
    {
        wt++;
    }

    public int totalTime()
    {
        return ct - at;
    }

    public String toString()
    {
        return "P"+id;
    }
}
