package dao;

import java.util.List;
import com.model.Article;
public interface IArticleDao {
    public Article save(Article p);
    public List<Article> articlesParMC(String mc);
    public Article getArticle(Long id);
    public Article update(Article p);
    public void deleteArticle(Long id);
    public void updateStockInDatabase(Article p);
}
