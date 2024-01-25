package jdbc02;

public class JoDTO {
	private int jno;
	private String jname;
	private int captain;
	private String project;
	private String slogan;
	
	
	public JoDTO() {}
	public JoDTO(String jname, String project, int captain) {
		this.jname = jname;
		this.captain = captain;
		this.project = project;
	}
	public JoDTO(int jno, String jname, int captain, String project, String slogan) {
		this.jno = jno;
		this.jname = jname;
		this.captain = captain;
		this.project = project;
		this.slogan = slogan;
	}
	public int getJno() {
		return jno;
	}
	public void setJno(int jno) {
		this.jno = jno;
	}
	public String getJname() {
		return jname;
	}
	public void setJname(String jname) {
		this.jname = jname;
	}
	public int getCaptain() {
		return captain;
	}
	public void setCaptain(int captain) {
		this.captain = captain;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getSlogan() {
		return slogan;
	}
	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}
	@Override
	public String toString() {
		return "JoDTO [jno=" + jno + ", jname=" + jname + ", captain=" + captain + ", project=" + project + ", slogan="
				+ slogan + "]";
	}
	
	
	
	
	
	
	
}
