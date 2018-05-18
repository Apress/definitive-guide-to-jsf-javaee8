package com.example.project.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.example.project.model.validator.PeriodConstraint;

@PeriodConstraint
public class Period implements Serializable {

	private static final long serialVersionUID = 1L;

	private @NotNull LocalDate startDate;
	private @NotNull LocalDate endDate;

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

}
