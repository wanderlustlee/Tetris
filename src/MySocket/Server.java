package MySocket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ��������
 */

public class Server {
//    public static final int PORT = 12345;//�����Ķ˿ں�
    private static ExchangeThread serverExchangeThread;
    public static int matchPort = 0;
    public static void Init(int PORT){
        try {
            ServerSocket ss = new ServerSocket(PORT);
            
            System.out.println("�˿ں�"+PORT+",������������");
            Socket s = ss.accept();
            // ���������߳�
            serverExchangeThread=new ExchangeThread(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ExchangeThread getExchangeThread(){
        return serverExchangeThread;
    }

}