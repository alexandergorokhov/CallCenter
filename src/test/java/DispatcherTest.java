import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import static org.junit.Assert.assertEquals;


public class DispatcherTest {
    private  Dispacher dispacher=new Dispacher().getDispacher();
    private  AtomicInteger count=new AtomicInteger(0);
    private Integer CONCURRENT_CALLS=10;
    private Integer OPERATORS=5;
    private Integer SUPERVISORS=4;
    private Integer DIRECTORS=1;
    private ExecutorService threadPool ;

    @Before
    public void init(){
        dispacher.setOperators(OPERATORS);
        dispacher.setDirectors(DIRECTORS);
        dispacher.setSupervisors(SUPERVISORS);
        threadPool= Executors.newFixedThreadPool(CONCURRENT_CALLS);
    }

    @Test
    public void testDispatcher(){
        for (int i = 0; i < CONCURRENT_CALLS; i++) {
            threadPool.execute(dispacher);
        }
        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.terminate();
    }

  //  @Test
    public void testOccupancyOperators(){
        this.init();
        for (int i = 0; i < 10; i++) {
        threadPool.execute(dispacher);
        }
        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(5,this.sumAttendedCalls(dispacher.getOperators()));
        assertEquals(4,this.sumAttendedCalls(dispacher.getSupervisors()));
        assertEquals(1,this.sumAttendedCalls(dispacher.getDirectors()));
        this.terminate();

     }


    public int sumAttendedCalls(Collection<CallCenterMember> list){
        int sum=0;
        for(CallCenterMember member: list){
            sum=sum +member.getAttendedCalls();
        }
        return sum;
    }
    @After
    public void terminate(){
       //Sleep to avoid RejectExecutionException due submiting task once the pool is shutdown
        try {
            Thread.sleep(2000);
       } catch (InterruptedException e) {
            e.printStackTrace();
        }
       dispacher.terminateAfterLastCall();
       threadPool.shutdown();
    }
}
