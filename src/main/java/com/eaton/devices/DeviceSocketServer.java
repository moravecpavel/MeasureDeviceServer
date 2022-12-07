package com.eaton.devices;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * This class implements java socket server reading messages from measurement devices
 * @author pamor
 *
 */

public class DeviceSocketServer {

    private static ServerSocket server;

    //socket server port on which it will listen
    private static final int PORT = 9877;

    public static void main(String args[]) throws IOException, ClassNotFoundException {

        boolean  requestToQuit = false;

        String   deviceType;
        String   deviceName;
        String   measurementUnit;
        int      measuredValue;

        int      cntAll = 0;
        int      cnt;

        Map<String, Integer> mapDeviceTypeCount = new HashMap<String, Integer>();
        Map<String, Integer> mapDeviceNameCount = new HashMap<String, Integer>();

        // create the socket server object
        server = new ServerSocket(PORT);

        //  server listens until receives 'quit' message

        while (true) {

            System.out.println("Waiting for the client request");
            // creating socket and waiting for client connection
            Socket socket = server.accept();

            //read from socket to ObjectInputStream object
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            //convert ObjectInputStream object to String
            String message = (String) ois.readObject();
            System.out.println("Message Received: " + message);

            requestToQuit = message.equalsIgnoreCase("quit");

            //create ObjectOutputStream object
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            if (requestToQuit) {

                oos.writeObject("Your request to quit accepted and server is qoing down");

            } else {

                // Message parsing

                ArrayList arrListMsgItems = new ArrayList(Arrays.asList(message.split(";")));

                deviceName      = arrListMsgItems.get(0).toString();
                deviceType      = arrListMsgItems.get(1).toString();
                measurementUnit = arrListMsgItems.get(2).toString();
                measuredValue   = Integer.valueOf((String) arrListMsgItems.get(3));


                // Total messages count
                cntAll++;

                // Count by device name
                try {
                    cnt = mapDeviceNameCount.get(deviceName);
                }
                catch (NullPointerException e) {
                    cnt = 0;
                }
                mapDeviceNameCount.put(deviceName, ++cnt);


                // Count by device type
                try {
                    cnt = mapDeviceTypeCount.get(deviceType);
                }
                catch (NullPointerException e) {
                    cnt = 0;
                }
                mapDeviceTypeCount.put(deviceType, ++cnt);


                // Return message processing response to the client
                oos.writeObject(deviceType + " " + deviceName + " measured out " + measuredValue + measurementUnit + ", measurement No." + cnt + ", overall message No." + cntAll);

            }

            // Close resources

            ois.close();
            oos.close();
            socket.close();

            // Terminate the server if client sends quit request

            if (requestToQuit) break;

        }

        System.out.println("\nShutting down Socket server....");

        //close the ServerSocket object

        server.close();


        // Report message count totals

        System.out.println("\n=== Meessage counts by device name ===");
        for(Map.Entry m:mapDeviceNameCount.entrySet()) {
            System.out.println(m.getKey() + " - " + m.getValue() + " message(s)");
        }

        System.out.println("\n=== Meessage counts by device type ===");
        for(Map.Entry m:mapDeviceTypeCount.entrySet()) {
            System.out.println(m.getKey() + " - " + m.getValue() + " message(s)");
        }

        System.out.println("\n=== Total message count: " + cntAll + " ===");

    }

}