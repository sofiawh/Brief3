package dao;

import java.util.List;
import com.model.Client;
public interface IClientDao {
    public Client save(Client p);
    public List<Client> clientsParMC(String mc);
    public Client getClient(Long id);
    public Client update(Client p);
    public void deleteClient(Long id);
}
