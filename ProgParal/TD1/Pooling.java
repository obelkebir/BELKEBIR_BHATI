import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Pooling {

    public static ExecutorService pool = Executors.newFixedThreadPool(Server.MAX_CLIENTS);
    
    public static void execute(Runnable r){
        pool.execute(r);
    }

}