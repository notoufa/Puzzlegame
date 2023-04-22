import com.hust.tool.IO;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class Server {
    private static ExecutorService pool = new ThreadPoolExecutor(10, 20,
            6, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args)  {
        System.out.println("===========服务端启动=========");
        ServerSocket ss=null;
        try {
            ss = new ServerSocket(7777);
        } catch (Exception e) {
            //throw new RuntimeException(e);
            IO.LOGGER.error("异常："+e);

        }
        while (true) {
            Socket s= null;
            try {
                s = ss.accept();
            } catch (Exception e) {
                //throw new RuntimeException(e);
                IO.LOGGER.error("异常："+e);
            }
            pool.execute(new Serverunnable(s));
        }
    }
}
