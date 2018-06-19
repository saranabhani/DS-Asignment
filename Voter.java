import java.io.Serializable;
import java.util.Date;

public class Voter implements Serializable {

	private String id;
	
	private Date bdate;
	
	private String name;

	public Voter(String id, Date bdate, String name) {
		super();
		this.id = id;
		this.bdate = bdate;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getBdate() {
		return bdate;
	}

	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getdata() {
		// TODO Auto-generated method stub
		return " " + id + " " + bdate + " " + name + " ";

	}

	@Override
	public String toString() {
		return "Voter [id=" + id + ", bdate=" + bdate + ", name=" + name + "]";
	}

	
	
	
	
}
