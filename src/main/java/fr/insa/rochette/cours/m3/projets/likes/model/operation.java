/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.rochette.cours.m3.projets.likes.model;

import fr.insa.rochette.utils.database.ConnectionSGBD;
import fr.insa.rochette.utils.list.ListUtils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author sarah
 */
public class operation {
    private int id;
    private int idtype;
    private int idproduit;
    private String produitbrut;

    private operation(int id, int idtype, int idproduit,String produitbrut) {
        this.id = id;
        this.idtype = idtype;
        this.idproduit = idproduit;
        this.produitbrut=produitbrut;
    }
    
    public operation(int idtype, int idproduit, String produitbrut) {
        this(-1,idtype, idproduit,produitbrut);
    }
  
    
    public void sauvegarde(ConnectionSGBD connSGBD)throws SQLException{
        try (PreparedStatement st= connSGBD.getCon().prepareStatement(
                 "insert into utilisateur (idtype,idproduit) values (?,?)",
                PreparedStatement.RETURN_GENERATED_KEYS)){
            st.setInt(1,this. idtype);
            st.setInt(2, this.idproduit);
            st.setString(3, this.produitbrut);
            st.executeUpdate();
            try (ResultSet ids= st.getGeneratedKeys()){
                ids.next();
                this.id=ids.getInt(1);
            }
        }
    }
    
    public void delete(ConnectionSGBD connSGBD)throws SQLException{
        try (PreparedStatement st= connSGBD.getCon().prepareStatement(
                 "delete from operation where id=?")){
            st.setInt(1,this. id);
            st.executeUpdate();
         }
    }
    
    
    
    public static operation demande(ConnectionSGBD connSGBD)throws SQLException{
        typeop choix1 =ListUtils.selectOne("--- selectionnez un type d'opération",typeop.tousLesTypeop(connSGBD), typeop::toString);
        Produit choix2 =ListUtils.selectOne("--- selectionnez un produit",Produit.tousLesProduits(connSGBD), Produit::toString);
        ProduitBrut choix3 =ListUtils.selectOne("--- selectionnez un produit brut",ProduitBrut.tousLesProduitsBruts(connSGBD), ProduitBrut::toString);
        return new operation (choix1.getId(),choix2.getId(),choix3.getString());
    }
    
    public static List<operation> toutesLesOperations(ConnectionSGBD connSGBD) throws SQLException{
        List<operation> alls = new ArrayList<>();
        try (PreparedStatement st = connSGBD.getCon().prepareStatement(" select id,idtype,idproduit,produitbrut from operation")){
         ResultSet res = st.executeQuery();
         while(res.next()){
             int id = res.getInt("id");
             int idtype = res.getInt("idtype");
             int idproduit = res.getInt("idproduit");
             String produitbrut=res.getString("produitbrut");
             alls.add(new operation(id,idtype, idproduit,produitbrut));
         }
     } 
     return alls;
    }
 @Override
    public String toString() {
        return "operation{" + "id=" + id + ", idtype=" + idtype + ", idproduit=" + idproduit + ", produitbrut=" + produitbrut + '}';
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdtype() {
        return idtype;
    }

    public void setIdtype(int idtype) {
        this.idtype = idtype;
    }

    public int getIdproduit() {
        return idproduit;
    }

    public void setIdproduit(int idproduit) {
        this.idproduit = idproduit;
    }

    public String getProduitbrut() {
        return produitbrut;
    }

    public void setProduitbrut(String produitbrut) {
        this.produitbrut = produitbrut;
    }

   

   
}