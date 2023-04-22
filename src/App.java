import com.hust.tool.IO;
import com.hust.ui.LoginJFrame;

import java.io.PrintStream;
import java.net.Socket;

public class App {

    public static void main(String[] args) {
        try {
            System.out.println("===========客户端启动=========");
            Socket socket = new Socket("172.25.48.1", 7777);
            IO.ps = new PrintStream(socket.getOutputStream());


        } catch (Exception e) {
            IO.LOGGER.error("异常："+e);
        }
         new LoginJFrame();
        //new RegisterJFrame();
        //new GameJFrame();
        //new Text();

    }
}
