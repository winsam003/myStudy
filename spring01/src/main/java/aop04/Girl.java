package aop04;


public class Girl implements Programmer {
	@Override
	public String doStudying(int n) throws Exception {
		// TODO Auto-generated method stub
		System.out.printf("열심히 게시판 %d개를 만듭니다 => 핵심점 관심사항\n", n);
		if (true) {
			throw new Exception("~~ Error 500*100 => 예외발생");
		}
		return "취업성공";
	} // class
}