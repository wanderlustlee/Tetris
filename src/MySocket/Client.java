package MySocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 客户端
*/
public class Client {
//    public static final int PORT = 12345;//监听的端口号
    private static ExchangeThread clientExchangeThread;

    public static void Init(int PORT){
        try {
            Socket socket = new Socket("127.0.0.1",PORT);
            System.out.println(socket);
            System.out.println("客户端IP:"+socket.getLocalAddress()+"端口"+socket.getPort());
            // 启动交流线程
            clientExchangeThread=new ExchangeThread(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static ExchangeThread getExchangeThread(){
        return clientExchangeThread;
    }
}
