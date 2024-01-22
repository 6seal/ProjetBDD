package fr.insa.rochette.cours.m3.projets.likes.model;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import fr.insa.rochette.utils.database.ConnectionSGBD;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The entry point of the Spring Boot application.
 *
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 *
 */
@SpringBootApplication
@Theme(value = "")
public class Application implements AppShellConfigurator {
    
    public static ConnectionSGBD link;
    public static void main(String[] args) {
        
        //Connexion à la base de données
        try {
            link = GestionBdD.defautCon();
        } catch (SQLException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("The connection to database failed");
        }
        
        SpringApplication.run(Application.class, args);
    }

}
