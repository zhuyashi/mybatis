package com.mybatis.entity;

public class Role {
	private int id;
	private String roleName;
	private String note;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	@Override
	public String toString() {
		return "Role [id=" + id + ", roleName=" + roleName + ", note=" + note
				+ "]";
	}
	
	
}
