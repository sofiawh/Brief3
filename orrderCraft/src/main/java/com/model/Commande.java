package com.model;

import java.util.Date;
import java.util.List;

public class Commande {
	
	private Long id;
	private String numeroCommande;
	private boolean status;
	private Date createdAtDate;
	private Client client;
	private List<ArticleCommande> ArticlesCommandes;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Commande() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Commande(String numeroCommande, boolean status, Date createdAtDate) {
		super();
		this.numeroCommande = numeroCommande;
		this.status = status;
		this.createdAtDate = createdAtDate;
		//this.client = client;
	}
	public String getNumeroCommande() {
		return numeroCommande;
	}
	public void setNumeroCommande(String numeroCommande) {
		this.numeroCommande = numeroCommande;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Date getCreatedAtDate() {
		return createdAtDate;
	}
	public void setCreatedAtDate(Date createdAtDate) {
		this.createdAtDate = createdAtDate;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public List<ArticleCommande> getArticlesCommandes() {
		return ArticlesCommandes;
	}
	public void setArticlesCommandes(List<ArticleCommande> articlesCommandes) {
		ArticlesCommandes = articlesCommandes;
	}
	@Override
	public String toString() {
		return "Commande [numeroCommande=" + numeroCommande + ", status=" + status + ", createdAtDate=" + createdAtDate
				+ ", client=" + client + ", ArticlesCommandes=" + ArticlesCommandes + "]";
	}	
//	public double calculerTotal() {
//        double total = 0;
//        if(ArticlesCommandes!=null) {
//        	 for (ArticleCommande l : ArticlesCommandes) {
//                 total += l.calculerSousTotal();
//             }
//             return total;
//        }
//        else return 0;
//    }
	

}
