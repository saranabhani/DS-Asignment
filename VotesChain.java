import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class VotesChain implements Serializable {
	
	
	private List<Vote> voteschain;
	
	public VotesChain() {
		super();
		// TODO Auto-generated constructor stub
		this.voteschain=new ArrayList<Vote>();
    	Date date =new Date();    	
    	Voter v = new Voter("pn109909",date,"myname");
    	Vote vv = new Vote(date,v,"Party 1");
    	this.voteschain.add(vv);
		
	}

	public VotesChain(List<Vote> voteschain) {
		super();
		this.voteschain = voteschain;
	}

	public List<Vote> getVoteschain() {
		return voteschain;
	}
	
	public void setVoteschain(List<Vote> voteschain) {
		this.voteschain = voteschain;
	}
	
	
	public boolean add(Vote v)
	{
	 if(checkforduplicate(v.getVoter()))
	 {
		this.voteschain.add(v);
		if (!this.isValid())
		{	this.voteschain.remove(this.voteschain.size()-1);
			System.out.println("last record is not valid");
			return false;
		}
		return true;
	 } else { System.out.println("vote with the same id already exist"); return false;}

	}
	
private boolean checkforduplicate(Voter voter) {
		for (int i=1; i<voteschain.size();i++)
		{
			if (voteschain.get(i).getVoter().getId().equals(voter.getId()))
			{
				return false;
			}
		}
		
		return true;	
	}

public boolean isValid() {
		
		for(int i=voteschain.size()-1; i>0; i--) {
			if(   !(voteschain.get(i).getHash().equals(voteschain.get(i).computehash()))   ) {
				return false;
			}
			
			if(  !(voteschain.get(i).getPreviousHash().equals(voteschain.get(i-1).computehash()))  ) {
				return false;
			}
		}
		
		return true;
		
	}


public void display()
{
	for (int i=1; i<voteschain.size();i++)
	{
		System.out.println(voteschain.get(i));
	}
}

public void getresult() {
	// TODO Auto-generated method stub
	
	int p1n=0,p2n=0,p3n=0;
	double p1p,p2p,p3p;
	int total = voteschain.size()-1;
	if (total > 0)
	{
	for (int i=1; i< voteschain.size();i++)
	{
		if (voteschain.get(i).getVfor().equals("Party 1")) p1n++;
		else if (voteschain.get(i).getVfor().equals("Party 2")) p2n++;
		else if (voteschain.get(i).getVfor().equals("Party 3")) p3n++;
	}
	
	p1p= p1n * 100 / total ;
	p2p= p2n * 100 / total ;
	p3p= p3n * 100 / total ;
	
	System.out.println("Party 1 : "+ p1p + "%");
	System.out.println("Party 2 : "+ p2p + "%");
	System.out.println("Party 3 : "+ p3p + "%");
	}

	else System.out.println("no votes yet ");
	
}

	

}
