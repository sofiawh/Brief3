package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.Article;
import com.model.ArticleCommande;
import com.model.Client;
import com.model.Commande;

public class ArticleCommandeDaoImpl implements IArticleCommandeDao {

	@Override
	public ArticleCommande save(ArticleCommande ac) {
		Connection connection=SingletonConnection.getConnection();
		try {
			PreparedStatement ps=connection.prepareStatement
					("INSERT INTO articlescommande ( quantite, SousTotal, commande_id, article_id) VALUES (?,?,?,?)");
					ps.setInt(1, ac.getQuantite());
			        ps.setDouble(2, ac.getSousTotal());
			        ps.setLong(3, ac.getCommande().getId());
			        ps.setLong(4, ac.getArticle().getId());
			        ps.executeUpdate();
			        PreparedStatement ps2=connection.prepareStatement
			        		("SELECT MAX(ID) AS MAX_ID FROM articlescommande");
			        ResultSet rs=ps2.executeQuery();
			        if(rs.next()) {
			        	ac.setId(rs.getLong("MAX_ID"));			        	
			        }
			        ps.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ac;
	}

	@Override
	public List<ArticleCommande> articleCommandesParMC(Long mc) {
		List<ArticleCommande> articleCommandes= new ArrayList<>();
		Connection connection=SingletonConnection.getConnection();
		try {
			PreparedStatement ps=connection.prepareStatement("SELECT * FROM articlescommande WHERE commande_id LIKE ?");
			ps.setLong(1, mc);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				ArticleCommande ac=new ArticleCommande();
				ac.setId(rs.getLong("id"));
				ac.setQuantite(rs.getInt("quantite"));
				ac.setSousTotal(rs.getDouble("SousTotal"));
				ac.setQuantite(rs.getInt("QUANTITE"));
				
				/////
				Commande c=new Commande();
				
				PreparedStatement ps2=connection.prepareStatement
		        		("SELECT * FROM commandes WHERE id = ? ");
				ps2.setInt(1, rs.getInt("commande_id"));
		        ResultSet rs1=ps2.executeQuery();
		        if(rs1.next()) {		        	
					c.setId(rs1.getLong("id"));
					c.setNumeroCommande(rs1.getString("numeroCommande"));
					c.setStatus(rs1.getBoolean("status"));
					c.setCreatedAtDate(rs1.getDate("createdAtDate"));
					
				////
					
					Client clt=new Client();					
					PreparedStatement ps3=connection.prepareStatement
			        		("SELECT * FROM CLIENTS WHERE ID = ? ");
					ps3.setInt(1, rs1.getInt("client_id"));
			        ResultSet rs3=ps3.executeQuery();
			        if(rs3.next()) {		        	
						clt.setId(rs3.getLong("ID"));
						clt.setNom(rs3.getString("NOM"));
						clt.setEmail(rs3.getString("EMAIL"));
						clt.setAdresse(rs3.getString("ADRESSE"));
						clt.setTel(rs3.getString("TEL"));
						c.setClient(clt);
						ac.setCommande(c);
			        }
		        }
				////
						Article art=new Article();					
						PreparedStatement ps4=connection.prepareStatement
				        		("SELECT * FROM produits WHERE ID = ? ");
						ps4.setInt(1, rs.getInt("article_id"));
				        ResultSet rs4=ps4.executeQuery();
				        if(rs4.next()) {		        	
				        	art.setId(rs4.getLong("ID"));
				        	art.setDesignation(rs4.getString("DESIGNATION"));
				        	art.setPrix(rs4.getDouble("PRIX"));
				        	art.setQuantite(rs4.getInt("QUANTITE"));				        	
						ac.setArticle(art);	
						
						/////
				
				
				articleCommandes.add(ac);
				        }
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return articleCommandes;
	}

	@Override
	public ArticleCommande getArticleCommande(Long id) {
		ArticleCommande ac= null;
		Connection connection=SingletonConnection.getConnection();
		try {
			PreparedStatement ps=connection.prepareStatement
					("SELECT * FROM articlescommande WHERE id=?");
			ps.setLong(1, id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {		
				ac=new ArticleCommande();
				ac.setId(rs.getLong("id"));
				ac.setQuantite(rs.getInt("quantite"));
				ac.setSousTotal(rs.getDouble("SousTotal"));
				ac.setQuantite(rs.getInt("QUANTITE"));
			
			/////
				Commande c=new Commande();
			
			PreparedStatement ps2=connection.prepareStatement
	        		("SELECT * FROM commandes WHERE id = ? ");
			ps2.setInt(1, rs.getInt("commande_id"));
	        ResultSet rs1=ps2.executeQuery();
	        if(rs1.next()) {		        	
				c.setId(rs1.getLong("id"));
				c.setNumeroCommande(rs1.getString("numeroCommande"));
				c.setStatus(rs1.getBoolean("status"));
				c.setCreatedAtDate(rs1.getDate("createdAtDate"));
				
			////
				
				Client clt=new Client();					
				PreparedStatement ps3=connection.prepareStatement
		        		("SELECT * FROM CLIENTS WHERE ID = ? ");
				ps3.setInt(1, rs1.getInt("client_id"));
		        ResultSet rs3=ps3.executeQuery();
		        if(rs3.next()) {		        	
					clt.setId(rs3.getLong("ID"));
					clt.setNom(rs3.getString("NOM"));
					clt.setEmail(rs3.getString("EMAIL"));
					clt.setAdresse(rs3.getString("ADRESSE"));
					clt.setTel(rs3.getString("TEL"));
					c.setClient(clt);
					ac.setCommande(c);
		        }
	        }
			////
					Article art=new Article();					
					PreparedStatement ps4=connection.prepareStatement
			        		("SELECT * FROM produits WHERE ID = ? ");
					ps4.setInt(1, rs.getInt("article_id"));
			        ResultSet rs4=ps4.executeQuery();
			        if(rs4.next()) {		        	
			        	art.setId(rs4.getLong("ID"));
			        	art.setDesignation(rs4.getString("DESIGNATION"));
			        	art.setPrix(rs4.getDouble("PRIX"));
			        	art.setQuantite(rs4.getInt("QUANTITE"));				        	
					ac.setArticle(art);	
					
					/////
				
		
			        }
		}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return ac;
	}

	@Override
	public ArticleCommande update(ArticleCommande p) {
		/*Connection connection=SingletonConnection.getConnection();
		try {
			PreparedStatement ps=connection.prepareStatement
					("UPDATE articlescommande SET DESIGNATION=?, PRIX=?, QUANTITE=? WHERE ID=?") ;
					ps.setString(1, p.getDesignation());
			        ps.setDouble(2, p.getPrix());
			        ps.setInt(3, p.getQuantite());
			        ps.setLong(4, p.getId());
			        ps.executeUpdate();
			        ps.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		*/
		return p;
	}

	@Override
	public void deleteArticleCommande(Long id) {
		Connection connection=SingletonConnection.getConnection();
		try {
			PreparedStatement ps=connection.prepareStatement
					("DELETE FROM articlescommande WHERE id=?");
					ps.setLong(1, id);
			        ps.executeUpdate();
			        ps.close();
			        
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	

}
