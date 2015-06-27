package com.nhuocquy.model;

import java.util.Date;
import java.util.List;

public class Student {
	private String SBD;
	private String name;
	private Date birthday;
	private String address;
	private String schoolName;
	private String schoolCode;
	private String schoolRegisterCode;
	private String schoolRegisterName;
	private boolean sex;
	private List<Subject> subjects;
	public Student() {
	}
	public String getSBD() {
		return SBD;
	}
	public void setSBD(String sBD) {
		SBD = sBD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getSchoolCode() {
		return schoolCode;
	}
	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}
	public String getSchoolRegisterCode() {
		return schoolRegisterCode;
	}
	public void setSchoolRegisterCode(String schoolRegisterCode) {
		this.schoolRegisterCode = schoolRegisterCode;
	}
	public String getSchoolRegisterName() {
		return schoolRegisterName;
	}
	public void setSchoolRegisterName(String schoolRegisterName) {
		this.schoolRegisterName = schoolRegisterName;
	}
	public boolean isSex() {
		return sex;
	}
	public void setSex(boolean sex) {
		this.sex = sex;
	}
	public List<Subject> getSubjects() {
		return subjects;
	}
	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}
	public double totalScrore(){
		double s = 0;
		for (Subject su : subjects) {
			s += su.getScore();
		}
		return s;
	}
	@Override
	public String toString() {
		return "Student [SBD=" + SBD + ", name=" + name + ", birthday="
				+ birthday + ", subjects=" + subjects + "]";
	}
	
}
