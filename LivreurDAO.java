package ma.fstt.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivreurDAO extends BaseDAO<Livreur>{
    public LivreurDAO() throws SQLException {

        super();
    }

    @Override
    public void save(Livreur object) throws SQLException {

        String request = "insert into livreur (nom , telephone) values (? , ?)";

        // mapping objet table

        this.preparedStatement = this.connection.prepareStatement(request);
        // mapping
        this.preparedStatement.setString(1 , object.getNom());

        this.preparedStatement.setString(2 , object.getTelephone());


        this.preparedStatement.execute();
    }
    // méthode pour mettre à jour les informations d'un livreur existante dans la base de données
    @Override
    public void update(Livreur object) throws SQLException {
    String query = "UPDATE livreur SET nom = ?, telephone = ? WHERE id_livreur = ?";
        this.preparedStatement = this.connection.prepareStatement(query);
        // mapping
        this.preparedStatement.setString(1, object.getNom());

        this.preparedStatement.setString(2, object.getTelephone());

        this.preparedStatement.setLong(3, object.getId_livreur());

     int ligneM= this.preparedStatement.executeUpdate();
     if(ligneM>0){
         System.out.println("Donnees modifiers avec succes");
     }else{
         System.out.println("Aucun donee n'a ete modifiee");
     }
    }

        // méthode pour supprimer un livreur de la base de données
    @Override
    public void delete(Livreur object) throws SQLException {

                String query="DELETE FROM livreur WHERE id_livreur=?";
                this.preparedStatement = connection.prepareStatement(query);
                preparedStatement.setLong(1, object.getId_livreur());
                preparedStatement.executeUpdate();

    }

    @Override
    public List<Livreur> getAll()  throws SQLException {

        List<Livreur> mylist = new ArrayList<Livreur>();

        String request = "select * from livreur ";

        this.statement = this.connection.createStatement();

        this.resultSet =   this.statement.executeQuery(request);

// parcours de la table
        while ( this.resultSet.next()){

// mapping table objet
            mylist.add(new Livreur(this.resultSet.getLong(1) ,
                    this.resultSet.getString(2) , this.resultSet.getString(3)));


        }


        return mylist;
    }

    @Override
    public Livreur getOne(Long id) throws SQLException {
        return null;
    }
}
