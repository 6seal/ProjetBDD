package fr.insa.rochette.cours.m3.projets.likes.model.views.main;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import fr.insa.rochette.cours.m3.projets.likes.model.Application;
import fr.insa.rochette.cours.m3.projets.likes.model.Machine;
import java.sql.SQLException;

@Route(value = "machine",  layout = MainLayout.class)
@PageTitle("Liste des machines | Gestion Production")
public class MachineView extends Div {


    private Grid<Machine> grid;
    private Dialog dialog;

    public MachineView() {
        grid = new Grid<>(Machine.class);

        try {
            grid.setItems(Machine.toutesLesMachines(Application.link));
        } catch (SQLException ex) {
            Notification.show("Erreur lors de la visualisation des machines : " + ex.getMessage());
        }

        Button supprimer = new Button("Supprimer");
        supprimer.addClickListener(event -> supprimerMachine());
        supprimer.addClickListener(e -> Notification.show("Machine supprimée"));

        add(grid, supprimer);
    }

    private void supprimerMachine() {
        // Récupérer la machine sélectionnée dans la grille
        Machine machineSelectionnee = grid.asSingleSelect().getValue();

        if (machineSelectionnee != null) {
            try {
                // Utiliser la connexion pour supprimer la machine de la base de données
                machineSelectionnee.delete(Application.link);
                // Mettre à jour la grille après la suppression
                grid.setItems(Machine.toutesLesMachines(Application.link));
            } catch (SQLException ex) {
                ex.printStackTrace();
                Notification.show("Erreur lors de la suppression de la machine : " + ex.getMessage());
            }
        } else {
            Notification.show("Veuillez sélectionner une machine à supprimer.");
        }
        dialog.close();
    }
    
}