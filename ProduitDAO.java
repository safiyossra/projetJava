package ma.fstt.model;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class ProduitDAO extends BaseDAO<Produit> {
    public ProduitDAO() throws SQLException {

        super();
    }


    @Override
    public void save(Produit object) throws SQLException {
        String request = "insert into produit (Nom, prix_unitaire,description) values (? ,?, ?)";

        // mapping objet table

        this.preparedStatement = this.connection.prepareStatement(request);
        // mapping
        this.preparedStatement.setString(1 , object.getNom());

        this.preparedStatement.setFloat(2 , object.getPrix_unitaire());

        this.preparedStatement.setString(3 , object.getDescription());

        this.preparedStatement.execute();
    }

    @Override
    public void update(Produit object) throws SQLException {

            String query = "UPDATE produit SET Nom = ?, prix_unitaire = ?, description=?, WHERE id_produit = ?";
            this.preparedStatement = this.connection.prepareStatement(query);
            // mapping
            this.preparedStatement.setString(1, object.getNom());

            this.preparedStatement.setFloat(2, object.getPrix_unitaire());

           this.preparedStatement.setString(3, object.getDescription());



            this.preparedStatement.execute();
    }

    @Override
    public void delete(Produit object) throws SQLException {
        String query="DELETE FROM produit WHERE id_produit=?";
        this.preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, object.getId_produit());
        preparedStatement.executeUpdate();

    }

    @Override
    public List<Produit> getAll() throws SQLException {
        List<Produit> mylist = new ArrayList<Produit>();

        String request = "select * from produit ";

        this.statement = this.connection.createStatement();

        this.resultSet =   this.statement.executeQuery(request);

// parcours de la table
        while ( this.resultSet.next()){

// mapping table objet
            mylist.add(new Produit(this.resultSet.getLong(1) ,
                    this.resultSet.getString(2) , this.resultSet.getFloat(3),this.resultSet.getString(4)));


        }


        return mylist;
    }

    @Override
    public Produit getOne(Long id) throws SQLException {
        return null;
    }
}
