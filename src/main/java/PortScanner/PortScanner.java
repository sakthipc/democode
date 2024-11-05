package PortScanner;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class PortScanner {

    private static final int TIMEOUT = 300; // Timeout in milliseconds

    // Method to check if a port is open on a given IP
    public static boolean isPortOpen(String ipAddress, int port) {
        try (Socket socket = new Socket()) {
            SocketAddress socketAddress = new InetSocketAddress(ipAddress, port);
            socket.connect(socketAddress, TIMEOUT); // Try to connect within the timeout period
            return true; // If connected successfully, the port is open
        } catch (IOException e) {
            return false; // If an exception occurs, the port is not open
        }
    }

    // Method to scan a range of ports on a given IP address
    public static void scanPorts(String ipAddress, int startPort, int endPort) {
        for (int port = startPort; port <= endPort; port++) {
            if (isPortOpen(ipAddress, port)) {
                System.out.println("Port " + port + " is open");
            }
        }
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java PortScanner <IP> <startPort> <endPort>");
            
        }

        String ipAddress = "";
        try {
            InetAddress localHost = InetAddress.getLocalHost();  // Gets the local machine's IP address
            ipAddress = localHost.getHostAddress();
            System.out.println("Local IP address: " + ipAddress);
        } catch (IOException e) {
            System.out.println("Unable to retrieve local IP address.");
            return;
        }
        int startPort = 80;
        int endPort = 200;

        // Print header message before scanning
        System.out.println("Scanning IP: " + ipAddress + " from port " + startPort + " to port " + endPort);

        // Start scanning the ports
        scanPorts(ipAddress, startPort, endPort);

        // Print end message when scanning is finished
        System.out.println("Scanning complete.");
    }
}
