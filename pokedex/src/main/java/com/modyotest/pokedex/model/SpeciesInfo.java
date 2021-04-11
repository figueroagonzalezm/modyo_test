package com.modyotest.pokedex.model;

public class SpeciesInfo {
	private int id;
	private String name;
	
	public SpeciesInfo(int id, String name) {
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
		return "Species [id=" + id + ", name=" + name + "]";
	}

}
