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
@Table(name = "profile")

@NamedQueries({ 
	@NamedQuery(name = "Profile.findAll", query = "SELECT p FROM Profile p"),
	@NamedQuery(name = "Profile.findByEmail", query = "SELECT p FROM Profile p WHERE p.email = :email")
})
public class Profile implements Serializable {

	private static final long serialVersionUID = 1L;

	private int pid;
	private String wunschmiete;
	private String wunschgroesse;
	private String wunschlage;
	private String interessen;
	private String taetigkeit;
	private String raucher;
	private String sonderwuensche;
	private String email;

	public Profile() {

	}

	@Id
	@SequenceGenerator(name = "profileSeq", sequenceName = "APP_PROFILE_DATA_SEQ", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profileSeq")
	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getWunschmiete() {
		return wunschmiete;
	}

	public void setWunschmiete(String wunschmiete) {
		this.wunschmiete = wunschmiete;
	}

	public String getWunschgroesse() {
		return wunschgroesse;
	}

	public void setWunschgroesse(String wunschgroesse) {
		this.wunschgroesse = wunschgroesse;
	}

	public String getWunschlage() {
		return wunschlage;
	}

	public void setWunschlage(String wunschlage) {
		this.wunschlage = wunschlage;
	}

	public String getInteressen() {
		return interessen;
	}

	public void setInteressen(String interessen) {
		this.interessen = interessen;
	}

	public String getTaetigkeit() {
		return taetigkeit;
	}

	public void setTaetigkeit(String taetigkeit) {
		this.taetigkeit = taetigkeit;
	}

	public String getRaucher() {
		return raucher;
	}

	public void setRaucher(String raucher) {
		this.raucher = raucher;
	}

	public String getSonderwuensche() {
		return sonderwuensche;
	}

	public void setSonderwuensche(String sonderwuensche) {
		this.sonderwuensche = sonderwuensche;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Profile [pid=" + pid + ", wunschmiete=" + wunschmiete + ", wunschgroesse=" + wunschgroesse
				+ ", wunschlage=" + wunschlage + ", interessen=" + interessen + ", taetigkeit=" + taetigkeit
				+ ", raucher=" + raucher + ", sonderwuensche=" + sonderwuensche + ", email=" + email + "]";
	}

}
