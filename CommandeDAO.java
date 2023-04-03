package ma.fstt.model;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class CommandeDAO extends BaseDAO<commande> {

    public CommandeDAO() throws SQLException {
        super();
    }

    // méthode pour insérer une nouvelle commande dans la base de données
    @Override
    public void save(commande object) throws SQLException {
        String request = "insert into commande (Date_D,Date_f,klm,Client) values (? ,?,? ,?)";

        // mapping objet table

        this.preparedStatement = this.connection.prepareStatement(request);
        // mapping
        this.preparedStatement.setString(1, object.getDate_D());

        this.preparedStatement.setString(2, object.getDate_f());

        this.preparedStatement.setString(3, object.getKlm());

        this.preparedStatement.setString(4, object.getClient());

         this.preparedStatement.execute();

    }

    // méthode pour mettre à jour les informations d'une commande existante dans la base de données
    @Override
    public void update(commande object) throws SQLException {
        String query = "UPDATE commande SET Date_D= ?,Date_f= ?,klm=? ,Client=?  WHERE id_commande = ?";
        this.preparedStatement = this.connection.prepareStatement(query);
        // mapping
        this.preparedStatement.setString(1, object.getDate_D());

        this.preparedStatement.setString(2, object.getDate_f());

        this.preparedStatement.setString(3, object.getKlm());

        this.preparedStatement.setString(4, object.getClient());
        this.preparedStatement.execute();

    }


    // méthode pour supprimer une commande de la base de données
    @Override
    public void delete(commande object) throws SQLException {
        String query = "DELETE FROM commande WHERE id_commande = ?";
        this.preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, object.getId_commande());
        preparedStatement.executeUpdate();

    }

    @Override
    public List<commande> getAll() throws SQLException {

        List<commande> list = new ArrayList<commande>();

        String request = "select * from commande";

        this.statement = this.connection.createStatement();

        this.resultSet = this.statement.executeQuery(request);

// parcours de la table
        while (this.resultSet.next()) {

// mapping table objet
            list.add(new commande(this.resultSet.getLong(1),
                    this.resultSet.getString(2), this.resultSet.getString(3), this.resultSet.getString(4), this.resultSet.getString(5)));

        }

        return list;
    }

    @Override
    public commande getOne(Long id) throws SQLException {
        return null;
    }
}

