import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Scanner;

public class Peer {

	private static VotesChain chain;
	private static ObjectOutputStream oos;
	private static ObjectInputStream ois;
	private static    PrintWriter pw;
	private static Scanner sc;
	 public static void main(String[] args) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		
		try {
			
			Socket s = new Socket("localhost",12345);
			ois= new ObjectInputStream(s.getInputStream());
		    oos = new ObjectOutputStream(s.getOutputStream());
		    pw = new PrintWriter(s.getOutputStream());

		    VotesChain vc= (VotesChain) ois.readObject();
			if (vc.isValid())  chain=vc;
			else System.out.println("The chain I recevied is not valid");
			  
		    displaymenu();
		    	 Scanner sc1 = new Scanner (System.in);
		    	 sc = new Scanner(s.getInputStream());
	
		    	while(true)
		    	{
		    		
		    		int o=sc1.nextInt();
		    
		    			if (o==1) 
		    			{ 
		    
		    			getupdates();
		    			addvote(); 
		    			displaymenu();
		    			}
			    	else if(o==2)
			    		{ 
			    		getupdates();
			    		chain.getresult(); 
			    		displaymenu();
			    		}
			    	else if (o==3)
			    		{
			    		getupdates();
			    		chain.display();
			    		displaymenu(); 
			    		}
			    	else 
			    		System.out.println("invalid input");

		    	}	
		    	
		    
		    

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
		
		

	}
	 
	  private static void getupdates() throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
			pw.println("update me");
			pw.flush();
			int num = (int) sc.nextInt();
			if(num > chain.getVoteschain().size())
			{
				pw.println(num - chain.getVoteschain().size());
    			pw.flush();
    			
				for (int i=0;i< num - chain.getVoteschain().size(); i++)
				{
					Vote v = (Vote) ois.readObject();
					chain.add(v);
					
				}
			}else { 
				pw.println(-1);
				pw.flush();
			}
	}

	private static void displaymenu() {
		
		  System.out.println("1- add vote ");
		  System.out.println("2- get voting result ");
		  System.out.println("3- display the chain ");
		
	}

	public static  void addvote() throws IOException
	    {
	    	String id,name;
	    	Date date =new Date();
	    	Scanner sc = new Scanner(System.in);
	    	
	    	System.out.println("Enter your name :");
	    	name = sc.nextLine();
	    	System.out.println("Enter your ID number :");
	    	id = sc.nextLine();
	    	Voter v = new Voter(id,date,name);
	    	String vfor="";
	    	boolean done = false;
	    	
	    	while (!done)
	    	{
	    		System.out.println("Voting for:");
	    		System.out.println("1- Party 1");
	    		System.out.println("2- Party 2");
	    		System.out.println("3- Party 3");
	    		
	    		int o= sc.nextInt();
	    		
	    		switch(o) {
	    		case 1: 
	    			vfor="Party 1"; done=true; break;
	    		case 2:
	    			vfor="Party 2"; done=true; break;
	    		case 3:
	    			vfor="Party 3"; done=true; break;	
	    		default: done=false;
	    		}
	    			
	    	}
	    	
	    	Vote vv = new Vote(date,v,vfor,chain.getVoteschain().get(chain.getVoteschain().size()-1).getHash());
	    	if(chain.add(vv))
	    	{
	        pw.println("new vote");
	        pw.flush();
	    	oos.writeObject(vv);
	        oos.flush();
	    	}
	        
	    }


}
