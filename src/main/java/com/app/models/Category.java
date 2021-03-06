package com.app.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="categories")
@Data
public class Category implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="category_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long categoryId;
	
	@Column(name="category_name")
	private String categoryName;
	
}
