package com.se4f7.SWP.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.se4f7.SWP.entities.CouponEntity;
import com.se4f7.SWP.repository.ReceiptPaymentRepositorys;
import com.se4f7.SWP.service.CouponService;
import com.se4f7.SWP.utils.ReadFromExcelFile;

public class CouponServiceImpl implements CouponService {

	private ReceiptPaymentRepositorys Bill = new ReceiptPaymentRepositorys();

	public boolean create(String name, String description, int status, String createdBy, String updatedBy,
			String createdDate, String updatedDate, int Amount, String due) {
		boolean result = false;
		try {
			this.Bill.insertWork(name, description, status, createdBy, updatedBy, createdDate, updatedDate,Amount,
					due);
			result = true;
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return result;
	}

	public boolean update(int id, String name, String description, int type, String createdBy, String updatedBy,
			String createdDate, String updatedDate, int Amount, String due, int status) {
		boolean result = false;
		try {
			this.Bill.updateWork(id, name, description, type, createdBy, updatedBy, createdDate, updatedDate,
					Amount, due, status);
			result = true;
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return result;
	}

	public boolean delete(String id) {
		boolean result = false;
		try {
			this.Bill.deleteWork(id);
			result = true;
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return result;
	}

	public CouponEntity getWorkById(String id) {
		CouponEntity Bill = new CouponEntity();
		try {
			Bill = this.Bill.getWorkById(id);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return Bill;
	}

	public int count() {
		int result = 0;
		try {
			result = this.Bill.countToDo();
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return result;
	}

	public int countByUser(String userName) {
		int result = 0;
		try {
			result = this.Bill.countUserWork(userName);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return result;
	}

	public String getDueById(int id) {
		String BillE = null;
		try {
			BillE = Bill.getDueById(id);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return BillE;
	}

	public String getCreatedById(int id) {
		String BillE = null;
		try {
			BillE = Bill.getCreatedById(id);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return BillE;
	}

	public List<CouponEntity> getWorkByDue(String due) {
		List<CouponEntity> list = new ArrayList<>();
		try {
			list = Bill.getWorkByDue(due);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

	public List<CouponEntity> getWorkByDue(String due, String username) {
		List<CouponEntity> list = new ArrayList<>();
		try {
			list = Bill.getWorkByDue(due, username);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

	public List<CouponEntity> getAllBill() {
		List<CouponEntity> list = new ArrayList<>();
		try {
			list = this.Bill.getAllWork();
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

	public List<CouponEntity> getAllTodoLimit(int page) {
		List<CouponEntity> list = new ArrayList<>();
		try {
			list = this.Bill.getAllWorkLimit(page);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

	public List<CouponEntity> getAllWorkUser(int page, String userName) {
		List<CouponEntity> list = new ArrayList<>();
		try {
			list = this.Bill.getAllWorkUser(page, userName);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

	public List<CouponEntity> getFilter(int status) {
		List<CouponEntity> list = new ArrayList<>();
		try {
			list = this.Bill.getFilter(status);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

	public List<CouponEntity> getFilterUser(int status, String username) {
		List<CouponEntity> list = new ArrayList<>();
		try {
			list = this.Bill.getFilterUser(status, username);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

	public List<CouponEntity> getPriorityUser(int Amount, String username) {
		List<CouponEntity> list = new ArrayList<>();
		try {
			list = this.Bill.getPriorityUser(Amount, username);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

	public List<CouponEntity> getWorkByName(String name) {
		List<CouponEntity> list = new ArrayList<>();
		try {
			list = Bill.getWorkByName(name);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

	public List<CouponEntity> getWorkByNameU(String name, String createdBy) {
		List<CouponEntity> list = new ArrayList<>();
		try {
			list = Bill.getWorkByNameU(name, createdBy);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

	public List<CouponEntity> getWorkByNameToExcel(String createdBy) {
		List<CouponEntity> list = new ArrayList<>();
		try {
			list = this.Bill.getWorkByNameToExcel();
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

	public boolean importFromExcel(HttpServletRequest request, HttpServletResponse response, String filePath) {
		try {
			ReadFromExcelFile.importExcel(request, response, filePath);
			return true;
		} catch (Exception e) {
			System.out.println("Error importing from Excel: " + e.getMessage());
			return false;
		}
	}

	@Override
	public int getTotalPayment() {
		return 0;
	}

	@Override
	public int getTotalReceipt() {
		return 0;
	}
	public int getTotalDeleteBill(){
		int total = 0;
		try {
			total = Bill.getTotalPayment();
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return total;
	}

	public List<CouponEntity> getAllBillDelete() {
		List<CouponEntity> list = new ArrayList<>();
		try {
			list = this.Bill.getAllBillDelete();
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}
	public List<CouponEntity> getAllBillDeleteLimit(int page) {
		List<CouponEntity> list = new ArrayList<>();
		try {
			list = this.Bill.getAllBillDeleteLimit(page);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}
}
