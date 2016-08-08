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
@Table(name = "Rente")

@NamedQueries({})
public class Rente implements Serializable {

	private static final long serialVersionUID = 1L;
	private int wkn;
	private String typ;
	private Date faelligkeitsdatum;
	/*
	 * long nicht int, sonst numerischer Überlauf
	 */
	private long zinskupon;
	private String rating;

	public Rente() {

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

	public Date getFaelligkeitsdatum() {
		return faelligkeitsdatum;
	}

	public void setFaelligkeitsdatum(Date faelligkeitsdatum) {
		this.faelligkeitsdatum = faelligkeitsdatum;
	}

	public long getZinskupon() {
		return zinskupon;
	}

	public void setZinskupon(long zinskupon) {
		this.zinskupon = zinskupon;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Rente [wkn=" + wkn + ", typ=" + typ + ", faelligkeitsdatum=" + faelligkeitsdatum + ", zinskupon="
				+ zinskupon + ", rating=" + rating + "]";
	}

}
