package guestbook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import guestbook.vo.GuestbookVo;

public class GuestbookDao {
	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://192.168.0.212:3306/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}

		return conn;
	}

	public void insert(GuestbookVo vo) {
		
		Connection conn=null;
		PreparedStatement pstmt1=null;
		PreparedStatement pstmt2=null;
		PreparedStatement pstmt3=null;
		
		try  {
			
			conn = getConnection();
			
			
			
			pstmt1 = conn.prepareStatement("update guestbook_log set count = count + 1 where date = current_date()");
			pstmt2 = conn.prepareStatement("insert into guestbook_log values(current_date(), 1)");
			
			pstmt3 = conn.prepareStatement("insert into guestbook values(null, ?, ?, ?,now())");
			// 4. binding
			pstmt3.setString(1, vo.getName());
			pstmt3.setString(2, vo.getPassword());
			pstmt3.setString(3, vo.getContents());
			
			// TX:BEGIN
			conn.setAutoCommit(false);

			// DML1
			int rowCount = pstmt1.executeUpdate();
			
			// DML2
			if(rowCount==0) {
				pstmt2.executeUpdate();
			}
			
			// DML3
			pstmt3.executeUpdate();
			
			// TX:END
			conn.commit();

		} catch (SQLException e) {
			System.out.println("error:" + e);
			
			// TX:END FAIL
			try {
				if(conn!=null) {
					conn.rollback();
				}
				
				}
			catch(SQLException ignored) {
				
			}
		} finally {
			try {
				if(pstmt3!=null) {
					pstmt3.close();
				}
				if(pstmt2!=null) {
					pstmt2.close();
				}
				if(pstmt1!=null) {
					pstmt1.close();
				}
			}catch(SQLException ignored){
				
			}
		}

	}

	public void deleteByNoAndPassword(Long no, String password) {
		

		
		

		try (Connection conn = getConnection();

				PreparedStatement pstmt = conn.prepareStatement("delete from guestbook where no=? and password = ?");){
			

			

			

			// 4. binding
			pstmt.setLong(1, no);
			pstmt.setString(2, password);

			// 4. SQL 실행
			pstmt.executeUpdate();

			

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		

		
	}

	public List<GuestbookVo> findAll() {
		List<GuestbookVo> result = new ArrayList<>();

		
		
		ResultSet rs = null;

		try (Connection conn = getConnection();

				PreparedStatement pstmt = conn.prepareStatement("   select no, name, password, contents, DATE_FORMAT(reg_date, '%Y-%m-%d') as reg_date"
						+ "     from guestbook" + " order by no desc");){
			

			

			

			// 5. SQL 실행
			rs = pstmt.executeQuery();

			// 6. 결과 처리
			while (rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String password = rs.getString(3);
				String contents = rs.getString(4);
				String reg_date = rs.getString(5);

				GuestbookVo vo = new GuestbookVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setPassword(password);
				vo.setContents(contents);
				vo.setRegDate(reg_date);

				result.add(vo);
			}
		}  catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		

		return result;
	}
}