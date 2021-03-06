package com.lab.bus.tracker;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author DEEPANKAR
 * @since 31-03-2016.
 */

public class SocketClient {

    public static String SERVERIP = "192.168.137.15";
    public static final int SERVERPORT = 12354;
    private OnMessageReceiveListener mMessageListener = null;
    private boolean mRun = false;
    private PrintWriter out;


    public SocketClient(OnMessageReceiveListener listener) {
        mMessageListener = listener;
      //  SERVERIP = serverIp;
    }

    /**
     * Sends the message entered by client to the server
     * @param message text entered by client
     */
    public void sendMessage(String message){
        if (out != null && !out.checkError()) {
            out.println(message);
            out.flush();
        }
    }

    public void stopClient(){
        mRun = false;
    }

    public void run() {

        mRun = true;
        String serverMessage;

        try {

            InetAddress serverAddr = InetAddress.getByName(SERVERIP);
            Socket socket = new Socket(serverAddr, SERVERPORT);

            try {
                //out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                Log.e("TCP Client", "C: Done.");
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                //in this while the client listens for the messages sent by the server
                while (mRun) {
                   serverMessage = in.readLine();
                    if (serverMessage != null && mMessageListener != null) {
                        mMessageListener.messageReceived(serverMessage);
                    }
                }

            } catch (Exception e) {
                Log.e("TCP", "S: Error", e);
            } finally {
                socket.close();
            }

        } catch (Exception e) {
            Log.e("TCP", "C: Error", e);
        }

    }

    public interface OnMessageReceiveListener {
        void messageReceived(String message);
    }
}
