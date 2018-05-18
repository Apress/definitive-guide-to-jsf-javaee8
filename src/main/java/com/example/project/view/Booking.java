package com.example.project.view;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.example.project.model.Period;

@Named
@ViewScoped
public class Booking implements Serializable {

	private static final long serialVersionUID = 1L;

	private Period period = new Period();

	public void submit() {
		System.out.println(period.getStartDate());
		System.out.println(period.getEndDate());
	}

	public Period getPeriod() {
		return period;
	}

}
