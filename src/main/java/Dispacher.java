import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Dispacher implements Runnable {
    private static ConcurrentLinkedQueue<CallCenterMember> operators;
    private static ConcurrentLinkedQueue<CallCenterMember> supervisors;
    private static ConcurrentLinkedQueue<CallCenterMember> directors;
    private final static Integer MAX_CALLS=10;

    private static ConcurrentLinkedQueue<Future<?>> futures = new ConcurrentLinkedQueue();
    private static Dispacher disp=new Dispacher();

    private static ExecutorService threadPool = Executors.newFixedThreadPool(MAX_CALLS);

    public  ConcurrentLinkedQueue<CallCenterMember> getOperators()
    {
        return operators;
    }

    public   ConcurrentLinkedQueue<CallCenterMember> getSupervisors() {

        return supervisors;
    }

    public  ConcurrentLinkedQueue<CallCenterMember> getDirectors() {

        return directors;
    }

    public static Dispacher getDispacher(){

        return disp;
    }
//Check if there is any processing calls, if none shutdown
    public  void terminateAfterLastCall(){
        boolean finished=false;

        while(!finished){
            finished=true;
          for(Future task:futures){
                if(!task.isDone()){
                    finished=false;
                    break;
                }
            }
        }
        threadPool.shutdown();
    }

    public void setOperators(int NumOperators) {
        this.operators=new ConcurrentLinkedQueue();
        for(int i=0;i<NumOperators;i++){
                this.operators.add(new Operator());
        }

    }

    public void setSupervisors(int NumSupervisors) {
        this.supervisors =new ConcurrentLinkedQueue();
        for(int i=0;i<NumSupervisors;i++){
            this.supervisors.add(new Supervisor());
        }
    }

    public void setDirectors(int NumDirectors) {
        this.directors =new ConcurrentLinkedQueue();
        for(int i=0;i<NumDirectors;i++){
            this.directors.add(new Director());
        }
    }
//Notify that there is an avalible CallCenter mamber
    public synchronized void addCallCenterMemeber(Collection collection, CallCenterMember member){
         collection.add(member);
         notify();
    }

    public  void  dispatchCall(Optional<CallCenterMember> member)  {
           Future futureTask=threadPool.submit(member.get());
            futures.add(futureTask);
    }

      //Get avalible CallCenterMember, if none is avalible wait
    public  synchronized Optional<CallCenterMember> getAvailibleMember(){
        //while is used to avoid spurious wakeup
        while (operators.isEmpty()&& supervisors.isEmpty()&& directors.isEmpty()){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(!operators.isEmpty()){
              return Optional.ofNullable(operators.poll());
        }
        if(!supervisors.isEmpty()){
            return Optional.ofNullable(supervisors.poll());
        }
        if(!directors.isEmpty()){
            return Optional.ofNullable(directors.poll());
        }

        return Optional.ofNullable(null);


  }

    @Override
    public void run() {

        this.dispatchCall(getAvailibleMember()
        );
    }

}
