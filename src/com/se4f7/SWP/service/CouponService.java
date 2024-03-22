package com.se4f7.SWP.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.se4f7.SWP.entities.CouponEntity;

public interface CouponService {

	public boolean create(String name, String description, int status, String createdBy, String updatedBy,
			String createdDate, String updatedDate, int Amount, String due);

	public boolean update(int id, String name, String description, int type, String createdBy, String updatedBy,
			String createdDate, String updatedDate, int Amount, String due, int status);

	public boolean delete(String id);

	public CouponEntity getWorkById(String id);

	public int count();

	public int countByUser(String userName);

	public String getDueById(int id);

	public String getCreatedById(int id);

	public List<CouponEntity> getWorkByDue(String due);

	public List<CouponEntity> getWorkByDue(String due, String username);

	public List<CouponEntity> getAllBill();

	public List<CouponEntity> getAllTodoLimit(int page);

	public List<CouponEntity> getAllWorkUser(int page, String userName);

	public List<CouponEntity> getFilter(int status);

	public List<CouponEntity> getFilterUser(int status, String username);

	public List<CouponEntity> getPriorityUser(int Amount, String username);

	public List<CouponEntity> getWorkByName(String name);

	public List<CouponEntity> getWorkByNameU(String name, String createdBy);

	public List<CouponEntity> getWorkByNameToExcel(String createdBy);

	public boolean importFromExcel(HttpServletRequest request, HttpServletResponse response, String filePath);

	public int getTotalPayment();
	public int getTotalReceipt();

	int getTotalDeleteBill();

	List<CouponEntity> getAllBillDelete();

	List<CouponEntity> getAllBillDeleteLimit(int page);
}
