/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.rochette.cours.m3.projets.likes.model.views.main;

import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import fr.insa.rochette.cours.m3.projets.likes.model.ProduitBrut;
import java.util.ArrayList;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import fr.insa.rochette.cours.m3.projets.likes.model.GestionBdD;
import fr.insa.rochette.cours.m3.projets.likes.model.Produit;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Route(value = "stockage",layout=MainLayout.class)
@PageTitle("Liste des produits | Gestion Production")


public class StockageView extends VerticalLayout{
    public StockageView() throws SQLException {
        
        
        //Connection con = GestionBdD.connectSurServeurM3();

        
        VerticalLayout verticalLayout = new VerticalLayout();
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        
        H3 title1 = new H3("Produits disponibles");
         //Tableau d'affichage des produits disponibles (à relier à la BdD)
        Grid<Produit> grid1 = new Grid<>(Produit.class);
        
        //grid1.setItems(tousLesProduits(con));
        
        H3 title2 = new H3("Produits Bruts disponibles");
        Grid<ProduitBrut> grid2 = new Grid<>(ProduitBrut.class);
              
        //grid2.setItems(tousLesProduitsBruts(con));
        
        //Bouton pour ajouter un produit
        Button BAjout1 = new Button("Ajouter");
        Button BSuppr1 = new Button("Supprimer");
        BAjout1.getStyle().set("background-color", "rgb(0,220,0)");
        BAjout1.getStyle().set("color", "rgb(255,255,255)");
        BSuppr1.getStyle().set("background-color", "rgb(220,0,0)");
        BSuppr1.getStyle().set("color", "rgb(255,255,255)");
        
        // Création de la boîte de dialogue
        List<Produit> listeProduits = new ArrayList<>();
        
        BAjout1.addClickListener(clickEvent1 -> {
            Dialog dialog = new Dialog();

            dialog.setHeaderTitle("Ajouter un Produit");

            HorizontalLayout dialogLayout = new HorizontalLayout();

            TextField Tf1 = new TextField();
            TextField Tf2 = new TextField();
            Tf1.setLabel("Référence");
            Tf2.setLabel("Description");

            dialogLayout.add(Tf1, Tf2);
            dialog.add(dialogLayout);

            Button saveButton1 = new Button("Ajouter");
            saveButton1.getStyle().set("background-color", "white");
            saveButton1.getStyle().set("color", "green");

            saveButton1.addClickListener(clickEvent2 -> {
                // Ajoutez le produit à la liste au lieu de créer une nouvelle liste
                String ref = Tf1.getValue();
                String description = Tf2.getValue();

                Produit produit1 = new Produit(ref, description);
                listeProduits.add(produit1);
                //try {
                //    produit1.sauvegarde(con);
                //} catch (SQLException ex) {
                //    Logger.getLogger(StockageView.class.getName()).log(Level.SEVERE, null, ex);
                //}
                grid1.setItems(listeProduits);
              
                saveButton1.getUI().ifPresent(ui -> ui.navigate(StockageView.class));

                dialog.close();
            });
            
            Button cancelButton1 = new Button("Cancel", e -> dialog.close());
            cancelButton1.getStyle().set("background-color","white");
            cancelButton1.getStyle().set("color","red");
            
            dialog.getFooter().add(cancelButton1);
            dialog.getFooter().add(saveButton1);

            dialog.open();
        });
        
        
        Button BAjout2 = new Button("Ajouter");
        Button BSuppr2 = new Button("Supprimer");
        BAjout2.getStyle().set("background-color", "rgb(0,220,0)");
        BAjout2.getStyle().set("color", "rgb(255,255,255)");
        BSuppr2.getStyle().set("background-color", "rgb(220,0,0)");
        BSuppr2.getStyle().set("color", "rgb(255,255,255)");
        
      // Création de la boîte de dialogue
        List<ProduitBrut> listeProduitsBruts = new ArrayList<>();
        
        BAjout2.addClickListener(clickEvent3 -> {
            Dialog dialog = new Dialog();

            dialog.setHeaderTitle("Ajouter un Produit Brut");

            HorizontalLayout dialogLayout = new HorizontalLayout();

            TextField Tf3 = new TextField();
            TextField Tf4 = new TextField();
            Tf3.setLabel("Description");
            Tf4.setLabel("Quantité");

            dialogLayout.add(Tf3, Tf4);
            dialog.add(dialogLayout);

            Button saveButton2 = new Button("Ajouter");
            saveButton2.getStyle().set("background-color", "white");
            saveButton2.getStyle().set("color", "green");

            saveButton2.addClickListener(clickEvent4 -> {

                String des = Tf3.getValue();
                String qtt = Tf4.getValue();
                
                int qttint = Integer.parseInt(qtt); //Transformer le String en int
                
                ProduitBrut produitbrut1 = new ProduitBrut(des, qttint);
                listeProduitsBruts.add(produitbrut1);
                //try {
                //    produitbrut1.sauvegarde(con);
                //} catch (SQLException ex) {
                //    Logger.getLogger(StockageView.class.getName()).log(Level.SEVERE, null, ex);
                //}
                
                
                saveButton2.getUI().ifPresent(ui -> ui.navigate(StockageView.class));
                
                grid2.setItems(listeProduitsBruts);
                
                dialog.close();
            });
            
            Button cancelButton2 = new Button("Cancel", e -> dialog.close());
            cancelButton2.getStyle().set("background-color","white");
            cancelButton2.getStyle().set("color","red");
            
            dialog.getFooter().add(cancelButton2);
            dialog.getFooter().add(saveButton2);

            dialog.open();            
        });
            
      
        verticalLayout.add(title1, grid1, BAjout1, BSuppr1, title2, grid2, BAjout2,BSuppr2);
        verticalLayout.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, BAjout1, BSuppr1);
        verticalLayout.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, BAjout2, BSuppr2);
        add(verticalLayout);
      
    }
}