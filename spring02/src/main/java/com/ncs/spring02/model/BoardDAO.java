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
		sql="select * from Board order by seq desc";
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
	
	// ** insert
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
			System.out.println("** Board inset => "+e);
			return 0;
		}
	}
	
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
			return pst.executeUpdate();
			
			
		}catch(Exception e) {
			System.out.println("** Board delete => "+e);
			return 0;
		}
		
	}
	
}// class
