package com.model;

import java.util.List;

public class Client {
	
	private Long id;	
	private String nom;		
	private String email;	
	private String adresse;	
	private String tel;
	private List<Commande> commandes;
	
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Client(String nom, String email, String adresse, String tel) {
		super();
		this.nom = nom;
		this.email = email;
		this.adresse = adresse;
		this.tel = tel;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public List<Commande> getCommandes() {
		return commandes;
	}
	public void setCommandes(List<Commande> commandes) {
		this.commandes = commandes;
	}
	@Override
	public String toString() {
		return "Client [id=" + id + ", nom=" + nom + ", email=" + email + ", adresse=" + adresse + ", tel=" + tel
				+ ", commandes=" + commandes + "]";
	}
	
}
