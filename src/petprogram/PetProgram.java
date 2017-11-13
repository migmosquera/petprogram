/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petprogram;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author migmosquera
 */
public class PetProgram extends Application {
    
    
    @PersistenceUnit(unitName="petProgramPU")
    static private EntityManagerFactory emf;
    
    static 
    {
        try 
        {
            emf = Persistence.createEntityManagerFactory("petProgramPU");
            EntityManager em = emf.createEntityManager();
        } 
        catch (Exception e) 
        {
            System.out.println("Fatal: Unable to create entity manager factory");
            e.printStackTrace();
        }  
    }

    public static EntityManagerFactory getEmf() {
        return emf;
    }

    public static void setEmf(EntityManagerFactory emf) {
        PetProgram.emf = emf;
    }
    
    
    
    @Override
    public void start(Stage stage) throws Exception {
        
        
        Parent root = FXMLLoader.load(getClass().getResource("/vistas/FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
