package j01_basic;

import jdbc02.JoDTO;

class Store {
	private Object data;
	public Object getDate() { return data; }
	public void setDate(Object data) { this.data = data; }
}	//Store

class StoreG<T>{
	private T data;	// T는 아직 데이터 타입을 정하지 않음! 이란 의미고 이대로 냅두면 오타인지 제네릭인지 알 수 없음 그래서 클래서를 정의 하면서 <T>임
	public T getDate() { return data; }
	public void setDate(T data) { this.data = data; }
				
}	// StoreG


class GenArray<T>{
	private T[] arr;
	public T[] getData() { return arr;}
	public void setData(T[] arr) { this.arr = arr; }
	
	public void arrayPrint() {
		for(T a: arr) {
			System.out.println(a);
		}	// for
	}	// arrayPrint
}	// GenArray


public class Gn01_StoreTest {

	public static void main(String[] args) {
		// 1. Objecr로 사용하는 경우 (제네릭이라는 것이 등장하기 전 기존 방식)
		Store s1 = new Store();
		s1.setDate("짜장면");
		s1.setDate(123);		// int <=> Integer으로 취급함 (래퍼클래스) : 자동형변환 -> 오토박싱이라고 함
		s1.setDate(123.456);	// double <=> Double으로 취급함 (래퍼클래스) : 자동형변환 -> 오토박싱이라고 함
		s1.setDate(123456789L);	// 이런식으로 해도 상관없음 (L은 long타입)
//		s1.setDate(new JoDTO(7, "Banana", 77, "파이팅입니다","갓승삼입니다 제네릭을 공부하고있습니다"));
		System.out.println("** Test 1 => "+s1.getDate());
		
		// ** 단점 Test
//		String s = (String) s1.getDate();
//		// => 컴파일 오류는 없으나 런타임 오류 발생
//		System.out.println("** 단점 Test => "+s);
		
		/*
		 * 이런 식으로 만들면 실제로 run은 안되는 형변환이지만 컴파일에서는 문제가 없음 -> 잠재적 에러의 가능성이 높음
		 * 그래서 나온게 제네릭임 얘는 int으로만 쓸거다 얘는 String으로만 쓸거다 하면서 바꿀 수 있음
		 * */
		
		
		// 2. Generic StoreG
		StoreG g1 = new StoreG();		// 그냥 이렇게 하면 경고가 나옴 -> 제네릭인데 그냥 선언하면 어쩌니 라는거임 -> 사용은 할 순 있지만 Object를 이용한거랑 같음
		StoreG<String> g2 = new StoreG<String>();
		g2.setDate("스트링만 가능함");		// 자동완성을 켜서 setData를 보면 String 타입만 가능함
//		g2.setDate(12345);				// String만 가능한데 숫자를 넣으면 컴파일 에러가 나옴
		
		/*
		 * 그럼 그냥 원하는 타입으로 만들면 되는거 아닌가?
		 * 어떤 객체를 만드는데 모든 타입에 적용되는 타입을 만들고 싶음
		 * list, arrayList, set.. 등을 만드는데 이 객체에는 꼭 String만 담거나 Car타입만 담거나 하는게 아니라 어떤 타입이던 담을 수 있는 형식의 자료구조를 만들고 싶을때
		 * 제네릭을 쓰면 편함
		 * 
		 * 보통 제네릭이 가장많이 쓰이는 곳이 컬렉션임
		 * 컬렉션은 자료를 담는 곳에 사용하기 때문임
		 * 
		 * */
		
		StoreG<Integer> g3 = new StoreG<Integer>();
		g3.setDate(12345);				// Integer만 가능함
//		g3.setDate("12345");			// Integer말고 다른 것을 넣으면 컴파일 오류 발생 함
		
		
		// GenArray Test
		GenArray<String> ga1 = new GenArray<String>();
		GenArray<Integer> ga2 = new GenArray<Integer>();
		GenArray<Character> ga3 = new GenArray<Character>();
		GenArray<JoDTO> ga4 = new GenArray<JoDTO>();
		// 1) String
		String[] ss = {"가","나","다","라","마","사"};
		ga1.setData(ss);
		ga1.arrayPrint();
		
		// 2) Integer
		Integer[] ii = {1, 2, 3, 4, 5, 6};
		ga2.setData(ii);
		ga2.arrayPrint();
		
		// 3) Character (char)
		Character[] cc = {'A', 'B', 'C', 'D', 'E', 'F'};
		ga3.setData(cc);
		ga3.arrayPrint();
		
		// 4) 객체
		JoDTO[] jj = {new JoDTO(), new JoDTO(), new JoDTO()};
		ga4.setData(jj);
		ga4.arrayPrint();
		
		
		StoreG<? extends JoDTO> g12 = new StoreG();
//		g12.setDate(new JoDTO());
		
		
		
	} // main

} // class

//------------------------------------------------------------
//** Generic
//=> 컬렉션(Collection:자료구조) 을 보강해준 기능
//=> 컴파일 단계에서 객체의 자료형을 선언(정의) 해주면
// 다른 타입의 자료가 저장될수 없도록 해주는 기능

//** Generic 클래스 정의
//=> 클래스 이름 옆에 <> 사이에 알파벳 1글자를 사용하여 
// Generic Type 명을 선언해줌 
// ex : <T> 처럼 "<" 와 ">" 사이에 선언 
//=> 대문자로 T, E 등을 많이 사용 
// Type 의미로 "T" 를 주로 사용
//=> Generic 타입으로 클래스를 사용한다는 의미 
//=> 제네릭으로 기본 자료형(int, float, double....)은 사용할 수 없지만
// 기본자료형의 wrapper 클래스를 이용할 수있다. 

//** Generic 타입제한 (사용시, Wildcards_와일드카드타입 이용으로)
//=> <?>
// Unbounded Wildcards (제한없음_모든 클래스나 인터페이스 타입 가능)
//=> <? extends 클래스명>
// Upper Bounded Wildcards (상위클래스 제한_같거나 하위 타입 가능)
//=> <? super 클래스명>
// Lower Bounded Wildcards (하위클래스 제한_ 같거나 상위타입 가능)

//=> 정의할때: <T> , <T extends 클래스명> , <T super 클래스명>
//------------------------------------------------------------