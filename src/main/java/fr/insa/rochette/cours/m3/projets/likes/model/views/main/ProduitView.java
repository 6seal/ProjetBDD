/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.rochette.cours.m3.projets.likes.model.views.main;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;

/**
 *
 * @author sarah
 */
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import fr.insa.rochette.cours.m3.projets.likes.model.Application;
import static fr.insa.rochette.cours.m3.projets.likes.model.Application.link;
import fr.insa.rochette.cours.m3.projets.likes.model.Machine;
import fr.insa.rochette.cours.m3.projets.likes.model.Produit;
import jakarta.annotation.security.PermitAll;
import java.sql.SQLException;

/**
 *
 * @author sarah
 */
@PermitAll
@Route(value="produit", layout = MainLayout.class)
@PageTitle("Produits | Gestion Production")
public class ProduitView extends Div {

    private Grid<Produit> grid;
    private Dialog dialog;
    
    public ProduitView() {
        
        grid = new Grid<>(Produit.class);
        
        try {     
            
            grid.setItems(Produit.tousLesProduits(link));
        } catch (SQLException ex) {
            Notification.show("Erreur lors de la visualisation dess machines : " + ex.getMessage());
        }

        Button supprimer = new Button("Supprimer");
        supprimer.addClickListener(event -> supprimerProduit());
        supprimer.addClickListener(e -> Notification.show("Produit supprimée"));

        add(grid, supprimer);
    }
    
    private void supprimerProduit() {
        // Récupérer la machine sélectionnée dans la grille
       Produit produitSelectionne = grid.asSingleSelect().getValue();

        if (produitSelectionne != null) {
            try {
                // Utiliser la connexion pour supprimer la machine de la base de données
                produitSelectionne.delete(Application.link);
                // Mettre à jour la grille après la suppression
                grid.setItems(Produit.tousLesProduits(Application.link));
            } catch (SQLException ex) {
                ex.printStackTrace();
                Notification.show("Erreur lors de la suppression du produit : " + ex.getMessage());
            }
        } else {
            Notification.show("Veuillez sélectionner un produit à supprimer.");
        }
        dialog.close();
    }
    
}