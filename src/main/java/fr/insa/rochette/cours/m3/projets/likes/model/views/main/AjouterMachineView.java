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
import fr.insa.rochette.cours.m3.projets.likes.model.Machine;
import java.sql.SQLException;

/**
 *
 * @author cecil
 */

@Route(value = "add-machine",  layout = MainLayout.class)
@PageTitle("Ajouter une machine | Gestion Production")
public class AjouterMachineView extends FormLayout{
    
    private TextField nom = new TextField("Nom");
    private TextField description = new TextField("Description");
    private TextField puissance = new TextField("Puissance");
    private TextField coutHoraire = new TextField("Coût horaire");
    private TextField operation = new TextField("Opération");

    private Button ajouter = new Button("Ajouter");
    /*ajouter.getStyle().set("background-color", "rgb(0,220,0)");
    ajouter.getStyle().set("color", "rgb(255,255,255)");*/

    private Dialog dialog = new Dialog();
    

    public AjouterMachineView() {
        
        
        
        add(nom, description, puissance, coutHoraire, operation, ajouter);
        
        // Evenements du bouton ajouter
        ajouter.addClickListener(event -> ajouterMachine());
        ajouter.addClickListener(e -> {
            Notification.show("Machine ajoutée");
        });
    }

    private void ajouterMachine() {
        // Logique pour ajouter une machine à la base de données
        Machine nouvelleMachine = new Machine(nom.getValue(), description.getValue(),Integer.parseInt(puissance.getValue()),Integer.parseInt(coutHoraire.getValue()),operation.getValue());
        nouvelleMachine.setNom(nom.getValue());
        nouvelleMachine.setDescription(description.getValue());
        nouvelleMachine.setPuissance(Integer.parseInt(puissance.getValue()));
        nouvelleMachine.setCouthoraire(Integer.parseInt(coutHoraire.getValue()));
        nouvelleMachine.setOperation(operation.getValue());

        try {
            // Utilisez la connexion pour ajouter la nouvelle machine à la base de données
            nouvelleMachine.sauvegarde(link);
        } catch (SQLException ex) {
            ex.printStackTrace();
            Notification.show("Erreur lors de l'ajout du produit : " + ex.getMessage());
        }

        //Vider les boutons après avoir appuyé
        nom.setValue("");
        description.setValue("");
        puissance.setValue("");
        coutHoraire.setValue("");
        operation.setValue("");
        
        // Fermeture du dialogue après l'ajout
        dialog.close();
    }

}
