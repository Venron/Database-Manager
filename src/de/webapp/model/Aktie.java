package de.webapp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Aktie")

@NamedQueries({})
public class Aktie implements Serializable {

	private static final long serialVersionUID = 1L;
	private int wkn;
	private int aid;
	private int bid;
	private double streubesitz;

	public Aktie() {

	}

	@Id
	public int getWkn() {
		return wkn;
	}

	public void setWkn(int wkn) {
		this.wkn = wkn;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public double getStreubesitz() {
		return streubesitz;
	}

	public void setStreubesitz(double streubesitz) {
		this.streubesitz = streubesitz;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Aktie [wkn=" + wkn + ", aid=" + aid + ", bid=" + bid + ", streubesitz=" + streubesitz + "]";
	}

}
