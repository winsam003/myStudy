package pageTest;
//** PageMaker : View 에 필요한 값을 완성
//=> 전체Row 갯수 (전체 Page수 계산위해 필요)
//=> 1Page당 표시할 pageNo갯수
//=> view 에 표시할 첫 PageNo
//=> view 에 표시할 끝 PageNo
//=> 출력 가능한 마지막 PageNo (totalRowsCount, rowsPerPage 로 계산)
//=> 이전 PageBlock 으로
//=> 다음 PageBlock 으로

import lombok.Getter;
import lombok.ToString;

//=> Criteria 를 이용해서..

@Getter
@ToString
public class PageMaker {
	private int totalRowsCount;				// 출력대상 전체 Row개수: from DB
	private int displayPageNo=3;			// 1page 당 표시할 pageNo 개수
	private int spageNo;					// View 에 표시할 첫 PageNo: 계산
	private int epageNo;					// View 에 표시할 끝 PageNo: 계산
	private int lastPageNo;					// 출력 가능한 마지막 PageNo
	private boolean prev;					// 이전 PageBlock 으로 
	private boolean next;					// 다음 PageBlock 으로 
	Criteria cri;
	
	// ** 필요값 계산
	// 1) Criteria
	public void setCri(Criteria cri) {
		this.cri=cri;
	} 
	
	// 2) totalRowsCount
	// => 전체 Rows 개수: Read from DB
	// => totalRowsCount을 이용해서 나머지 필요한 값 계산
	public void setTotalRowsCount(int totalRowsCount) {
		this.totalRowsCount=totalRowsCount;
		calcData();
	}
	
	// 3) 나머지 필요한 값 계산
	public void calcData() {
		// 3.1) spageNo, epageNo
		// => currPage가 속한 PageBlock을 찾아서 spageNo, epageNo 구간을 계산
		
		
		// => n개 씩 출력한다고 가정하면 epageNo 는 항상 n의 배수
		//		displayPageNo=3 이면 3, 6, 9, 12, .......

	    // => ex) 17 page 요청 , displayPageNo=3, 일때 17이 몇번째 그룹 인지 계산하려면,
	    //        17/3 -> 5 나머지 2 결론은 정수 나누기 후 나머지가 있으면 +1 이 필요함
	    //    -> Math.ceil(17/3) -> Math.ceil(5.73..) -> 6.0 -> 6번쨰 그룹 16,17,18
	    // 즉, 17이 몇번째 그룹 인지 계산하고, 여기에 * displayPageNo 를 하면 됨.   
	      
	    // ** Math.ceil(c) : 매개변수 c 보다 크면서 같은 가장 작은 정수를 double 형태로 반환 
	    //                   ceil -> 천장, 예) 11/3=3.666..  -> 4
	    // => Math.ceil(12.345) => 13.0      
		
		
		this.epageNo = (int)Math.ceil(cri.getCurrPage()/(double)displayPageNo)*displayPageNo;
		this.spageNo = (this.epageNo - displayPageNo)+1;
		// => 요청받은 currPage 11 인 경우
		// epageNo = (int)Math.ceil(11/3)*3; => 12
		// spageNo = (12 - 3) + 1 => 10
		// 	=> 결과적으로 11은 10, 11, 12 그룹에 속함
		
		// 3.2) lastPageNo를 계산 후 동시에 epageNo의 적합성을 확인해야 함
		this.lastPageNo = (int)Math.ceil(this.totalRowsCount/(double)cri.getRowsPerPage());
		if(this.epageNo > this.lastPageNo) this.epageNo = this.lastPageNo;
		
		// 3.3) prev, next
		prev = this.spageNo==1 ? false : true;
		next = this.epageNo==lastPageNo ? false : true;
	} //calcData
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
