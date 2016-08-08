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
@Table(name = "Kurs")

@NamedQueries({
	@NamedQuery(name = "Kurs.findten", query = "SELECT k FROM Kurs k WHERE k.wkn = :wkn"),
})
public class Kurs implements Serializable {

	private static final long serialVersionUID = 1L;
	private int wkn;
	private int hid;
	private Date datum;
	private double startpreis;
	private double hoechstpreis;
	private double tiefpreis;
	private double schlusspreis;
	private int volumen;

	public Kurs() {

	}

	@Id
	public int getWkn() {
		return wkn;
	}

	public void setWkn(int wkn) {
		this.wkn = wkn;
	}

	public int getHid() {
		return hid;
	}

	public void setHid(int hid) {
		this.hid = hid;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public double getStartpreis() {
		return startpreis;
	}

	public void setStartpreis(double startpreis) {
		this.startpreis = startpreis;
	}

	public double getHoechstpreis() {
		return hoechstpreis;
	}

	public void setHoechstpreis(double hoechstpreis) {
		this.hoechstpreis = hoechstpreis;
	}

	public double getTiefpreis() {
		return tiefpreis;
	}

	public void setTiefpreis(double tiefpreis) {
		this.tiefpreis = tiefpreis;
	}

	public double getSchlusspreis() {
		return schlusspreis;
	}

	public void setSchlusspreis(double schlusspreis) {
		this.schlusspreis = schlusspreis;
	}

	public int getVolumen() {
		return volumen;
	}

	public void setVolumen(int volumen) {
		this.volumen = volumen;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Kurs [wkn=" + wkn + ", hid=" + hid + ", datum=" + datum + ", startpreis=" + startpreis
				+ ", hoechstpreis=" + hoechstpreis + ", tiefpreis=" + tiefpreis + ", schlusspreis=" + schlusspreis
				+ ", volumen=" + volumen + "]";
	}

}
