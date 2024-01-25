package iocDI01_xml;

// ** Test1. 절차지향
class SsTV {

	public void turnOn() {
		System.out.println("~~ SsTV turn on ~~");
	}

	public void turnOff() {
		System.out.println("~~ SsTV turn off ~~");
	}

	public void soundUp() {
		System.out.println("~~ SsTV sound up ~~");
	}

	public void soundDown() {
		System.out.println("~~ SsTV sound down ~~");
	}
} // SsTV

class LgTV {

	public void powerOn() {
		System.out.println("~~ LgTV power on ~~");
	}

	public void powerOff() {
		System.out.println("~~ LgTV power off ~~");
	}

	public void volumeUp() {
		System.out.println("~~ LgTV volume up ~~");
	}

	public void volumeDown() {
		System.out.println("~~ LgTV volume down ~~");
	}
} // LgTV

// ** Test2. 객체지향 : 다형성적용
// => interface, 구현을 강제 (메서드 명의 일치 규칙이 생김)
// 인터페이스에서는 생략하면 다 public 임 (추상 이니까)
interface TV {
	void powerOn();

	void powerOff();

	void volumeUp();

	void volumeDown();
}

class SsTVi implements TV {

	public SsTVi() {
		System.out.println("~~ SsTVi 기본생성자 ~~");
	} // 생성자

	@Override
	public void powerOn() {
		// TODO Auto-generated method stub
		System.out.println("~~ SsTVi power on ~~");
	}

	@Override
	public void powerOff() {
		// TODO Auto-generated method stub
		System.out.println("~~ SsTVi power off ~~");
	}

	@Override
	public void volumeUp() {
		// TODO Auto-generated method stub
		System.out.println("~~ SsTVi volume up ~~");
	}

	@Override
	public void volumeDown() {
		// TODO Auto-generated method stub
		System.out.println("~~ SsTVi volume down ~~");
	}
}

class LgTVi implements TV {

	public LgTVi() {
		System.out.println("~~ LgTVi 기본생성자 ~~");
	} // 생성자

	@Override
	public void powerOn() {
		// TODO Auto-generated method stub
		System.out.println("~~ LgTVi power on ~~");
	}

	@Override
	public void powerOff() {
		// TODO Auto-generated method stub
		System.out.println("~~ LgTVi power off ~~");
	}

	@Override
	public void volumeUp() {
		// TODO Auto-generated method stub
		System.out.println("~~ LgTVi volume up ~~");
	}

	@Override
	public void volumeDown() {
		// TODO Auto-generated method stub
		System.out.println("~~ LgTVi volume down ~~");
	}
}

// ** Test3. Factory 패턴 (IOC/DI 입문)
// => getBean 메서드의 매개변수로 요청을 전달
class BeanFactory {
	
	public TV getBean(String tv) {
		if(tv!=null && tv.equals("ss")) return new SsTVi();
		else if(tv!=null && tv.equals("lg")) return new LgTVi();
		else return null;
	} // getBean
} // BeanFactory






public class TVUser01 {

	public static void main(String[] args) {
		// ** Test1. 절차지향
		System.out.println("** Test1. 절차지향 **");
//		SsTV tv = new SsTV();
//		tv.turnOn();
//		tv.soundDown();
//		tv.soundUp();
//		tv.turnOff();
		// => 여기서 만약 TV 교체 필요시 완전 재작성 해야 함
		// => 그래서 위에 코드 다 주석하고
		LgTV tv = new LgTV();
		tv.powerOn();
		tv.volumeDown();
		tv.volumeUp();
		tv.powerOff();
		// => 메서드마저 재활용할게 하나도 없음 완전 다시만들어야 함
		// => 이런 것이 절차지향임

		// ** Test2. 객체지향
		// => 기본특징 : 캡슐화, 상속, 추상, 다형성(*)
		// => 다형성 적용
		// -> 관련성이 없는 두객체를 하나의 인터페이스로 묶어줌, 규칙성 부여
		// -> 인터페이스에 정의된 메서드만 사용했다는 의미 (그러므로 교체가능)
		// -> TV 교체 필요 : 우측의 클래스만 교체 (단, 소스코드수정-재컴파일 이 필요함)
		System.out.println("** Test2. 객체지향 **");
		TV tvi = new SsTVi(); // LgTVi();
		tvi.powerOn();
		tvi.powerOn();
		tvi.volumeUp();
		tvi.volumeDown();
		tvi.powerOff();

		
		
		// ** Test3. 소스코드 수정없이 실시간 객체 교체
		// => 객체를 생성해서 교체해줄 역할자가 필요 : Factory 패턴 (IOC/DI 입문)
		// => user 클래스의 요구사항(필요한 클래스, 의존성_Dependency) 을 Factory 에게 전달하는방법
		// => 3가지 : xml, @, JavaConfig (JavaCode)
		System.out.println("** Test3. Factory 패턴 (IOC/DI 입문) **");
		BeanFactory bf = new BeanFactory();
		TV tvf = bf.getBean(args[0]);
		// => 소스코드 수정없이 실시간으로 요청 전달 받음 : main의 매개변수 활용
		// => main의 매개변수가 null 인 경우에는 args[0] 의 값이 없기 때문에: java.lang.ArrayIndexOutOfBoundsException
		if(tvf!=null) {
			tvf.powerOn();
			tvf.powerOn();
			tvf.volumeUp();
			tvf.volumeDown();
			tvf.powerOff();

		}else {
			System.out.println("** TV를 선택하지 않았습니다. **");
		}
		
		

	} // main

}// class
