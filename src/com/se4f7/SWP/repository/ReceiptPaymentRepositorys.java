package com.se4f7.SWP.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.se4f7.SWP.entities.CouponEntity;
import com.se4f7.SWP.entities.Graph;
import com.se4f7.SWP.utils.DBUtil;

public class ReceiptPaymentRepositorys {

	private static final String SELECT_ALL = "select * from work WHERE isDelete ='0'";
	private static final String SELECT_ALL_DELETE = "select * from work WHERE isDelete !='0'";
	private static final String SELECT_ALL_LIMIT = "SELECT * FROM work WHERE IsDelete = '0' LIMIT 5 OFFSET ?;\n";
	private static final String SELECT_ALL_DELETE_LIMIT = "SELECT * FROM work WHERE IsDelete != '0' LIMIT 5 OFFSET ?;\n";
	private static final String COUNT_U = "SELECT COUNT(*) from work where created_by = ? and isDelete ='0'";

	private static final String COUNT = "select count(*) from work where isDelete ='0'";

	private static final String INSERT_WORK_SQL = "insert into work (name, description, type, created_by, updated_by, created_date, updated_date, Amount, due) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String UPDATE_WORK_SQL = "update work set name = ?, description = ?, type = ?, created_by = ?, updated_by = ?, created_date = ?, updated_date = ?, Amount = ?, due = ?, status = ?"
			+ " where id = ?";

	private static final String DELETE_WORK_SQL = "UPDATE work SET IsDelete = '1' WHERE id = ?";

	private static final String SELECT_WORK_ID = "select * from work where id = ? and IsDelete = '0'";

	private static final String SELECT_STATUS = "select * from work where type = ? and IsDelete = '0'";

	private static final String SELECT_USER_WORK = "SELECT * FROM work\n"
			+ "WHERE created_by = ? and IsDelete = '0' ORDER BY Amount = 1 DESC, Amount DESC LIMIT 5 OFFSET ?";

	private static final String SELECT_STATUS_USER = "select * from work where status = ? and created_by = ? and isDelete ='0'";

	private static final String SELECT_PRIORITY_USER = "select * from work where Amount = ? and created_by = ? and isDelete ='0'";

	private static final String SELECT_NAME_TODO = "select * from work where name like ? and isDelete ='0'";

	private static final String SELECT_NAME_TODO_U = "select * from work where name like ? and created_by = ? and isDelete ='0'";

	private static final String EXCEL = "select * from work where isDelete ='0'";

	private static final String SELECT_DUE = "select due from work where id = ? and isDelete ='0'";

	private static final String SELECT_CREATED = "select created_by from work where id = ? and isDelete ='0'";

	private static final String SELECT_FILTER_DUE = "select * from work WHERE due <= CURDATE() and isDelete ='0'";

	private static final String SELECT_NOT_DUE = "select * from work WHERE due > CURDATE() and isDelete ='0'";

	private static final String SELECT_DUE_USER = "select * from work WHERE due <= CURDATE() and created_by = ? and isDelete ='0'";

	private static final String SELECT_NOT_DUE_USER = "select * from work WHERE due > CURDATE() and created_by = ? and isDelete ='0'";
	private static final String SELECT_MONEY_DAY = "SELECT DATE(created_date) AS created_date,\n" +
			"       SUM(CASE WHEN status = 1 THEN Amount ELSE 0 END) AS amount_status_1,\n" +
			"       SUM(CASE WHEN status = 0 THEN Amount ELSE 0 END) AS amount_status_2\n" +
			"FROM work\n" +
			"WHERE isDelete ='0'\n"+
			"GROUP BY DATE(created_date)\n" +
			"ORDER BY created_date;\n";
	private static final String SELECT_RECEIPT = "SELECT COUNT(*) AS total_receipt from work where type = 0 and isDelete ='0'";
	private static final String SELECT_PAYMENT = "SELECT COUNT(*) AS total_payment from work where type = 1 and isDelete ='0'";
	private static final String SELECT_ISDELETE = "SELECT COUNT(*) AS total_deleteBill from work where isDelete ='1'";

	public List<CouponEntity> getWorkByDue(String due, String username) throws SQLException {
		List<CouponEntity> list = new ArrayList<>();

		try (Connection conn = DBUtil.getConnection()) {
			String sql;
			if ("due".equalsIgnoreCase(due)) {
				sql = SELECT_DUE_USER;
			} else if ("not-due".equalsIgnoreCase(due)) {
				sql = SELECT_NOT_DUE_USER;
			} else {
				return list;
			}

			try (PreparedStatement pstm = conn.prepareStatement(sql)) {
				pstm.setString(1, username);

				try (ResultSet rs = pstm.executeQuery()) {
					while (rs.next()) {
						CouponEntity toDo = new CouponEntity(rs.getInt("id"), rs.getString("name"),
								rs.getString("description"), rs.getInt("type"), rs.getString("created_by"),
								rs.getString("updated_by"), rs.getString("created_date"), rs.getString("updated_date"),
								rs.getInt("Amount"), rs.getString("due"), rs.getInt("status"));
						list.add(toDo);
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}

		return list;
	}

	public List<CouponEntity> getWorkByDue(String due) throws SQLException {
		List<CouponEntity> list = new ArrayList<>();

		try (Connection conn = DBUtil.getConnection()) {
			String sql;
			if ("due".equalsIgnoreCase(due)) {
				sql = SELECT_FILTER_DUE;
			} else if ("not-due".equalsIgnoreCase(due)) {
				sql = SELECT_NOT_DUE;
			} else {
				return list;
			}
			try (PreparedStatement pstm = conn.prepareStatement(sql)) {
				ResultSet rs = pstm.executeQuery();
				while (rs.next()) {
					CouponEntity toDo = new CouponEntity(rs.getInt("id"), rs.getString("name"), rs.getString("description"),
							rs.getInt("type"), rs.getString("created_by"), rs.getString("updated_by"),
							rs.getString("created_date"), rs.getString("updated_date"), rs.getInt("Amount"),
							rs.getString("due"), rs.getInt("status"));
					list.add(toDo);
				}
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}

		return list;
	}

	public String getCreatedById(int id) throws SQLException {
		try (Connection conn = DBUtil.getConnection(); PreparedStatement pstm = conn.prepareStatement(SELECT_CREATED)) {
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				String toDo = rs.getString(1);
				return toDo;
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return null;
	}

	public String getDueById(int id) throws SQLException {
		try (Connection conn = DBUtil.getConnection(); PreparedStatement pstm = conn.prepareStatement(SELECT_DUE)) {
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				String toDo = rs.getString(1);
				return toDo;
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return null;
	}

	public List<CouponEntity> getWorkByName(String name) throws SQLException {
		List<CouponEntity> list = new ArrayList<>();
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstm = conn.prepareStatement(SELECT_NAME_TODO)) {
			pstm.setString(1, "%" + name + "%");
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				CouponEntity toDo = new CouponEntity();
				toDo.setId(rs.getInt(1));
				toDo.setName(rs.getString(2));
				toDo.setDescription(rs.getString(3));
				toDo.setStatus(rs.getInt(4));
				toDo.setCreatedBy(rs.getString(5));
				toDo.setUpdatedBy(rs.getString(6));
				toDo.setCreatedDate(rs.getString(7));
				toDo.setUpdatedDate(rs.getString(8));
				toDo.setAmount(rs.getInt(9));
				toDo.setDue(rs.getString(10));
				list.add(toDo);
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

	public List<CouponEntity> getWorkByNameU(String name, String createdBy) throws SQLException {
		List<CouponEntity> list = new ArrayList<>();
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstm = conn.prepareStatement(SELECT_NAME_TODO_U)) {
			pstm.setString(1, "%" + name + "%");
			pstm.setString(2, createdBy);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				CouponEntity toDo = new CouponEntity();
				toDo.setId(rs.getInt(1));
				toDo.setName(rs.getString(2));
				toDo.setDescription(rs.getString(3));
				toDo.setStatus(rs.getInt(4));
				toDo.setCreatedBy(rs.getString(5));
				toDo.setUpdatedBy(rs.getString(6));
				toDo.setCreatedDate(rs.getString(7));
				toDo.setUpdatedDate(rs.getString(8));
				toDo.setAmount(rs.getInt(9));
				toDo.setDue(rs.getString(10));
				list.add(toDo);
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

	public List<CouponEntity> getWorkByNameToExcel() throws SQLException {
		List<CouponEntity> list = new ArrayList<>();
		try (Connection conn = DBUtil.getConnection(); PreparedStatement pstm = conn.prepareStatement(EXCEL)) {
//			pstm.setString(1, createdBy);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				CouponEntity toDo = new CouponEntity(rs.getInt("id"), rs.getString("name"), rs.getString("description"),
						rs.getInt("type"), rs.getString("created_by"), rs.getString("updated_by"),
						rs.getString("created_date"), rs.getString("updated_date"), rs.getInt("Amount"),
						rs.getString("due"), rs.getInt("status"));
				list.add(toDo);
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

	public List<CouponEntity> getAllWorkUser(int page, String userName) throws SQLException {
		List<CouponEntity> list = new ArrayList<>();
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstm = conn.prepareStatement(SELECT_USER_WORK)) {
			pstm.setString(1, userName);
			pstm.setInt(2, ((page - 1) * 5));
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				CouponEntity toDo = new CouponEntity(rs.getInt("id"), rs.getString("name"), rs.getString("description"),
						rs.getInt("type"), rs.getString("created_by"), rs.getString("updated_by"),
						rs.getString("created_date"), rs.getString("updated_date"), rs.getInt("Amount"),
						rs.getString("due"),  rs.getInt("status"));
				list.add(toDo);
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

	public List<CouponEntity> getAllWork() throws SQLException {
		List<CouponEntity> list = new ArrayList<>();
		try (Connection conn = DBUtil.getConnection(); PreparedStatement pstm = conn.prepareStatement(SELECT_ALL)) {
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				CouponEntity toDo = new CouponEntity(rs.getInt("id"), rs.getString("name"), rs.getString("description"),
						rs.getInt("type"), rs.getString("created_by"), rs.getString("updated_by"),
						rs.getString("created_date"), rs.getString("updated_date"), rs.getInt("Amount"),
						rs.getString("due"),  rs.getInt("status"));
				list.add(toDo);
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

	public List<CouponEntity> getAllWorkLimit(int page) throws SQLException {
		List<CouponEntity> list = new ArrayList<>();
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstm = conn.prepareStatement(SELECT_ALL_LIMIT)) {
			pstm.setInt(1, ((page - 1) * 5));
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				CouponEntity toDo = new CouponEntity(rs.getInt("id"), rs.getString("name"), rs.getString("description"),
						rs.getInt("type"), rs.getString("created_by"), rs.getString("updated_by"),
						rs.getString("created_date"), rs.getString("updated_date"), rs.getInt("Amount"),
						rs.getString("due"),  rs.getInt("status"));
				list.add(toDo);
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

	public List<CouponEntity> getFilter(int status) throws SQLException {
		List<CouponEntity> list = new ArrayList<>();
		try (Connection conn = DBUtil.getConnection(); PreparedStatement pstm = conn.prepareStatement(SELECT_STATUS)) {
			pstm.setInt(1, status);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				CouponEntity toDo = new CouponEntity(rs.getInt("id"), rs.getString("name"), rs.getString("description"),
						rs.getInt("type"), rs.getString("created_by"), rs.getString("updated_by"),
						rs.getString("created_date"), rs.getString("updated_date"), rs.getInt("Amount"),
						rs.getString("due"),  rs.getInt("status"));
				list.add(toDo);
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

	public List<CouponEntity> getFilterUser(int status, String username) throws SQLException {
		List<CouponEntity> list = new ArrayList<>();
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstm = conn.prepareStatement(SELECT_STATUS_USER)) {
			pstm.setInt(1, status);
			pstm.setString(2, username);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				CouponEntity toDo = new CouponEntity(rs.getInt("id"), rs.getString("name"), rs.getString("description"),
						rs.getInt("status"), rs.getString("created_by"), rs.getString("updated_by"),
						rs.getString("created_date"), rs.getString("updated_date"), rs.getInt("Amount"),
						rs.getString("due"),  rs.getInt("status"));
				list.add(toDo);
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

	public List<CouponEntity> getPriorityUser(int Amount, String username) throws SQLException {
		List<CouponEntity> list = new ArrayList<>();
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstm = conn.prepareStatement(SELECT_PRIORITY_USER)) {
			pstm.setInt(1, Amount);
			pstm.setString(2, username);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				CouponEntity toDo = new CouponEntity(rs.getInt("id"), rs.getString("name"), rs.getString("description"),
						rs.getInt("status"), rs.getString("created_by"), rs.getString("updated_by"),
						rs.getString("created_date"), rs.getString("updated_date"), rs.getInt("Amount"),
						rs.getString("due"),  rs.getInt("status"));
				list.add(toDo);
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

	public int countToDo() throws SQLException {
		try (Connection conn = DBUtil.getConnection(); PreparedStatement pstm = conn.prepareStatement(COUNT)) {
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("error: " + e.getMessage());
		}
		return 0;
	}

	public int countUserWork(String userName) throws SQLException {
		int count = 0;
		try (Connection conn = DBUtil.getConnection(); PreparedStatement pstm = conn.prepareStatement(COUNT_U)) {
			pstm.setString(1, userName);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
			throw e;
		}
		return count;
	}

	public boolean insertWork(String name, String description, int type, String createdBy, String updatedBy,
			String createdDate, String updatedDate, int Amount, String due) throws SQLException {
		boolean result = false;
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstm = conn.prepareStatement(INSERT_WORK_SQL)) {
			pstm.setString(1, name);
			pstm.setString(2, description);
			pstm.setInt(3, type);
			pstm.setString(4, createdBy);
			pstm.setString(5, updatedBy);
			pstm.setString(6, createdDate);
			pstm.setString(7, updatedDate);
			pstm.setInt(8, Amount);
			pstm.setString(9, due);

			pstm.executeUpdate();
			result = true;
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return result;
	}

	public boolean updateWork(int id, String name, String description, int type, String createdBy, String updatedBy,
			String createdDate, String updatedDate, int Amount, String due, int status) throws SQLException {
		boolean result = false;
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstm = conn.prepareStatement(UPDATE_WORK_SQL)) {
			pstm.setString(1, name);
			pstm.setString(2, description);
			pstm.setInt(3, type);
			pstm.setString(4, createdBy);
			pstm.setString(5, updatedBy);
			pstm.setString(6, createdDate);
			pstm.setString(7, updatedDate);
			pstm.setInt(8, Amount);
			pstm.setString(9, due);
			pstm.setInt(10, status);
			pstm.setInt(11, id);

			pstm.executeUpdate();
			result = true;
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return result;
	}

	public CouponEntity getWorkById(String id) throws SQLException {
		try (Connection conn = DBUtil.getConnection(); PreparedStatement pstm = conn.prepareStatement(SELECT_WORK_ID)) {
			pstm.setString(1, id);

			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				return new CouponEntity(rs.getInt("id"), rs.getString("name"), rs.getString("description"),
						rs.getInt("type"), rs.getString("created_by"), rs.getString("updated_by"),
						rs.getString("created_date"), rs.getString("updated_date"), rs.getInt("Amount"),
						rs.getString("due"),  rs.getInt("status"));
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return null;
	}

	public boolean deleteWork(String id) throws SQLException {
		boolean deleted = false;
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstm = conn.prepareStatement(DELETE_WORK_SQL)) {
			pstm.setString(1, id);
			pstm.executeUpdate();
			deleted = true;
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return deleted;
	}
	public List<Graph> getMoneyy() throws SQLException {
		List<Graph> list = new ArrayList<>();
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstm = conn.prepareStatement(SELECT_MONEY_DAY)) {
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Graph data = new Graph(
						rs.getDate("created_date"),
						rs.getInt("amount_status_1"),
						rs.getInt("amount_status_2")
				);
				list.add(data);
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

	public int getTotalReceipt() throws SQLException {
		int total = 0;
		try (Connection connection = DBUtil.getConnection();
			 PreparedStatement statement = connection.prepareStatement(SELECT_RECEIPT)) {
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					total = resultSet.getInt("total_receipt");
				}
			}
		} catch (SQLException e) {
			DBUtil.printSQLException(e);
		}
		return total;
	}

	public int getTotalPayment() throws SQLException {
		int total = 0;
		try (Connection connection = DBUtil.getConnection();
			 PreparedStatement statement = connection.prepareStatement(SELECT_PAYMENT)) {
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					total = resultSet.getInt("total_payment");
				}
			}
		} catch (SQLException e) {
			DBUtil.printSQLException(e);
		}
		return total;
	}

	public int getTotalDeleteBill() throws SQLException {
		int total = 0;
		try (Connection connection = DBUtil.getConnection();
			 PreparedStatement statement = connection.prepareStatement(SELECT_ISDELETE)) {
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					total = resultSet.getInt("total_deleteBill");
				}
			}
		} catch (SQLException e) {
			DBUtil.printSQLException(e);
		}
		return total;
	}


	public List<CouponEntity> getAllBillDelete() throws SQLException {
		List<CouponEntity> list = new ArrayList<>();
		try (Connection conn = DBUtil.getConnection(); PreparedStatement pstm = conn.prepareStatement(SELECT_ALL_DELETE)) {
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				CouponEntity toDo = new CouponEntity(rs.getInt("id"), rs.getString("name"), rs.getString("description"),
						rs.getInt("type"), rs.getString("created_by"), rs.getString("updated_by"),
						rs.getString("created_date"), rs.getString("updated_date"), rs.getInt("Amount"),
						rs.getString("due"), rs.getInt("status"));
				list.add(toDo);
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}
	public List<CouponEntity> getAllBillDeleteLimit(int page) throws SQLException {
		List<CouponEntity> list = new ArrayList<>();
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstm = conn.prepareStatement(SELECT_ALL_DELETE_LIMIT)) {
			pstm.setInt(1, ((page - 1) * 5));
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				CouponEntity toDo = new CouponEntity(rs.getInt("id"), rs.getString("name"), rs.getString("description"),
						rs.getInt("type"), rs.getString("created_by"), rs.getString("updated_by"),
						rs.getString("created_date"), rs.getString("updated_date"), rs.getInt("Amount"),
						rs.getString("due"),  rs.getInt("status"));
				list.add(toDo);
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}
}
