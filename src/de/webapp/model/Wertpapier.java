package de.webapp.model;

import java.io.Serializable;
import java.sql.Date;

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
@Table(name = "Wertpapier")

@NamedQueries({})
public class Wertpapier implements Serializable {

	private static final long serialVersionUID = 1L;
	private int wkn;
	private String name;
	private String typ;
	private int stueckzahl;
	private Date ausgabedatum;
	private double ausgabekurs;

	public Wertpapier() {

	}

	@Id
	public int getWkn() {
		return wkn;
	}

	public void setWkn(int wkn) {
		this.wkn = wkn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	public int getStueckzahl() {
		return stueckzahl;
	}

	public void setStueckzahl(int stueckzahl) {
		this.stueckzahl = stueckzahl;
	}

	public Date getAusgabedatum() {
		return ausgabedatum;
	}

	public void setAusgabedatum(Date ausgabedatum) {
		this.ausgabedatum = ausgabedatum;
	}

	public double getAusgabekurs() {
		return ausgabekurs;
	}

	public void setAusgabekurs(double ausgabekurs) {
		this.ausgabekurs = ausgabekurs;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Wertpapier [wkn=" + wkn + ", name=" + name + ", typ=" + typ + ", stueckzahl=" + stueckzahl
				+ ", ausgabedatum=" + ausgabedatum + ", ausgabekurs=" + ausgabekurs + "]";
	}

}
