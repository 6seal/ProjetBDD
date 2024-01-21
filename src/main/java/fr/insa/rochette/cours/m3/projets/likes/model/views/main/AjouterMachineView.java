/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.rochette.cours.m3.projets.likes.model.views.main;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import fr.insa.rochette.cours.m3.projets.likes.model.GestionBdD;
import fr.insa.rochette.cours.m3.projets.likes.model.Machine;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author cecil
 */

@Route(value = "add-machine",  layout = MainLayout.class)
@PageTitle("Ajouter une machine | Gestion Production")
public class AjouterMachineView extends HorizontalLayout{
   
    
    /*private TextField nom;
    private Button createMachine;


    public AjouterMachineView() {
        nom = new TextField("Votre machine");
        createMachine = new Button("Say hello");
        createMachine.addClickListener(e -> {
            Notification.show("La machine " + nom.getValue() + "a été ajoutée");
        });
        createMachine.addClickShortcut(Key.ENTER);

        setMargin(true);
        setVerticalComponentAlignment(Alignment.END, nom, createMachine);

        add(nom, createMachine);
    }*/
    
    private Dialog dialog;
    private Binder<Machine> binder;

    public AjouterMachineView() {
        // Initialisation du dialog
        initializeDialog();

        // Création du formulaire d'ajout
        TextField nomField = new TextField("Nom");
        TextField descriptionField = new TextField("Description");

        Button addButton = new Button("Ajouter");
        addButton.addClickListener(event -> {
            Machine nouvelleMachine = binder.getBean();
            ajouterMachine(nouvelleMachine);
            dialog.close();
            // Rafraîchir la liste des machines si nécessaire
        });

        binder = new Binder<>(Machine.class);
        binder.bindInstanceFields(this);

        // Ajout des composants au dialog
        dialog.add(nomField, descriptionField, addButton);

        // ... (suite du code existant)
    }

    private void ajouterMachine(Machine machine) {
        try (Connection con = GestionBdD.defautCon()) {
            machine.ajouterMachine(con);
        } catch (SQLException ex) {
            // Gérer l'erreur (par exemple, afficher un message à l'utilisateur)
            ex.printStackTrace();
        }
    }

    private void initializeDialog() {
        dialog = new Dialog();
        dialog.setHeader("Ajouter une nouvelle machine");
        // Ajoutez d'autres configurations de dialog si nécessaire
    }

}
