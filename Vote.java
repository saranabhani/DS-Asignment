import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

public class Vote implements Serializable {
	
	private int index ;
	
	private Date timestamp;
	
	private Voter voter;
	
	private String vfor;
	
	private String hash;
	
	private String previousHash;

	public Vote(Date timestamp, Voter voter, String vfor,String previous) {
		super();
		this.timestamp = timestamp;
		this.voter = voter;
		this.vfor = vfor;
		this.previousHash=previous;
		this.hash= computehash();
	}
	
	
public Vote(Date date, Voter v, String string) {
		// TODO Auto-generated constructor stub
	this.timestamp = date;
	this.voter = v;
	this.vfor = string;
	this.previousHash=" ";
	this.hash= computehash();
	}


public String computehash() {
		
		String dataToHash = "" + this.timestamp + this.previousHash + this.voter.getdata();
		
		MessageDigest digest;
		String encoded = null;
		
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(dataToHash.getBytes(StandardCharsets.UTF_8));
			encoded = Base64.getEncoder().encodeToString(hash);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		this.hash = encoded;
		return encoded;
		
	}


public Date getTimestamp() {
	return timestamp;
}


public void setTimestamp(Date timestamp) {
	this.timestamp = timestamp;
}


public Voter getVoter() {
	return voter;
}


public void setVoter(Voter voter) {
	this.voter = voter;
}


public String getVfor() {
	return vfor;
}


public void setVfor(String vfor) {
	this.vfor = vfor;
}


public String getHash() {
	return hash;
}


public void setHash(String hash) {
	this.hash = hash;
}


public String getPreviousHash() {
	return previousHash;
}


public void setPreviousHash(String previousHash) {
	this.previousHash = previousHash;
}


public int getIndex() {
	return index;
}


public void setIndex(int index) {
	this.index = index;
}


@Override
public String toString() {
	return "Vote [timestamp=" + timestamp + ", voter=" + voter + ", vfor=" + vfor + ", hash=" + hash + ", previousHash="
			+ previousHash + "]";
}




	
	
	
	
	
	
	
	
	
	
}
