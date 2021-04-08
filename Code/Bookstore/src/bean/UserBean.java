package bean;

public class UserBean {
	
	private String name;
	private String user_name;
	private int addr;
	private String password;
	private String type;
	
	public UserBean(String name, String user_name, int addr, String type,String password) {
		super();
		this.name = name;
		this.user_name = user_name;
		this.addr = addr;
		this.password = password;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getAddr() {
		return addr;
	}
	public void setAddr(int addr) {
		this.addr = addr;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
