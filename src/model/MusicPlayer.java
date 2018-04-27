package model;

import java.applet.AudioClip;
import java.io.*;
import java.applet.Applet;
import java.net.MalformedURLException;
import java.net.URL;
/**
 * ���ֿ���
 */
public class MusicPlayer {

    static volatile boolean running =false;
    static volatile boolean turnOn =false;
    private static String bgmUrl="Music\\bgm.wav";
    private static String actionUrl="Music\\action.wav";
    
    private static AudioClip bgmAudioClip;
    private static AudioClip actionAudioClip;
    
    public static void bgmPlay(){
    	if(!turnOn)
    		return;
    	if(bgmAudioClip==null){
    		try {
                URL cb;
                File file = new File(bgmUrl);
                cb = file.toURL();
                bgmAudioClip = Applet.newAudioClip(cb);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
    	}
    	try{
    		bgmAudioClip.loop();
    		running =true;
    	}catch(Exception exception){
    		exception.printStackTrace();
    	}
    	
    }
    public static void bgmStop(){
    	try{
            bgmAudioClip.stop();
            running =false;
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public static void actionPlay(){
    	if(!turnOn)
    		return;
    	if(actionAudioClip==null){
    		try {
                URL cb;
                File file = new File(actionUrl);
                cb = file.toURL();
                actionAudioClip = Applet.newAudioClip(cb);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
    	}
    	try{
    		actionAudioClip.play();
    	}catch(Exception exception){
    		exception.printStackTrace();
    	}
    }
    /**
     * �����Ƿ���������
     * @return
     */
    public static boolean isRunning(){
        return running;
    }
    /**
     * �����Ƿ�������ֿ���
     * û�򿪱�ʾ����״̬
     * @return
     */
    public static boolean isturnOn(){
    	return turnOn;
    }
    /**
     * �����Ƿ���
     * @param turn
     */
    public static void setturnOn(Boolean turn){
    	turnOn=turn;
    }
    
}

