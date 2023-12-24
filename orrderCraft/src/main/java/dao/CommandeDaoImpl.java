package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.model.Client;
import com.model.Commande;

public class CommandeDaoImpl implements ICommandeDao {

	@Override
	public Commande save(Commande commande) {
		Connection connection=SingletonConnection.getConnection();
		try {
			PreparedStatement ps=connection.prepareStatement
					("INSERT INTO commandes (numeroCommande, status, createdAtDate, client_id) VALUES (?, ?, ?, ?)");
					//////
			System.out.println("commande.getClient().getId()***"+commande.getClient().getId());
					ps.setString(1, commande.getNumeroCommande());
					ps.setBoolean(2, commande.isStatus());
                    ps.setDate(3, new java.sql.Date(commande.getCreatedAtDate().getTime()));
                    ps.setLong(4, commande.getClient().getId());
                          
			        ps.executeUpdate();
			        PreparedStatement ps2=connection.prepareStatement
			        		("SELECT MAX(ID) AS MAX_ID FROM COMMANDES");
			        ResultSet rs=ps2.executeQuery();
			        if(rs.next()) {
			        	commande.setId(rs.getLong("MAX_ID"));
			        	System.out.println("commande.setId(rs.getLong(\"MAX_ID\"));***"+commande.getId());
			        }   
		ps.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		return commande;
	}

	@Override
	public List<Commande> commandesParMC(boolean mc) {
		List<Commande> commandes= new ArrayList<>();
		Connection connection=SingletonConnection.getConnection();
		try {
			PreparedStatement ps=connection.prepareStatement
					("SELECT * FROM COMMANDES WHERE status LIKE ?");
			ps.setBoolean(1, mc);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Commande c=new Commande();			
				c.setId(rs.getLong("id"));
				c.setNumeroCommande(rs.getString("numeroCommande"));
				c.setStatus(rs.getBoolean("status"));
				c.setCreatedAtDate(rs.getDate("createdAtDate"));
				Client cl=new Client();
								
				PreparedStatement ps2=connection.prepareStatement
		        		("SELECT * FROM CLIENTS WHERE ID = ? ");
				ps2.setInt(1, rs.getInt("client_id"));
		        ResultSet rs1=ps2.executeQuery();
		        if(rs1.next()) {		        	
					cl.setId(rs1.getLong("ID"));
					cl.setNom(rs1.getString("NOM"));
					cl.setEmail(rs1.getString("EMAIL"));
					cl.setAdresse(rs1.getString("ADRESSE"));
					cl.setTel(rs1.getString("TEL"));
					c.setClient(cl);
					commandes.add(c);
		        }		      							
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return commandes;
	}

	@Override
	public Commande getCommande(Long id) {
		Commande c= null;
		Connection connection=SingletonConnection.getConnection();
		try {
			PreparedStatement ps=connection.prepareStatement
					("SELECT * FROM COMMANDES WHERE ID=?");
			ps.setLong(1, id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
			    c=new Commande();
			    c.setId(rs.getLong("id"));
				c.setNumeroCommande(rs.getString("numeroCommande"));
				c.setStatus(rs.getBoolean("status"));
				c.setCreatedAtDate(rs.getDate("createdAtDate"));			
								
				PreparedStatement ps2=connection.prepareStatement
		        		("SELECT * FROM CLIENTS WHERE 	client_id = ? ");
				ps2.setInt(1, rs.getInt("client_id"));
		        ResultSet rs1=ps2.executeQuery();
		        if(rs1.next()) {
		        	Client cl=new Client();
					cl.setId(rs.getLong("ID"));
					cl.setNom(rs.getString("NOM"));
					cl.setEmail(rs.getString("EMAIL"));
					cl.setAdresse(rs.getString("ADRESSE"));
					cl.setTel(rs.getString("TEL"));
					c.setClient(cl);	
		        }}				
							
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return c;
	}

	@Override
	public Commande update(Commande p) {
		Connection connection=SingletonConnection.getConnection();
		try {
			PreparedStatement ps=connection.prepareStatement
					("UPDATE COMMANDES SET id=?, status=? WHERE id=?") ;
					ps.setLong(1, p.getId());
			        ps.setBoolean(2, p.isStatus());
			        ps.setLong(3, p.getId());
			        ps.executeUpdate();
			        ps.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return p;
	}

	@Override
	public void deleteCommande(Long id) {
		Connection connection=SingletonConnection.getConnection();
		try {
			PreparedStatement ps=connection.prepareStatement
					("DELETE FROM COMMANDES WHERE id=?");
					ps.setLong(1, id);
			        ps.executeUpdate();
			        ps.close();
			        
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	

}
