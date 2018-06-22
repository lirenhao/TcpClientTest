import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Test {

    public static void main(String[] args) throws IOException {
        InetSocketAddress address = new InetSocketAddress("10.2.53.248", 9999);

        Socket socket = new Socket();
        socket.connect(address);
//        socket.setSoTimeout(20 * 1000);

        String req = "test";
        System.out.println("send:" + req);
        socket.getOutputStream().write(req.getBytes());

        byte[] bytes = new byte[1024];
        int count = socket.getInputStream().read(bytes);
        String resp = new String(bytes, 0 , count);
        System.out.println("return:" + resp);

        socket.close();
    }
}
