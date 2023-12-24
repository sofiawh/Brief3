package web;

import java.util.ArrayList;
import java.util.List;

import com.model.Article;
import com.model.ArticleCommande;

public class ArticleCommandeModel {
    private Long motCle;
    private List<ArticleCommande> articleCommandes=new ArrayList<>();
	public Long getMotCle() {
		return motCle;
	}
	public void setMotCle(Long motCle2) {
		this.motCle = motCle2;
	}
	public List<ArticleCommande> getArticleCommandes() {
		return articleCommandes;
	}
	public void setArticleCommandes(List<ArticleCommande> articleCommandes) {
		this.articleCommandes = articleCommandes;
	}
	
	public double calculerTotal() {
        double total = 0;
        if(articleCommandes!=null) {
        	 for (ArticleCommande l : articleCommandes) {
                 total += l.getSousTotal();
             }
             return total;
        }
        else return 0;
    }

}
