package org.example.jdbcFlyway.client;

import org.example.jdbcFlyway.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoClientService {
   private PreparedStatement insertSt;
   private PreparedStatement getByIdSt;
   private PreparedStatement maxIdSt;
   private PreparedStatement updateSt;
   private PreparedStatement deleteByIdSt;
   private  PreparedStatement selectAllSt;

   public DaoClientService(Connection connection){
       try {
           insertSt = connection.prepareStatement("insert into client(name) values(?)");
           updateSt = connection.prepareStatement("update client set name=? where ID = ?");
           getByIdSt = connection.prepareStatement("SELECT name from client where ID = ?");
           maxIdSt = connection.prepareStatement("SELECT max(id) AS maxId from client");
           deleteByIdSt = connection.prepareStatement("DELETE FROM client WHERE ID = ?");
           selectAllSt = connection.prepareStatement("Select * from client" );
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
   }
    public long create(String name){
       long idNewClient;
        try {
            insertSt.setString(1, name);
            insertSt.executeUpdate();
            ResultSet rs = maxIdSt.executeQuery();
            rs.next();
            idNewClient = rs.getLong("maxId");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return idNewClient ;
    }
    public ClientEntity getById(long id) {
       ClientEntity client = new ClientEntity();
        try {
            getByIdSt.setLong(1,id);
            try (ResultSet rs = getByIdSt.executeQuery()){
                if(!rs.next()) return null;
                client.setID(id);
                client.setName(rs.getString("name"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return client ;
    }
    public void setNameById(long id, String name){
        try {
            updateSt.setString(1, name);
            updateSt.setLong(2, id);
            updateSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void deleteById(long id){
        try {
            deleteByIdSt.setLong(1,id);
            deleteByIdSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
   public List<ClientEntity> listAll(){
       List<ClientEntity> result = new ArrayList<>();
       try (ResultSet rs = selectAllSt.executeQuery()){
          while (rs.next()){
              ClientEntity client = new ClientEntity();
              client.setID(rs.getLong("ID"));
              client.setName(rs.getString("name"));
              result.add(client);
          }
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }

       return result;
   }

    public static void main(String[] args) {
        DaoClientService dao = new DaoClientService(Database.getConnection());
        //System.out.println(dao.create("Nina"));

        System.out.println(dao.getById(7).getName());

        dao.setNameById(7,"Viktor");
        System.out.println(dao.getById(7).getName());

        //dao.deleteById(9);

        System.out.println(dao.listAll());


    }

}
