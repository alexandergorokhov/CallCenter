import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Application {
    public static Dispacher disp=Dispacher.getDispacher();

    public static void main(String[] args){
        disp.setDirectors(1);
        disp.setSupervisors(4);
        disp.setOperators(5);

        ExecutorService threadPool = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 10; i++) {

            threadPool.execute(disp);
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        disp.terminateAfterLastCall();
        threadPool.shutdown();

    }
}
