/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.rochette.cours.m3.projets.likes.model.views.main;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import static fr.insa.rochette.cours.m3.projets.likes.model.Application.link;
import fr.insa.rochette.cours.m3.projets.likes.model.Produit;
import java.sql.SQLException;

/**
 *
 * @author cecil
 */

@Route(value = "add-product",  layout = MainLayout.class)
@PageTitle("Ajouter un produit | Gestion Production")
public class AjouterProduitView extends FormLayout{
    
    private TextField ref = new TextField("Nom");
    private TextField description = new TextField("Description");

    private Button ajouter = new Button("Ajouter");
    /*ajouter.getStyle().set("background-color", "rgb(0,220,0)");
    ajouter.getStyle().set("color", "rgb(255,255,255)");*/

    private Dialog dialog = new Dialog();
    
    public AjouterProduitView() {
        
        
        add(ref, description,ajouter);
        
        // Evénements du bouton ajouter
        ajouter.addClickListener(e -> {
            Notification.show("Produit ajouté");
        });
        ajouter.addClickListener(event -> ajouterProduit());
    }

    private void ajouterProduit() {
        // Logique pour ajouter un Produit à la base de données
        Produit nouveauProduit = new Produit(ref.getValue(), description.getValue());
        nouveauProduit.setRef(ref.getValue());
        nouveauProduit.setDescription(description.getValue());

        

        try {
            // Utilisez la connexion pour ajouter la nouvelle machine à la base de données
            nouveauProduit.sauvegarde(link);
        } catch (SQLException ex) {
            ex.printStackTrace();
            Notification.show("Erreur lors de l'ajout du produit : " + ex.getMessage());
        }
        
        //Vider les boutons après avoir appuyé
        ref.setValue("");
        description.setValue("");
        
        // Fermer le dialogue après l'ajout
        dialog.close();
    }
    
}
