package MySocket;


import java.io.*;
import java.net.Socket;

import Controller.GameController;
import Controller.RemoteController;


/**
 * 进程通信线程
*/

public class ExchangeThread implements Runnable {
    private Socket socket;
    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;

    public static boolean isNum(String str){
        return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
    }

    public ExchangeThread(Socket socket) {
        this.socket = socket;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        }catch (Exception e){
            e.printStackTrace();
        }
        new Thread(this).start();
    }
    public void run() {
        try {
            while(true) {
                // 这里负责读
                String mess = bufferedReader.readLine();
                if(isNum(mess)) {
                    int color=Integer.parseInt(mess);
                    if(RemoteController.remoteController.getCurRect()==null){
                        RemoteController.remoteController.setcurRect(color);
                    }else{
                        RemoteController.remoteController.setcurRect(RemoteController.remoteController.getNextRect().color);
                        RemoteController.remoteController.setNextRect(color);
                    }
                }
                switch (mess){
                    case "up":
                        RemoteController.remoteController.rectUp();
                        break;
                    case "down":
                        RemoteController.remoteController.rectDown();
                        break;
                    case "left":
                        RemoteController.remoteController.rectLeft();
                        break;
                    case "right":
                        RemoteController.remoteController.rectRight();
                        break;
                    case "isput":
                        RemoteController.remoteController.isPut();
                        break;
                    case "ispop":
                        RemoteController.remoteController.ispop();
                        break;
                    case "gameover":
                        RemoteController.remoteController.gameover();
                        break;
                    case "keyPause":
                        GameController.localController.pause();
                        break;
                    case "keyResume":
                        GameController.localController.resume();
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("服务器 run 异常: " + e.getMessage());
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (Exception e) {
                    socket = null;
                    System.out.println("服务端 finally 异常:" + e.getMessage());
                }
            }
        }
    }

    public void sendMessage(String str){
        // 这里负责写
        try {
            bufferedWriter.write(str);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}