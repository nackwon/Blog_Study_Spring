package com.javaex.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.javaex.VO.MemberVO;
import com.javaex.connection.ConnectionManager;

@Repository
public class MemberDAO {

	public void MemberInsert(MemberVO vo) {

		ConnectionManager mgr = new ConnectionManager();
		Connection con = mgr.getConnection();
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			String sql = "INSERT INTO member_tbl VALUES (user_no.nextval,?,?,?)";

			// 4. 바인딩
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getEmail());

			// 5. 실행
			count = pstmt.executeUpdate();

			if (count > 0) {
				System.out.print("삽입 완료");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			mgr.ConnectionClose(con, pstmt, null);
		}
	}

	public ArrayList<MemberVO> searchAll() {

		ConnectionManager mgr = new ConnectionManager();
		Connection con = mgr.getConnection();
		PreparedStatement pstmt = null;
		ArrayList<MemberVO> list = null;
		MemberVO vo = null;
		ResultSet rs = null;

		try {

			String sql = "SELECT * FROM member_tbl ORDER BY no DESC";

			// 4. 바인딩
			pstmt = con.prepareStatement(sql);
			// 5. 실행
			rs = pstmt.executeQuery();

			list = new ArrayList<MemberVO>();

			while (rs.next()) {
				vo = new MemberVO();
				vo.setNo(rs.getInt(1));
				vo.setId(rs.getString(2));
				vo.setPw(rs.getString(3));
				vo.setEmail(rs.getString(4));
				list.add(vo);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			mgr.ConnectionClose(con, pstmt, rs);
		}
		return list;
	}
}