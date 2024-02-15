package pageTest;
//** PageMaker : View 에 필요한 값을 완성
//=> 전체Row 갯수 (전체 Page수 계산위해 필요)
//=> 1Page당 표시할 pageNo갯수
//=> view 에 표시할 첫 PageNo
//=> view 에 표시할 끝 PageNo
//=> 출력 가능한 마지막 PageNo (totalRowsCount, rowsPerPage 로 계산)
//=> 이전 PageBlock 으로
//=> 다음 PageBlock 으로

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.ToString;

//=> Criteria 를 이용해서..

@Getter
@ToString
public class PageMaker {
	private int totalRowsCount;				// rows의 전체 Row개수: from DB (총 게시글의 수)
	private int displayPageNo=3;			// 1page 당 표시할 pageNo 개수 (총 게시글이 20개이고 5개씩 출력하면 4개의 페이지가 있겠지만 3개씩만 보여줌)
	private int spageNo;					// View 에 표시할 첫 PageNo: 계산	(총 게시글이 20개이고 5개씩 출력할때 1페이지면 출력하는 [1 2 3] 중 spageNo은 1임)
	private int epageNo;					// View 에 표시할 끝 PageNo: 계산	(총 게시글이 20개이고 5개씩 출력할때 1페이지면 출력하는 [1 2 3] 중 epageNo은 3임)
	private int lastPageNo;					// 출력 가능한 마지막 PageNo		(총 게시글이 20개이고 5개씩 출력할때 lastPageNo은 4 / 4페이지가 마지막임)
	
											// 여기서 epageNo 은 4페이지를 출력할때 [4 5 6] 중 6이 될건데
											// 만약 총 게시글이 20개라면 lastPageNo 은 4가 됨
											// epageNo = 6 / lastPageNo = 4 으로 둘 이 다르므로 둘이 같은지 확인하는 코드가 필요 함
	
	
	private boolean prev;					// 이전 PageBlock 으로 
	private boolean next;					// 다음 PageBlock 으로 
	SearchCriteria cri;
	
	// ** 필요값 계산
	// 1) Criteria
	// => ver01: Criteria
	// => ver02: SearchCriteria
	public void setCri(SearchCriteria cri) {
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
	
	
	
	
	   // 4) QueryString 자동 만들기
	   // ** 패키지 org.springframework.web.util
	   // => 웹개발에 필요한 많은 유틸리티 클래스 제공
	   // => UriComponents , UriComponentsBuilder
	   //     Uri를 동적으로 생성해주는 클래스,
	   //     파라미터가 조합된 uri를 손쉽게 만들어줌
	   // => ?currPage=7&rowsPerPage=10 이것을 만들어줌
	   //     ? 부터 만들어지므로 jsp Code에서 ? 포함하지 않도록 주의    
	   
	   // ** ver01
	   // => QueryString 자동생성 
	   //    ?currPage=4&rowsPerPage=3 
	
	public String makeQuery(int currPage) {
		UriComponents uriComponents = 
				UriComponentsBuilder.newInstance()
				.queryParam("currPage", currPage)
				.queryParam("rowsPerPage", cri.getRowsPerPage())
				.build();
				
		
		return uriComponents.toString();				
	} // makeQuery
	
	   // ** ver02
	   // => makeQuery + search 조건 추가 (Paging 시에도 조건이 유지되도록 해줘야함)
	   // => ?curPage=1&rowsPerPage=5&searchType=t&keyword=Java
	   
	   // ** 배열Type check 처리 : Map 으로처리
	   // => ?curPage=1&rowsPerPage=5&searchType=t&keyword=Java&check=admin&check=banana
	   //    위의 쿼리스트링에서 check 부분은 몇개일지 모름
	   // => UriComponents 에서 Multi Value 처리 :  queryParams(MultiValueMap<String, String> params) 
	   
	   // ** MultiValueMap
	   // => 키의 중복이 허용됨 즉, 하나의 키에 여러 값을 받을 수 있음
	   // => new LinkedMultiValueMap() 으로 생성, add("key","value")
	   
	   // ** Map (키중복 허용안됨) 과 비교 
	   // => HashMap : 순서보장 안됨 
	   // => TreeMap : key값 순서로 자동정렬
	   // => LinkedHashMap : 입력순서보장
	
	
	public String searchQuery(int currPage) {
		
	    // ** check 처리 
	    // => 배열 -> MultiValueMap 으로 -> UriComponents 의 queryParams에 적용
	    // => MultiValueMap 생성
		MultiValueMap<String, String> checkMap = new LinkedMultiValueMap<String, String>();
	      
	    // => check에 선택값이 있는경우에만
	    //    배열 check의 원소들을 checkMap 으로
		if(cri.getCheck()!=null && cri.getCheck().length > 0) {
			for(String c:cri.getCheck()) {
				checkMap.add("check", c);
			}// for
		}else {
			checkMap = null;
		}
			
		
		
		
		UriComponents uriComponents = 
				UriComponentsBuilder.newInstance()
				.queryParam("currPage", currPage)
				.queryParam("rowsPerPage", cri.getRowsPerPage())
				.queryParam("searchType", cri.getSearchType())
				.queryParam("keyword", cri.getKeyword())
				.queryParams(checkMap)
				.build();
				
		
		return uriComponents.toString();	
	} // searchQuery
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
