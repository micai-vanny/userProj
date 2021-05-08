package UserService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;
	
	public void close() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (psmt != null) {
			try {
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<UserVO> getUserList() {	// 전체 리스트 받아오기
		conn = DBCon.getConnect();
		List<UserVO> list = new ArrayList<UserVO>();
		
		try {
			psmt = conn.prepareStatement("select * from user_temp");
			rs = psmt.executeQuery();
			while(rs.next()) {
				UserVO vo = new UserVO();
				vo.setUserId(rs.getString("user_id"));
				vo.setUserPass(rs.getString("user_pass"));
				vo.setUserName(rs.getString("user_name"));
				vo.setUserPhone(rs.getString("user_phone"));
				vo.setUserGender(rs.getString("user_gender"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return list;
	}
	
	public UserVO insertUser(UserVO vo) {
		conn = DBCon.getConnect();
		
		String sql = "insert into user_temp values(?,?,?,?,?)";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getUserId());
			psmt.setString(2, vo.getUserPass());
			psmt.setString(3, vo.getUserName());
			psmt.setString(4, vo.getUserPhone());
			psmt.setString(5, vo.getUserGender());
			
			int in = psmt.executeUpdate();
			System.out.println(in + "건 입력 완료.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return vo;
	}
	
	public boolean modifyUser(UserVO vo) {
		conn = DBCon.getConnect();
		String sql = "update user_temp set user_pass = ?, user_phone = ? where user_id = ?";
		int modiCnt = 0;
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getUserPass());
			psmt.setString(2, vo.getUserPhone());
			psmt.setString(3, vo.getUserId());
			
			modiCnt = psmt.executeUpdate();
			System.out.println(modiCnt+"건 수정 완료.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return modiCnt == 0 ? false : true;
	}
}
