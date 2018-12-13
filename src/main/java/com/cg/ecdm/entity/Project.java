package com.cg.ecdm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
public class Project {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="project_code")
	private int projectCode;
	@Pattern(regexp="[A-Z]{1}[A-Za-z+\\s]{2,}",message="Project code should start with capital letter")
	@Column(name="project_description")
	private String projectDescription;
	@Column(name="start_date")
	@NotNull(message="Date pattern should be dd/MM/yyyy format")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date startDate;
	@Column(name="end_date")
	@NotNull(message="Date pattern should be dd/MM/yyyy format")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date endDate;

	public int getprojectCode() {
		return projectCode;
	}

	public void setprojectCode(int projectCode) {
		this.projectCode = projectCode;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Project(int projectCode, String projectDescription,
			Date startDate, Date endDate) {
		super();
		this.projectCode = projectCode;
		this.projectDescription = projectDescription;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Project() {
	}

}
