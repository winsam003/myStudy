package com.ncs.spring02.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ncs.spring02.domain.MemberDTO;


//import JDBC01.DBConnection;

@Repository
public class MemberDAO {
	// ** JDBC API 에 정의된 필요한 객체들을 전역변수 정의
	private static Connection cn = DBConnection.getConnection();
	private static Statement st;
	private static PreparedStatement pst;
	private static ResultSet rs;
	private static String sql;

	// ** selectList
	public List<MemberDTO> selectList() {
		sql = "select * from member";
		List<MemberDTO> list = new ArrayList<MemberDTO>();

		try {
			pst = cn.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				do {
					MemberDTO dto = new MemberDTO();
					dto.setId(rs.getString(1));
					dto.setPassword(rs.getString(2));
					dto.setName(rs.getString(3));
					dto.setAge(rs.getInt(4));
					dto.setJno(rs.getInt(5));
					dto.setInfo(rs.getString(6));
					dto.setPoint(rs.getDouble(7));
					dto.setBirthday(rs.getString(8));
					dto.setRid(rs.getString(9));
					list.add(dto);
				} while (rs.next());
				return list;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("** SelectList Exception => " + e.toString());
			return null;
		}

	} // selectList

	// ** selectOne
	public MemberDTO selectOne(String id) {
		sql = "select * from member where id = ?";

		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, id);
			rs = pst.executeQuery();

			if (rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setId(rs.getString(1));
				dto.setPassword(rs.getString(2));
				dto.setName(rs.getString(3));
				dto.setAge(rs.getInt(4));
				dto.setJno(rs.getInt(5));
				dto.setInfo(rs.getString(6));
				dto.setPoint(rs.getDouble(7));
				dto.setBirthday(rs.getString(8));
				dto.setRid(rs.getString(9));
				return dto;
			}

		} catch (Exception e) {
			System.out.println("** selectOne Exception => " + e.toString());
			return null;
		}
		return null;
	} // selectOne

	// ** insert
	// => member 테이블엔 auto_increment 나 default가 없음 그래서 모든 컬럼을 입력 함
	public int insert(MemberDTO dto) {
		sql = "insert into member Value(?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, dto.getId());
			pst.setString(2, dto.getPassword());
			pst.setString(3, dto.getName());
			pst.setInt(4, dto.getAge());
			pst.setInt(5, dto.getJno());
			pst.setString(6, dto.getInfo());
			pst.setDouble(7, dto.getPoint());
			pst.setString(8, dto.getBirthday());
			pst.setString(9, dto.getRid());

			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** Insert Exception => " + e.toString());
			return 0;
		}
	} // insert

	// ** update
	// => id는 primary key이기 때문에 수정하지 않음 그래서 id를 제외한 모든 컬럼 수정
	public int update(MemberDTO dto) {
		sql = "update member set password=?, name=?, age=?, jno=?, info=?, point=?, birthday=?, rid=? where id = ?";

		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, dto.getPassword());
			pst.setString(2, dto.getName());
			pst.setInt(3, dto.getAge());
			pst.setInt(4, dto.getJno());
			pst.setString(5, dto.getInfo());
			pst.setDouble(6, dto.getPoint());
			pst.setString(7, dto.getBirthday());
			pst.setString(8, dto.getRid());
			pst.setString(9, dto.getId());

			return pst.executeUpdate();

		} catch (Exception e) {
			System.out.println("** Update Exception => " + e.toString());
			return 0;
		}

	} // update

	// ** delete // 삭제같은경우 확장을 해도 결국 지우는거니까 sno만 해도 됨
	public int delete(String id) {
		sql = "delete from member where id = ?";
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, id);

			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** Delete Exception => " + e.toString());
			return 0;
		}
	} // delete
	
	
	public List<MemberDTO> selectJoList(int jno) {
		sql = "select * from member where jno = ?";
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		
		try {
			pst = cn.prepareStatement(sql);
			
			pst.setInt(1, jno);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				do {
					MemberDTO dto = new MemberDTO();
					dto.setId(rs.getString(1));
					dto.setPassword(rs.getString(2));
					dto.setName(rs.getString(3));
					dto.setAge(rs.getInt(4));
					dto.setJno(rs.getInt(5));
					dto.setInfo(rs.getString(6));
					dto.setPoint(rs.getDouble(7));
					dto.setBirthday(rs.getString(8));
					dto.setRid(rs.getString(9));
					
					list.add(dto);
				}while(rs.next());
				return list;
			}else {
				return null;
			}

			
		}catch (Exception e) {
			System.out.println("** selectJoList Exception => " + e.toString());
			return null;
		}
	}
	
} // class
