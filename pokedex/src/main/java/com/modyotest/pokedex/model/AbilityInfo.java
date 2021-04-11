package com.modyotest.pokedex.model;

public class AbilityInfo {
	private int id;
	private String name;
	
	public AbilityInfo(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "AbilityInfo [id=" + id + ", name=" + name + "]";
	}
	
}
