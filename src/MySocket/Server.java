package MySocket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务器端
 */

public class Server {
//    public static final int PORT = 12345;//监听的端口号
    private static ExchangeThread serverExchangeThread;
    public static int matchPort = 0;
    public static void Init(int PORT){
        try {
            ServerSocket ss = new ServerSocket(PORT);
            
            System.out.println("端口号"+PORT+",服务器已启动");
            Socket s = ss.accept();
            // 启动交流线程
            serverExchangeThread=new ExchangeThread(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ExchangeThread getExchangeThread(){
        return serverExchangeThread;
    }

}