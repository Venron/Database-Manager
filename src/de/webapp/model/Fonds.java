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
@Table(name = "Fonds")

@NamedQueries({})
public class Fonds implements Serializable {

	private static final long serialVersionUID = 1L;
	private int wkn;
	private String typ;
	private double maxanteil;

	public Fonds() {

	}

	@Id
	public int getWkn() {
		return wkn;
	}

	public void setWkn(int wkn) {
		this.wkn = wkn;
	}

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	public double getMaxanteil() {
		return maxanteil;
	}

	public void setMaxanteil(double maxanteil) {
		this.maxanteil = maxanteil;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Fonds [wkn=" + wkn + ", typ=" + typ + ", maxanteil=" + maxanteil + "]";
	}

}
