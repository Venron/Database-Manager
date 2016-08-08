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
@Table(name = "Fondsanteil")

@NamedQueries({})
public class Fondsanteil implements Serializable {

	private static final long serialVersionUID = 1L;
	private int fondswkn;
	private int papierwkn;
	private long stueckzahl;

	public Fondsanteil() {

	}

	@Id
	public int getFondswkn() {
		return fondswkn;
	}

	public void setFondswkn(int fondswkn) {
		this.fondswkn = fondswkn;
	}

	public int getPapierwkn() {
		return papierwkn;
	}

	public void setPapierwkn(int papierwkn) {
		this.papierwkn = papierwkn;
	}

	public long getStueckzahl() {
		return stueckzahl;
	}

	public void setStueckzahl(long stueckzahl) {
		this.stueckzahl = stueckzahl;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Fondsanteil [fondswkn=" + fondswkn + ", papierwkn=" + papierwkn + ", stueckzahl=" + stueckzahl + "]";
	}

}
