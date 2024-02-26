package mapperInterface;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import com.example.demo.domain.BoardDTO;

import pageTest.Criteria;
import pageTest.SearchCriteria;

public interface BoardMapper {
	
	// ** selectList
	public List<BoardDTO> selectList();
	
	// ** selectOne
	public BoardDTO selectOne(int seq);
	
	// ** insert
	public int insert(BoardDTO dto);
	
	// ** replyInsert
	public int rinsert(BoardDTO dto);
	
	// ** stepUpdate
	public int stepUpdate(BoardDTO dto);
	
	// ** update
	public int update(BoardDTO dto);
	
	// ** delete
	public int delete(BoardDTO dto);
	
	// ** selectList
	public List<BoardDTO> bPageList(Criteria cri);
	
	// ** totalRowsCount
	public int totalRowsCount(Criteria cri);
	
	// ** bSearchList
	public List<BoardDTO> bSearchList(SearchCriteria cri);	
	
	// ** bSearchRowsCount
	public int bSearchRowsCount(SearchCriteria cri);
	
	// ** bCheckList
	public List<BoardDTO> bCheckList(SearchCriteria cri);
	
	// ** bCheckRowsCount
	public int bCheckRowsCount(SearchCriteria cri);
	
	@Select("select * from board where id = #{id}")
	public List<BoardDTO> idblist(String id);
	

	
}
