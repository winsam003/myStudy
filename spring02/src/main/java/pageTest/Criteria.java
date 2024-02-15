package pageTest;

import lombok.Getter;
import lombok.ToString;

//** Criteria : (판단이나 결정을 위한) 기준
//=> 출력할 Row 를 select 하기 위한 클래스
//=> 이것을 위한 기준 값들을 관리

//** PageMaker : UI에 필요한 정보 완성

//** Paging 을 하려면 ... **
//=> 1Page에 출력할 Row 갯수 :  5개
//=> 현재 출력(요청) Page
//=> 출력할 List (Rows) 
// -> start Row 순서번호 : 계산필요
//=> Criteria Class 에 정의 

//=> 1Page 출력 PageNo 갯수 : 10개
// -> PagreBlock 의 First Page No 
// -> PagreBlock 의 Last Page No
// -> 전진, 후진 표시여부
// -> go 전체의 First Page 표시여부
// -> go 전체의 Last Page 표시여부
//=> PageMaker Class 로 처리 

@Getter
@ToString
public class Criteria {

	private int rowsPerPage;	// 1Page 당 출력할 게시글 수
	private int currPage;		// 현재 출력해야 하는 페이지 (요청받은 페이지)
	private int sno;			// 출력할 게시글의 첫 순서번호: 계산필요 (5개씩 출력하는 페이지의 3페이지인 경우 15일 것임 (limit는 16이 아니라 15여야 함))
	private int eno;			// 출력할 게시글의 마지막 순서번호: 계산필요(Oracle만 계산이 필요함 -> mysql은 limit이 있기때문에 계산이 필요 없음)

	// 1) 기본생성자 : 기본값 초기화
	public Criteria() {
		this.rowsPerPage=5;
		this.currPage=1;
	}//생성자
	
	// 2) 요청시 값 갱신
	// 2-1) currPage
	// 1보다 currPage가 크면 매개변수 currPage를 넣어주고 음수같은 값이 들어오면 1로 재 초기화
	public void setCurrPage(int currPage) {
		if(currPage>1) this.currPage=currPage;
		else this.currPage=1;
	}//setCurrPage

	// 2.2) rowsPerPage 
	// => 1페이지당 보여줄 Row(Record,튜플) 갯수 확인
	// => 제한조건 점검 ( 50개 까지만 허용)
	// => 당장은 사용하지 않지만 사용가능하도록 작성   
	public void setRowsPerPage(int rowsPerPage) {
		if(rowsPerPage>5 && rowsPerPage<51) {
			this.rowsPerPage=rowsPerPage;
		}else {
			this.rowsPerPage=5;
		}
	}
	
	// 2.3) setSnoEno: sno, eno 계산
	public void setSnoEno() {
		if(this.sno<1) this.sno=1;
		this.sno=(this.currPage-1)*this.rowsPerPage;	
		// mysql의 limit 구문은 limit 5,5 하면 6번째부터 5개라는 의미임 그래서 바로 위 65행에서 +1 안해도 괜찮음
		// Oracle의 구문은 다름 : between 6 and 10
		// sno=> this.sno=(this.currPage-1)*this.rowsPerPage+1;
		// eno=> this.eno=this.sno+this.rowsPerpage-1;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}//class
