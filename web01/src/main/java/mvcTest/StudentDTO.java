package mvcTest;
/*
 * DAO나 DTO나 각 역할이 나눠지는데
 * 데이터를 전송하는 과정에서 더 원활하게 데이터를 처리하기위해 객체화 하는 것임
 * VO, DTO, Entity.. 등등이 있음
 * 
 * //** VO (Value Object) 읽기전용 임 (읽기전용이기때문에 getter만 있다고 볼 수 있음, 수정이 불가하니 불변객체 라고 할 수 있음)
//=> 특정 비즈니스 값을 담은 객체로 값을 표현하기 위한용도
//=> 불변객체 immutable object	// 꺼내기만 하고 수정을 할 수 없는 것 -> 불변객체
//   DTO와 유사하나 read only 속성을 가짐
//   그러므로 setter 속성을 띄는 메소드 금지
//=> 특징: 데이터가 전송 과정 중에 변조되지 않음을 보장할 수 있다
//=> 다양한 로직 추가 가능

//** DTO (Data Transfer Object)
//=> 계층간 데이터 교환을 위한 객체
//=> 가변객체 mutable object
//   로직을 포함하지않은 getter/setter 메소드만 가질수 있는 순수 Data 전달용
//=> View 와 통신하며 request, response 처리위해 값의 변경이 유동적 (View Layer)
//=> 네이밍: ~~DTO 

//** 결론 
//=> Spring MyBatis를 쓰는 경우에는 주로 VO라 표현하고 때로는 DTO라 표현하기도 하며
//   Spring JPA를 쓰는 경우에는 Entity 라고 표현한다.
//   그리고 DTO와 VO는 위의 내용처럼 분명한 차이가 있다.
 * 
 * //=> 참고 DTO 와 VO 
//   https://multifrontgarden.tistory.com/182?category=471239 

//---------------------------------------------

//** 추가적 분류
//=> 스프링 JPA를 사용하면 객체와 Table을 구체적으로 매핑하기 때문에
//   Entity 로 구분함.

//** Entity
//=> 실제 DB 테이블과 매핑되는 클래스 (DB Layer)
//=> 가변객체 mutable object
//   로직 포함 가능한 getter/setter 메소드를 가질수 있다
//=> 네이밍: 테이블명과 동일 

//** Domain
//=> 어플리케이션 내 로직들이 관여하는 정보와 활동의 영역,
//   즉 해결하고자하는 업무 영역을 도메인(Domain) 이라한다.
//   예를 들어 "온라인 서점" 도메인은 회원관리, 주문, 결제등의 하위도메인을 가진다.
//=> 이러한 도메인을 개념적으로 표현한것을 도메인 모델이라하고 이러한 분석과정을 통해
//   도출된 결과물을 모델객체라 하며 이것은 Entity와 Value로 구분할 수 있다.
//=> 참고 Domain, Entity, Value(Object)
//   https://doing7.tistory.com/79 

//=> 주로 Entity(Table) 관련 폴더명으로 사용됨  
 * */
//---------------------------------------------
// ** ~DTO 정의
//=> 맴버변수 : private
//=> 외부에서는 setter/getter 로 접근
//=> 자료확인시 편리성을 위해 toString() 메서드 오버라이딩 

// ** 객체 초기화
// 1. setter를 이용
// 2. 생성자를 이용

public class StudentDTO extends JoDTO{
	
	/*
	 * int 보다 짧은 short을 쓰거나
	 * double 대신 float를 쓰거나
	 * 하는게 데이터를 절약하는 용도가 아니래 그래서 굳이 int를 short를 쓰고 하는 일이 그동안 별로 없던 것임
	 * 그리고 내가 개발하기에도 그때그때 맞춰서 타입을 선언하는 것도 손이 많이감
	 * 
	 * */
	
	// 1) private 으로 멤버 변수 정의
	private int sno;
	private String name;
	private int age;
	private int jno;
	private String info;
	private double point;
	private String cname;
	
	// ** 생성자
	// => 매개변수가 없는경우 default 생성자가 만들어짐, 아래 같은 경우는 모든 값을 초기화한 생성자 임
	// => 상속할 때 부모에 생성자가 있으면 자식도 생성자가 있어야 함 그래서 이런 DTO 를 만들적에는 default생성자를 안쓰더라도 default생성자는 써놔야 함
	public StudentDTO() {}
	public StudentDTO(String name, int age, int jno, String info) {
		this.name = name;
		this.age = age;
		this.jno = jno;
		this.info = info;
	}
	public StudentDTO(int sno, String name, int age, int jno, String info) {
		this(name, age, jno, info);
		this.sno = sno;
	}
	public StudentDTO(int sno, String name, int age, int jno, String info, double point, String cname) {
		this(sno, name, age, jno, info);
		this.point = point;
		this.cname = cname;
	}
	public StudentDTO(int sno, String name, int age, int jno, String jname, String project, int captain, String cname) {
		super(jname, project, captain);
		this.sno = sno;
		this.name = name;
		this.age = age;
		this.jno = jno;
		this.cname = cname;
	}
	
//	public StudentDTO(int sno, String name, int age, int jno, String info, double point, String cname, String jname, int captain, String project, String slogan) {
//		super(jno, jname, captain, project, slogan);
//		this.jno = jno;
//		this.sno = sno;
//		this.name = name;
//		this.age = age;
//		this.info = info;
//		this.point = point;
//		this.cname = cname;
//	}
	
	
	
	// 2) getter/setter

	
	
	
	
	@Override
	public String toString() {
		return "StudentDTO [sno=" + sno + ", name=" + name + ", age=" + age + ", jno=" + jno + ", info=" + info
				+ ", point=" + point + ", cname=" + cname + ", getJname()=" + getJname() + ", getCaptain()="
				+ getCaptain() + ", getProject()=" + getProject()+ "]";
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getJno() {
		return jno;
	}
	public void setJno(int jno) {
		this.jno = jno;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public double getPoint() {
		return point;
	}
	public void setPoint(double point) {
		this.point = point;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}

	
	
	
	
	
} // class
