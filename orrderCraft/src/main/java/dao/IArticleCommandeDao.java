package dao;

import java.util.List;
import com.model.ArticleCommande;
public interface IArticleCommandeDao {
    public ArticleCommande save(ArticleCommande p);
    public List<ArticleCommande> articleCommandesParMC(Long motCle);
    public ArticleCommande getArticleCommande(Long id);
    public ArticleCommande update(ArticleCommande p);
    public void deleteArticleCommande(Long id);
}
