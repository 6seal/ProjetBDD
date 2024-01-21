/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.rochette.cours.m3.projets.likes.model.views.main;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
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
public class AjouterMachineView extends FormLayout{
   
    
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
    
    
/*    private Dialog dialog;
    private Binder<Machine> binder;

    public AjouterMachineView() {
        // Initialisation du dialog
        initializeDialog();

        // Création du formulaire d'ajout
        TextField nomField = new TextField("Nom");
        TextField descriptionField = new TextField("Description");
        TextField puissanceField = new TextField("Puissance");
        TextField coutField = new TextField("Coût horaire");
        TextField operationField = new TextField("Opération");

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
        try {
            machine.sauvegarde(GestionBdD.defautCon());
        }
        catch (SQLException ex) {
            // Gérer l'erreur (par exemple, afficher un message à l'utilisateur)
            ex.printStackTrace();
        }
    }

    private void initializeDialog() {
        dialog = new Dialog();
        add(new H2 ("Ajouter une nouvelle machine"));
        // Ajoutez d'autres configurations de dialog si nécessaire
    }*/
    
    private TextField nom = new TextField("Nom");
    private TextField description = new TextField("Description");
    private TextField puissance = new TextField("Puissance");
    private TextField coutHoraire = new TextField("Coût horaire");
    private TextField operation = new TextField("Opération");

    private Button ajouter = new Button("Ajouter");

    private Dialog dialog = new Dialog();

    public AjouterMachineView() {
        // Ajoutez vos composants à la mise en page ici
        add(nom, description, puissance, coutHoraire, operation, ajouter);

        // Configurez les événements des boutons ou d'autres interactions ici
        ajouter.addClickListener(event -> ajouterMachine());
    }

    private void ajouterMachine() {
        // Logique pour ajouter une machine à la base de données
        Machine.setNom(nom.getValue());
        Machine.setDescription(description.getValue());
        Machine.setPuissance(puissance.getValue());
        Machine.setCouthoraire(coutHoraire.getValue());
        Machine.setOperation(operation.getValue());

        try {
            // Utilisez la connexion pour ajouter la nouvelle machine à la base de données
            Machine.sauvegarde(GestionBdD.defautCon());
        } catch (SQLException ex) {
            ex.printStackTrace(); // Gérez l'exception selon vos besoins
        }

        // Fermez le dialogue après l'ajout
        dialog.close();
    }

}
