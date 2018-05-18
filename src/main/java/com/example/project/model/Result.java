package com.example.project.model;

public class Result {

	private Long id;
	private String name;
	private String value;

	public static Result create(long i) {
		Result result = new Result();
		result.id = i;
		result.name = "name" + i;
		result.value = "value" + i;
		return result;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
