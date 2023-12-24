package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.Client;

public class ClientDaoImpl implements IClientDao {

	@Override
	public Client save(Client p) {
		Connection connection=SingletonConnection.getConnection();
		try {
			PreparedStatement ps=connection.prepareStatement
					("INSERT INTO CLIENTS (NOM, EMAIL, ADRESSE, TEL) VALUES (?,?,?,?)");
					ps.setString(1, p.getNom());
			        ps.setString(2, p.getEmail());
			        ps.setString(3, p.getAdresse());
			        ps.setString(4, p.getTel());
			        ps.executeUpdate();
			        PreparedStatement ps2=connection.prepareStatement
			        		("SELECT MAX(ID) AS MAX_ID FROM CLIENTS");
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
	public List<Client> clientsParMC(String mc) {
		List<Client> clients= new ArrayList<>();
		Connection connection=SingletonConnection.getConnection();
		try {
			PreparedStatement ps=connection.prepareStatement("SELECT * FROM CLIENTS WHERE NOM LIKE ?");
			ps.setString(1, mc);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Client p=new Client();
				p.setId(rs.getLong("ID"));
				p.setNom(rs.getString("NOM"));
				p.setEmail(rs.getString("EMAIL"));
				p.setAdresse(rs.getString("ADRESSE"));
				p.setTel(rs.getString("TEL"));
				clients.add(p);
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return clients;
	}

	@Override
	public Client getClient(Long id) {
		Client p= null;
		Connection connection=SingletonConnection.getConnection();
		try {
			PreparedStatement ps=connection.prepareStatement
					("SELECT * FROM CLIENTS WHERE ID=?");
			ps.setLong(1, id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
			    p=new Client();
				p.setId(rs.getLong("ID"));
				p.setNom(rs.getString("NOM"));
				p.setEmail(rs.getString("EMAIL"));
				p.setAdresse(rs.getString("ADRESSE"));
							
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return p;
	}

	@Override
	public Client update(Client p) {
		Connection connection=SingletonConnection.getConnection();
		try {
			PreparedStatement ps=connection.prepareStatement
					("UPDATE CLIENTS SET NOM=?, EMAIL=?, ADRESSE=? , TEL=? WHERE ID=?") ;
					ps.setString(1, p.getNom());
			        ps.setString(2, p.getEmail());
			        ps.setString(3, p.getAdresse());
			        ps.setString(4, p.getTel());
			        ps.setLong(5, p.getId());
			        ps.executeUpdate();
			        ps.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return p;
	}

	@Override
	public void deleteClient(Long id) {
		Connection connection=SingletonConnection.getConnection();
		try {
			PreparedStatement ps=connection.prepareStatement
					("DELETE FROM CLIENTS WHERE ID=?");
					ps.setLong(1, id);
			        ps.executeUpdate();
			        ps.close();
			        
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	

}
