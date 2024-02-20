package aop03;


public class Girl implements Programmer {
	@Override
	public void doStudying() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("열심히 게시판을 만듭니다 => 핵신점 관심사항");
		if (true) {
			throw new Exception("~~ Error 500*100 => 예외발생");
		}
	} // class
}