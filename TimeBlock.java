public class TimeBlock
{
    private int t1, t2;
    private Process p;

    public TimeBlock(Process p)
    {
        this.p = p;
    }

    public void setStart(int t)
    {
        t1 = t;
    }
    public int getStart()
    {
        return t1;
    }

    public void setStop(int t)
    {
        t2 = t;
    }
    public int getStop()
    {
        return t2;
    }

    public void setDelta(int t)
    {
        t2 = t1 + t;
    }
    public int getDelta()
    {
        return t2 - t1;
    }

    public void setProcess(Process p)
    {
        this.p = p;
    }
    public Process getProcess()
    {
        return p;
    }

    public static TimeBlock fromDelta(Process p, int s, int d)
    {
        TimeBlock x = new TimeBlock(p);
        x.setStart(s); x.setDelta(d);
        return x;
    }

    public static TimeBlock fromSlice(Process p, int t1, int t2)
    {
        TimeBlock x = new TimeBlock(p);
        x.setStart(t1); x.setStop(t2);
        return x;
    }
}
