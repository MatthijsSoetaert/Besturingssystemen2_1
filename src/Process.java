public class Process implements Comparable<Process> {

    private int PID;
    private double arrivalTime;
    private double serviceTime;
    private double startTime;
    private double endTime;
    private double turnAroundTime;
    private double normalizedTurnAroundTime;
    private double waitTime;



    private double remainingTime;

    //Constructors
    public Process() {
        remainingTime = serviceTime;
    }

    //Functions
    public void printProcess(){
        System.out.println("PID: " + PID);
        System.out.println("arrivalTime" + arrivalTime);
        System.out.println("serviceTime" + serviceTime);
        System.out.println("Starttime: " + startTime);
        System.out.println("EndTime: " + endTime);
        System.out.println("TAT: " + turnAroundTime);
        System.out.println("NormalizedTAT: " + normalizedTurnAroundTime);
        System.out.println("Waittime: " + waitTime);
        System.out.println("");
    }

    public double executeProcess(){
        endTime = startTime + serviceTime;
        waitTime = startTime - arrivalTime;
        return endTime;
    }

    //Getters en setters
    public int getPID() {
        return PID;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }

    public double getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(double arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public double getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(double serviceTime) {
        this.serviceTime = serviceTime;
    }

    public double getStartTime() {
        return startTime;
    }

    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }

    public double getEndTime() {
        return endTime;
    }

    public void setEndTime(double endTime) {
        this.endTime = endTime;
    }

    public double getTurnAroundTime() {
        return turnAroundTime;
    }

    public void setTurnAroundTime() {
        this.turnAroundTime = waitTime + serviceTime;
    }

    public double getNormalizedTurnAroundTime() {
        return normalizedTurnAroundTime;
    }

    public void setNormalizedTurnAroundTime() {
        this.normalizedTurnAroundTime = turnAroundTime/serviceTime;
    }

    public double getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(double waitTime) {
        this.waitTime = waitTime;
    }

    public double getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(double remainingTime) {
        this.remainingTime = remainingTime;
    }

    @Override
    public int compareTo(Process p) {
        return (int) (serviceTime - p.getServiceTime());
    }
}
