

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class Server {

    public static ArrayList<PrintWriter> outputList;
    public static ArrayList<BufferedReader> inputList;
    public static ArrayList<ObjectOutputStream> ooss;
    public static ArrayList<ObjectInputStream> oiss;
    public static VotesChain chain;
    public static ArrayList<PeerThread> pths;

    
    
    
    public Server() {
    	 outputList = new ArrayList<>();
         inputList = new ArrayList<>();
         ooss = new ArrayList<>();
         oiss = new ArrayList<>();
         chain = new VotesChain();
  
         pths=new ArrayList<>();
	}

	public static void main(String args[]) throws IOException
    {
		Server s=new Server();
       
        s.acceptAndConnect();

    }

    public void acceptAndConnect()
    {

        try 
        {
        	ServerSocket ss = new ServerSocket(12345);
        	System.out.println("Server Created");
			while(true) {
				
				Socket s = ss.accept();
				Object lock =new Object();
				new Thread(new PeerThread(s,chain)).start();
				
			}
        
        }
        catch (IOException e)
        {
        	
            e.printStackTrace();
            System.out.println(e);
        }

    }


}