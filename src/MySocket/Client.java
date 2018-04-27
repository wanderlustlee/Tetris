package MySocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * �ͻ���
*/
public class Client {
//    public static final int PORT = 12345;//�����Ķ˿ں�
    private static ExchangeThread clientExchangeThread;

    public static void Init(int PORT){
        try {
            Socket socket = new Socket("127.0.0.1",PORT);
            System.out.println(socket);
            System.out.println("�ͻ���IP:"+socket.getLocalAddress()+"�˿�"+socket.getPort());
            // ���������߳�
            clientExchangeThread=new ExchangeThread(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static ExchangeThread getExchangeThread(){
        return clientExchangeThread;
    }
}
