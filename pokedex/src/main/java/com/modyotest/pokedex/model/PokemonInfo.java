package com.modyotest.pokedex.model;

import java.util.ArrayList;
import java.util.List;

public class PokemonInfo {
	
	private Integer id;
	private String name;
	private List<TypeInfo> types;
	private Integer weight;
	private List<AbilityInfo> abilities;
	private String description;
	private List<SpeciesInfo> evolutions;
	private String imageUrl;
	
	public PokemonInfo() {
		this.types = new ArrayList<>();
		this.abilities = new ArrayList<>();
		this.evolutions = new ArrayList<>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TypeInfo> getTypes() {
		return types;
	}

	public void setTypes(List<TypeInfo> types) {
		this.types = types;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public List<AbilityInfo> getAbilities() {
		return abilities;
	}

	public void setAbilities(List<AbilityInfo> abilities) {
		this.abilities = abilities;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<SpeciesInfo> getEvolutions() {
		return evolutions;
	}

	public void setEvolutions(List<SpeciesInfo> evolutions) {
		this.evolutions = evolutions;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "PokemonInfo [id=" + id + ", name=" + name + ", types=" + types + ", weight=" + weight + ", abilities="
				+ abilities + ", description=" + description + ", evolutions=" + evolutions + "]";
	}


}
