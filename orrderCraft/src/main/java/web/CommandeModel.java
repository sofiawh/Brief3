package web;

import java.util.ArrayList;
import java.util.List;

import com.model.Article;
import com.model.Commande;

public class CommandeModel {
    private boolean motCle;
    private List<Commande> commandes=new ArrayList<>();
	public boolean getMotCle() {
		return motCle;
	}
	public void setMotCle(boolean motCle2) {
		this.motCle = motCle2;
	}
	public List<Commande> getCommandes() {
		return commandes;
	}
	public void setCommandes(List<Commande> commandes) {
		this.commandes = commandes;
	}

}
