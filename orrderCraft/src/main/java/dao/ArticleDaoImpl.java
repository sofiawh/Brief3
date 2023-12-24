package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.Article;

public class ArticleDaoImpl implements IArticleDao {

	@Override
	public Article save(Article p) {
		Connection connection=SingletonConnection.getConnection();
		try {
			PreparedStatement ps=connection.prepareStatement
					("INSERT INTO PRODUITS (DESIGNATION, PRIX, QUANTITE) VALUES (?,?,?)");
					ps.setString(1, p.getDesignation());
			        ps.setDouble(2, p.getPrix());
			        ps.setInt(3, p.getQuantite());
			        ps.executeUpdate();
			        PreparedStatement ps2=connection.prepareStatement
			        		("SELECT MAX(ID) AS MAX_ID FROM PRODUITS");
			        ResultSet rs=ps2.executeQuery();
			        if(rs.next()) {
			        	p.setId(rs.getLong("MAX_ID"));			        	
			        }
			        ps.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return p;
	}

	@Override
	public List<Article> articlesParMC(String mc) {
		List<Article> articles= new ArrayList<>();
		Connection connection=SingletonConnection.getConnection();
		try {
			PreparedStatement ps=connection.prepareStatement("SELECT * FROM PRODUITS WHERE DESIGNATION LIKE ?");
			ps.setString(1, mc);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Article p=new Article();
				p.setId(rs.getLong("ID"));
				p.setDesignation(rs.getString("DESIGNATION"));
				p.setPrix(rs.getDouble("PRIX"));
				p.setQuantite(rs.getInt("QUANTITE"));
				articles.add(p);
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return articles;
	}

	@Override
	public Article getArticle(Long id) {
		Article p= null;
		Connection connection=SingletonConnection.getConnection();
		try {
			PreparedStatement ps=connection.prepareStatement
					("SELECT * FROM PRODUITS WHERE ID=?");
			ps.setLong(1, id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
			    p=new Article();
				p.setId(rs.getLong("ID"));
				p.setDesignation(rs.getString("DESIGNATION"));
				p.setPrix(rs.getDouble("PRIX"));
				p.setQuantite(rs.getInt("QUANTITE"));
							
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return p;
	}

	@Override
	public Article update(Article p) {
		Connection connection=SingletonConnection.getConnection();
		try {
			PreparedStatement ps=connection.prepareStatement
					("UPDATE PRODUITS SET DESIGNATION=?, PRIX=?, QUANTITE=? WHERE ID=?") ;
					ps.setString(1, p.getDesignation());
			        ps.setDouble(2, p.getPrix());
			        ps.setInt(3, p.getQuantite());
			        ps.setLong(4, p.getId());
			        ps.executeUpdate();
			        ps.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return p;
	}

	@Override
	public void deleteArticle(Long id) {
		Connection connection=SingletonConnection.getConnection();
		try {
			PreparedStatement ps=connection.prepareStatement
					("DELETE FROM PRODUITS WHERE ID=?");
					ps.setLong(1, id);
			        ps.executeUpdate();
			        ps.close();
			        
		}catch (SQLException e) {
			e.printStackTrace();
		}	
		}
	public void updateStockInDatabase(Article article) {
    	Connection connection=SingletonConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE stock SET quantite = ? WHERE nom_produit = ?")) {
            preparedStatement.setInt(1, article.getQuantite());
            preparedStatement.setString(2, article.getDesignation());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	

}
