import com.hust.obj.User;
import com.hust.tool.IO;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.hust.tool.IO.ps;

public class Serverunnable implements Runnable {
    private Socket socket;

    public Serverunnable(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        Date day=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String msg;
            User u;
            while ((msg = br.readLine()) != null) {
                {
                    IO.prs.println(msg+"   IP地址:" + socket.getRemoteSocketAddress()
                            + "  时间：" + sdf.format(day));
                    IO.prs.flush();
                }
            }

        } catch (Exception e) {
            IO.LOGGER.error("退出：用户"+IO.u.getUserName()+"已退出"+"   IP地址:" + socket.getRemoteSocketAddress()
                    + "  时间：" + sdf.format(day));
            IO.prs.println("退出：用户"+IO.u.getUserName()+"已退出"+"   IP地址:" + socket.getRemoteSocketAddress()
                    + "  时间：" + sdf.format(day));
        }
    }
}
