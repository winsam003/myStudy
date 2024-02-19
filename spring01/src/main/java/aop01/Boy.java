package aop01;

import java.util.Random;

public class Boy implements Programmer {
	
	@Override
	public void doStudying() {
		// TODO Auto-generated method stub

		System.out.println("프로젝트 과제를 합니다. => Before 단계");
		try {
			
			System.out.println("열심히 회원관리를 만듭니다 => 핵신점 관심사항");
			if(new Random().nextBoolean()) {
				throw new Exception("~~ Error 500*100 => 예외발생");
			}else {
				System.out.println("~~ 회원가입이 잘 됩니다. => 핵심적 관심사항 정상종료");
			}
			
		} catch (Exception e) {
			System.out.println("** Boy Exception =>" +e.toString());
			System.out.println("밤새워 수정 합니다.		=> 예외발생으로 핵심적 관심사항 비정상적으로 종료");
			// TODO: handle exception
		}finally {
			System.out.println("** finally: 무조건 제출합니다 ~~ => 무조건 종료");
		}
		
		
	} // doStudying
	
} // class
