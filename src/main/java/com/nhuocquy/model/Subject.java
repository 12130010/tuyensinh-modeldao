package com.nhuocquy.model;

public class Subject {
	private String subject;
	private double score;
	public Subject() {
		// TODO Auto-generated constructor stub
	}
	public Subject(String subject, double score) {
		super();
		this.subject = subject;
		this.score = score;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "Subject [subject=" + subject + ", score=" + score + "]";
	}
	
}
