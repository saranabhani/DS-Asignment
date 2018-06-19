


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class PeerThread extends Thread{

    public Socket socket;
    public VotesChain chain;

    
    
    public PeerThread(Socket clientSocket, VotesChain chain2)
    {
        this.socket = clientSocket;
        this.chain=chain2;


    }

    @Override
    public void run() {
        try
        {
           ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
      	   ObjectInputStream ois = new ObjectInputStream(socket.getInputStream()); 
      	   PrintWriter pw = new PrintWriter(socket.getOutputStream());
      	   Scanner sc = new Scanner(socket.getInputStream());
      	   Server.oiss.add(ois);
      	   Server.ooss.add(oos);
      	   Server.outputList.add(pw);
      	   oos.writeObject(Server.chain);
           oos.flush();
 
      	   while(true)
      	   {
      			 String got= sc.nextLine();
      			   if(got.equals("new vote"))
      			   {
      				 Vote v= (Vote) ois.readObject();
      				Server.chain.add(v);      		      	   
      			   }
      			   else if(got.equals("update me"))
      			   {
      				   pw.println(Server.chain.getVoteschain().size());
      				   pw.flush();
      				   int rep = sc.nextInt();
      				   if(rep != -1)
      				   {
      					   int rec = (int) rep;
      					   for (int i = Server.chain.getVoteschain().size()-1 ; i>Server.chain.getVoteschain().size()-1-rec; i--)
      					   {
      						   oos.writeObject(Server.chain.getVoteschain().get(i));
      						   oos.flush();
      					   }
      				   }
      			   }
      		   }    	   
        }
        catch (IOException e)
        { 	
            e.printStackTrace();
            System.out.println(e);
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    System.out.println(e);
		}
    }
      
}