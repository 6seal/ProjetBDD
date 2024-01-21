/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.rochette.cours.m3.projets.likes.model.views.main;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import fr.insa.rochette.cours.m3.projets.likes.model.GestionBdD;
import fr.insa.rochette.cours.m3.projets.likes.model.Machine;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author cecil
 */



@Route(value = "machine",  layout = MainLayout.class)
@PageTitle("Liste des machines | Gestion Production")

/**
 *
 * @author cecil
 */
public class MachineView extends Div{
/*
    public MachineView() {
        
        Grid<Machine> grid = new Grid<>(Machine.class);
        
        
            /*grid.addColumn(fr.insa.rochette.cours.m3.projets.likes.model.Machine::getId).setHeader("Id");
            grid.addColumn(fr.insa.rochette.cours.m3.projets.likes.model.Machine::getNom).setHeader("Nom");
            grid.addColumn(fr.insa.rochette.cours.m3.projets.likes.model.Machine::getDescription).setHeader("Description");
            grid.addColumn(fr.insa.rochette.cours.m3.projets.likes.model.Machine::getPuissance).setHeader("Puissance");
            grid.addColumn(fr.insa.rochette.cours.m3.projets.likes.model.Machine::getCouthoraire).setHeader("Coût horaire");
            grid.addColumn(fr.insa.rochette.cours.m3.projets.likes.model.Machine::getOperation).setHeader("Opération");
            //        grid.addThemeVariants(Grid.LUMO_ROW_STRIPES);
        
          try {
            grid.setItems(Machine.toutesLesMachines(GestionBdD.defautCon()));
        } catch (SQLException ex) {
            Logger.getLogger(MachineView.class.getName()).log(Level.SEVERE, null, ex);
        }

        add(grid);
    }*/
    
    
    
    
    
        
    private Dialog dialog;
    
    public MachineView(){
        boolean ajoutverif = false;
        Button ajouter = new Button("Ajouter une nouvelle machine");
        ajouter.setWidth("300px");
        ajouter.setHeight("300px");
        ajouter.addClickListener(event -> {
                    //v.add(upload);
                    dialog.open();
                });
        VerticalLayout v = new VerticalLayout();
        v.setAlignItems(FlexComponent.Alignment.CENTER);
        List<Machine> machines = new ArrayList<>();
        HorizontalLayout h = new HorizontalLayout();
        Button cancel = new Button("Cancel");
        cancel.addClickListener(event -> {
        dialog.close();
        });
        VerticalLayout dialoglayout = new VerticalLayout();
        dialog = new Dialog();
        dialog.setHeaderTitle("Creer une machine");

        Grid<Machine> grid = new Grid<>(Machine.class);
        
        try {
            
            machines = Machine.toutesLesMachines(GestionBdD.defautCon());
            grid.setItems(machines);
        } catch (SQLException ex) {
           // throw new Error("Connection impossible", ex);           
            Logger.getLogger(MachineView.class.getName()).log(Level.SEVERE, null, ex);
        }

        add(grid);
          
            
        }

      
}
    

        