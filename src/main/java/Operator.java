import java.util.concurrent.ThreadLocalRandom;

public class Operator extends CallCenterMember implements Runnable {
    private Integer attendedCalls=0;
    public Integer getAttendedCalls() {
        return attendedCalls;
    }
    public Operator() {
    }

    @Override
    public  void attendCall() {

                    try {
                        System.out.println("Starting Attendind call operator  " + " " + Thread.currentThread().getName());
                        attendedCalls++;
                        Thread.sleep(ThreadLocalRandom.current().nextLong(5000, 10000));
                        System.out.println("Finish Attendind call operator  " + " " + Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

             Dispacher.getDispacher().addCallCenterMemeber(Dispacher.getDispacher().getOperators(),this);



    }
    @Override
    public void run(){
       this.attendCall();
    }

 }
