package com.se4f7.SWP.entities;

public class CouponEntity extends BaseEntity {

	private String name;

	private String description;

	private int type;

	private int Amount;

	private String due;
	private int status;

	public CouponEntity(int id, String name, String description, int type, String createdBy, String updatedBy,
						String createdDate, String updatedDate, int Amount, String due, int status) {
		super(id, createdBy, updatedBy, createdDate, updatedDate);
		this.name = name;
		this.description = description;
		this.type = type;
		this.Amount = Amount;
		this.due = due;
		this.status = status;
	}

	public String getDue() {
		return due;
	}

	public void setDue(String due) {
		this.due = due;
	}

	public CouponEntity() {
		super();
	}

	public int getAmount() {
		return Amount;
	}

	public void setAmount(int amount) {
		Amount = amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	public  void setType(int type){
		this.type = type;
	}
	public int getType(){
		return type;
	}

	@Override
	public String toString() {
		return "CouponEntity{" +
				"name='" + name + '\'' +
				", description='" + description + '\'' +
				", status=" + type +
				", Amount=" + Amount +
				", due='" + due + '\'' +
				'}';
	}
}
