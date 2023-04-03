package ma.fstt.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LignecommandeDAO extends BaseDAO<Lignecommande>{
    public LignecommandeDAO() throws SQLException {

        super();
    }
    @Override
    public void save(Lignecommande object) throws SQLException {
        String request = "insert into ligne_commande (id_commande ,id_produit,quantite) values (? , ?,?)";

        // mapping objet table

        this.preparedStatement = this.connection.prepareStatement(request);
        /* mapping */
        this.preparedStatement.setLong(1 , object.getId_commande());

        this.preparedStatement.setLong(2 , object.getId_produit());

        this.preparedStatement.setLong(3 , object.getQuantite());

        this.preparedStatement.execute();
    }

    @Override
    public void update(Lignecommande object) throws SQLException {
        String query = "UPDATE ligne_commande SET id_commande = ?, id_produit = ?,quantite=?, WHERE id = ?";
        this.preparedStatement = this.connection.prepareStatement(query);
        // mapping
        this.preparedStatement.setLong(1, object.getId_commande());

        this.preparedStatement.setLong(2, object.getId_produit());

        this.preparedStatement.setLong(3, object.getQuantite());

        this.preparedStatement.execute();
    }


    @Override
    public void delete(Lignecommande object) throws SQLException {
   String query = "DELETE FROM commande WHERE id= ?";
        this.preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, object.getId());
        preparedStatement.executeUpdate();

    }

    @Override
    public List<Lignecommande> getAll() throws SQLException {
        List<Lignecommande> mylist = new ArrayList<Lignecommande>();

        String request = "select * from ligne_commande ";

        this.statement = this.connection.createStatement();

        this.resultSet =   this.statement.executeQuery(request);

// parcours de la table
        while ( this.resultSet.next()){

// mapping table objet
            mylist.add(new Lignecommande(this.resultSet.getLong(1) ,
                    this.resultSet.getLong(2) , this.resultSet.getLong(3),this.resultSet.getInt(3)));


        }


        return mylist;
    }


    @Override
    public Lignecommande getOne(Long id) throws SQLException {
        return null;
    }
}
