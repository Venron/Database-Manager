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
@Table(name = "Unternehmensdaten")

@NamedQueries({})
public class Unternehmensdaten implements Serializable {

	private static final long serialVersionUID = 1L;
	private int wkn;
	private int jahr;
	/*long sonst numerischer Überlauf*/
	private long bilanzsumme;
	private long ausschuettung;
	private int anzahlaktien;

	public Unternehmensdaten() {

	}

	@Id
	public int getWkn() {
		return wkn;
	}

	public void setWkn(int wkn) {
		this.wkn = wkn;
	}

	public int getJahr() {
		return jahr;
	}

	public void setJahr(int jahr) {
		this.jahr = jahr;
	}

	public long getBilanzsumme() {
		return bilanzsumme;
	}

	public void setBilanzsumme(long bilanzsumme) {
		this.bilanzsumme = bilanzsumme;
	}

	public long getAusschuettung() {
		return ausschuettung;
	}

	public void setAusschuettung(long ausschuettung) {
		this.ausschuettung = ausschuettung;
	}

	public int getAnzahlaktien() {
		return anzahlaktien;
	}

	public void setAnzahlaktien(int anzahlaktien) {
		this.anzahlaktien = anzahlaktien;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Unternehmensdaten [wkn=" + wkn + ", jahr=" + jahr + ", bilanzsumme=" + bilanzsumme + ", ausschuettung="
				+ ausschuettung + ", anzahlaktien=" + anzahlaktien + "]";
	}

}
