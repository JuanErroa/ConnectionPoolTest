package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.User;

public class UserDao {
	private Connection con;
	private String sql;
	private PreparedStatement stmnt;
	private ResultSet rs;

	public UserDao() 
	{
		DbConnection db = new DbConnection();
		this.con = db.getCon();
	}

	public List<User> getList() {
		List<User> users = new ArrayList<>();
		sql = "SELECT id, username, pass FROM users";
		try {
			stmnt = con.prepareStatement(sql);
			rs = stmnt.executeQuery();
			while (rs.next()) {
				User user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("pass"));
				users.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmnt.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return users;
	}

	public void addNewUser(User user) {
		sql = "INSERT INTO users (username, pass) VALUES(?,?)";
		try {
			stmnt = con.prepareStatement(sql);
			stmnt.setString(1, user.getUsername());
			stmnt.setString(2, user.getPass());
			stmnt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmnt.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean checkIfUserExist(int userId) {
		boolean exist = false;
		try {
			stmnt = con.prepareStatement("SELECT * FROM users WHERE id = ?");
			stmnt.setInt(1, userId);
			rs = stmnt.executeQuery();
			exist = !rs.next() ? exist = false : true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmnt.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return exist;
	}

	public void updateUser(User user) {
		if (checkIfUserExist(user.getId())) {
			sql = "UPDATE users SET username = ?, pass = ? WHERE id = ?";
			try {
				stmnt = con.prepareStatement(sql);
				stmnt.setString(1, user.getUsername());
				stmnt.setString(2, user.getPass());
				stmnt.setInt(3, user.getId());
				stmnt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					stmnt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else
			System.out.println("user not exist");
	}

	public void deleteUser(int id) {
		if(checkIfUserExist(id)){
			try {
				stmnt = con.prepareStatement("DELETE FROM users WHERE id = ?");
				stmnt.setInt(1, id);
				stmnt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
			System.out.println("user not exist");
	}
}
