package com.ncs.spring02.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ncs.spring02.domain.BoardDTO;

// ** 게시판
// => CRUD 를 구현
@Repository
public class BoardDAO {
	
	private static Connection cn = DBConnection.getConnection();
	private static PreparedStatement pst;
	private static ResultSet rs;
	private static String sql;

	

	// ** selectList
	public List<BoardDTO> selectList(){
		// 답글 입력 후 순서 변경 sql
		sql="select * from Board order by root desc, step asc";
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			pst=cn.prepareStatement(sql);
			rs=pst.executeQuery();
			if(rs.next()) {
				do {
					BoardDTO dto = new BoardDTO();
					dto.setSeq(rs.getInt(1));
					dto.setId(rs.getString(2));
					dto.setTitle(rs.getString(3));
					dto.setContent(rs.getString(4));
					dto.setRegdate(rs.getString(5));
					dto.setCnt(rs.getInt(6));
					dto.setRoot(rs.getInt(7));
					dto.setStep(rs.getInt(8));
					dto.setIndent(rs.getInt(9));
					
					list.add(dto);
				}while(rs.next());
				return list;
			}else {
				System.out.println("** Board selectList => 출력자료가 없습니다.");
				return null;
			} // if
			
		}catch(Exception e) {
			System.out.println("** Board selectList => "+e);
			return null;
		} // try
	} // selectList
	
	// ** selectOne
	public BoardDTO selectOne(int seq) {
		sql="select * from board where seq = ?";
		
		try {
			pst=cn.prepareStatement(sql);
			pst.setInt(1, seq);
			rs=pst.executeQuery();
			BoardDTO dto = new BoardDTO();
			if(rs.next()) {
				
				dto.setSeq(rs.getInt(1));
				dto.setId(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRegdate(rs.getString(5));
				dto.setCnt(rs.getInt(6));
				dto.setRoot(rs.getInt(7));
				dto.setStep(rs.getInt(8));
				dto.setIndent(rs.getInt(9));
			}
			return dto;
		}catch(Exception e) {
			System.out.println("** Board selectOne => "+e);
			return null;
		}
	}
	
	   // ** insert : 원글입력
	   // => 입력 컬럼: id, title, content 
	   //    default값: regdate, cnt, step, indent
	   // => root : seq 와 동일한 값   
	   // => Auto_Inc: seq (계산: auto 보다 IFNULL(max(seq)...) 를 적용)
	public int insert(BoardDTO dto) {
		sql = "insert into board values(\r\n"
				+ "(select * from (select ifNull(max(seq),0)+1 from board) as temp), \r\n"
				+ "?, ?, ?, Current_TimeStamp, 0,\r\n"
				+ "(select * from (select ifNull(max(seq),0)+1 from board) as temp), 0, 0)";
		
		try {
			pst=cn.prepareStatement(sql);
			pst.setString(1, dto.getId());
			pst.setString(2, dto.getTitle());
			pst.setString(3, dto.getContent());
			
			return pst.executeUpdate();
		}catch(Exception e) {
			System.out.println("** Board insert => "+e);
			return 0;
		}
	}
	
	// ** replyInsert : 답글 입력
	public int rinsert(BoardDTO dto) {
		sql = "insert into board(seq, id, title, content, root, step, indent) values("
				+ "(select * from (select ifNull(max(seq),0)+1 from board) as temp)"
				+ ",?, ?, ?, ?, ?, ?)";
		
		try {
			pst=cn.prepareStatement(sql);
			pst.setString(1, dto.getId());
			pst.setString(2, dto.getTitle());
			pst.setString(3, dto.getContent());
			pst.setInt(4, dto.getRoot());
			pst.setInt(5, dto.getStep());
			pst.setInt(6, dto.getIndent());
			pst.executeUpdate();	// 답글등록 성공 -> stepUpdate
			System.out.println("** stepUpdate Count = "+stepUpdate(dto));
			
			
			return 1;
		}catch(Exception e) {
			System.out.println("** Reply_insert Exception => "+e);
			return 0;
		}
	} //replyInsert
	
	// ** stepUpdate : step 값 증가
	// => 조건 정의
	//	-> 조건1: 같은 family이어야 함 즉 root는 동일해야 함 
	//	-> 조건2: step >=(크거나 같음)이어야 함
	//	-> 조건3: 새글은 제외해야 함
	// => boardList 의 출력순서 확인
	//		~~ order by root desc, stem asc
	public int stepUpdate(BoardDTO dto) {
		sql = "update board set step = step+1 where root=? "										// 조건 1
				+ "and step>=? "																	// 조건 2
				+ "and seq <> (select * from (select ifNull(max(seq),0) from board) as temp)";		// 조건 3
		
		try {
			pst=cn.prepareStatement(sql);
			pst.setInt(1, dto.getRoot());
			pst.setInt(2, dto.getStep());
			
			return pst.executeUpdate();			// 수정 된 DATA 개수 return
		} catch (Exception e) {
			System.out.println("** stepUpdate Exception => "+e);
			return 0;
		}
	} // stepUpdate
	
	
	
	// ** update
	public int update(BoardDTO dto) {
		sql = "update board set id=?, title=?, content=?, regdate=?, cnt=? where seq=?";
		
		try {
			pst=cn.prepareStatement(sql);
			pst.setString(1, dto.getId());
			pst.setString(2, dto.getTitle());
			pst.setString(3, dto.getContent());
			pst.setString(4, dto.getRegdate());
			pst.setInt(5, dto.getCnt());
			pst.setInt(6, dto.getSeq());
			
			return pst.executeUpdate();
		}catch(Exception e) {
			System.out.println("** Board update => "+e);
			return 0;
		}
		
	}
	
	// ** delete
	public int delete(int seq) {
		sql = "delete from board where seq = ?";
		
		try {
			pst=cn.prepareStatement(sql);
			pst.setInt(1, seq);
			System.out.println("test1");
			return pst.executeUpdate();
		}catch(Exception e) {
			System.out.println("** Board delete => "+e);
			return 0;
		}
		
	}
	
}// class
