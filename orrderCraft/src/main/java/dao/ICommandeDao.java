package dao;

import java.util.List;
import com.model.Commande;
public interface ICommandeDao {
    public Commande save(Commande p);
    public List<Commande> commandesParMC(boolean mc);
    public Commande getCommande(Long id);
    public Commande update(Commande p);
    public void deleteCommande(Long id);
}
