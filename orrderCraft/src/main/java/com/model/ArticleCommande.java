package com.model;

public class ArticleCommande {
	
		private Long id;
		private int quantite;
		private double sousTotal;
		Commande commande;
		Article article;		
		
		public ArticleCommande() {
			super();
			// TODO Auto-generated constructor stub
		}
		public ArticleCommande(int quantite, double sousTotal) {
			super();
			this.quantite = quantite;
			this.sousTotal = sousTotal;			
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public int getQuantite() {
			return quantite;
		}
		public void setQuantite(int quantite) {
			this.quantite = quantite;
		}
		public double getSousTotal() {
			return sousTotal;
		}
		public void setSousTotal(double sousTotal) {
			this.sousTotal = sousTotal;
		}
		public Commande getCommande() {
			return commande;
		}
		public void setCommande(Commande commande) {
			this.commande = commande;
		}
		public Article getArticle() {
			return article;
		}
		public void setArticle(Article article) {
			this.article = article;
		}
		@Override
		public String toString() {
			return "ArticleCommande [quantite=" + quantite + ", SousTotal=" + sousTotal + ", article=" + article.getDesignation() + "]";
		}		
	    public double calculerSousTotal() {
	    	if (article != null) {
	            return quantite * article.getPrix();
	        } else {
	            return 0.0; 
	        }
	    }

}
