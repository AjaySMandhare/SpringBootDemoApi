package com.springboot.hibernate;
// Generated 21 May, 2020 12:44:37 PM by Hibernate Tools 5.2.12.Final

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Country generated by hbm2java
 */
@Entity
@Table(name = "country", catalog = "springbootdemo")
public class Country implements java.io.Serializable {

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer cid;
	
	public Integer getCid() {
		return cid;
	}


	public void setCid(Integer cid) {
		this.cid = cid;
	}


	public String getCname() {
		return cname;
	}


	public void setCname(String cname) {
		this.cname = cname;
	}


	private String cname;
	

	@Override
	public String toString() {
		return "Country [cid=" + cid + ", cname=" + cname + "]";
	}


	public Country() {
	}

	

}
