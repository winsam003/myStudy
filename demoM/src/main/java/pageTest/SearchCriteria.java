package pageTest;

import lombok.Data;

@Data
public class SearchCriteria extends Criteria{
	private String searchType = "all";			// defualt 값 all로 지정
	private String keyword;						// keyword는 파라미터로 받아야하니까 default 없음
	private String[] check;						// 나중에 쓸거임 체크박스를 만들었을때를 위해 미리 만들겠음
}
