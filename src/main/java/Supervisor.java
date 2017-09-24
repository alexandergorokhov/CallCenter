import java.util.concurrent.ThreadLocalRandom;

public class Supervisor extends CallCenterMember implements Runnable {
    private Integer attendedCalls=0;

    public Integer getAttendedCalls() {
        return attendedCalls;
    }

    @Override
    public  void attendCall() {

            try {
                System.out.println("Statting Attendind call supervisor  " + " " + Thread.currentThread().getName());
                attendedCalls++;
                Thread.sleep(ThreadLocalRandom.current().nextLong(5000, 10000));
                System.out.println("Finish Attendind call supervisor  " + " " + Thread.currentThread().getName());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
               Dispacher.getDispacher().addCallCenterMemeber(Dispacher.getDispacher().getSupervisors(),this);


    }
    @Override
    public void run(){
     this.attendCall();
    }

}
