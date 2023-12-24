package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.SingletonConnection;

public class Article {
	
	private Long id;	
	private String designation;		
	private double prix;	
	private int quantite;
	
	public Article() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Article(String designation, double prix, int quantite) {
		super();
		this.designation = designation;
		this.prix = prix;
		this.quantite = quantite;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
	public void addQuantite(int quantite) {
        this.quantite += quantite;
    }

    public void removeQuantite(int quantite) {
        if (quantite <= this.quantite) {
            this.quantite -= quantite;
        } else {
            System.out.println("Quantité insuffisante en stock.");
        }
    }       
	@Override
	public String toString() {
		return "Article [id=" + id + ", designation=" + designation + ", prix=" + prix + ", quantite=" + quantite + "]";
	}
	

}
