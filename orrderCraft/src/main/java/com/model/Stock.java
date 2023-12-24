package com.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Stock {
    private Map<String, Article> stock;

    public Stock() {
        this.stock = new HashMap<>();
    }

    public void ajouterArticle(Article article) {
        stock.put(article.getDesignation(), article);
    }

    public void afficherStock() {
        System.out.println("Stock actuel :");
        for (Article article : stock.values()) {
            System.out.println(article.getDesignation() + ": " + article.getQuantite());
        }
    }

    public void ajouterStock(String nomArticle, int quantite) {
        Article article = stock.get(nomArticle);
        if (article != null) {
            article.addQuantite(quantite);
        } else {
            System.out.println("Article non trouvé dans le stock.");
        }
    }

    public void retirerStock(String nomArticle, int quantite) {
        Article article = stock.get(nomArticle);
        if (article != null) {
            article.removeQuantite(quantite);
        } else {
            System.out.println("Article non trouvé dans le stock.");
        }
    }
}