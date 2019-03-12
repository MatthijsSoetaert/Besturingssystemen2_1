import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import com.emxsys.chart.extension.LogarithmicAxis;
import javafx.scene.chart.ScatterChart;

public class FirstComeFirstServed {

    private List<Process> processList;
    private double currentTime = 0;

    public FirstComeFirstServed(List<Process> processList){
        this.processList = new ArrayList<>(processList);
    }

    public List<Process> execute(){
        for(int i = 0; i < processList.size(); i++){
            Process p = processList.get(i);

            if(i == 0 || currentTime < p.getArrivalTime()){
                p.setStartTime(p.getArrivalTime());
                p.setEndTime(p.getServiceTime() + p.getArrivalTime());
                currentTime = p.getEndTime();
                p.setWaitTime(0);
                p.setTurnAroundTime();
                p.setNormalizedTurnAroundTime();
            }else if(currentTime > p.getArrivalTime()){
                Process previousProcess = processList.get(i-1);
                p.setStartTime(previousProcess.getEndTime());
                p.setEndTime(p.getStartTime() + p.getServiceTime());
                currentTime = p.getEndTime();
                p.setWaitTime(previousProcess.getEndTime() - p.getArrivalTime());
                p.setTurnAroundTime();
                p.setNormalizedTurnAroundTime();
            }

        }
        Collections.sort(processList);
        for(Process p : processList){
            p.printProcess();
        }
        return processList;
    }


    public List<Process> getProcessList() {
        return processList;
    }

    public void setProcessList(List<Process> processList) {
        this.processList = processList;
    }
}
