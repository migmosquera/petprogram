/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import clases.User;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import utils.Converter;



/**
 *
 * @author migmosquera
 */
public class FXMLDocumentController implements Initializable {
    
    @PersistenceUnit(unitName="petProgramPU")
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("petProgramPU");
    EntityManager em = emf.createEntityManager();
    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtPasword;
    @FXML
    private Label txtErrorUser;
    @FXML
    private Label txtErrorPass;
    @FXML
    private void Login(ActionEvent event) {
        
        if (txtUsuario.getText().isEmpty())
        {
            txtErrorUser.setVisible(true);
            txtErrorPass.setVisible(false);
            txtErrorUser.setText("Ingrese su Usuario");
        }
        else if(txtPasword.getText().isEmpty())
        {
            txtErrorPass.setVisible(true);
            txtErrorUser.setVisible(false);
            txtErrorPass.setText("Ingrese su Contrasena");
        }
        else
        {
            try
            {
                txtErrorUser.setVisible(false);
                txtErrorPass.setVisible(false);
                Query queryUser = em.createNamedQuery("User.findByUsername");
                queryUser.setParameter("username", txtUsuario.getText());
                User user = (User) queryUser.getSingleResult();
                Converter convert = new Converter();
                String pass = convert.desencriptaCadena(user.getPass());
                if(txtPasword.getText().equals(pass))
                {
                    System.out.println("usuario logueado");
                }
                else
                {
                    System.out.println("contrasena Incorrecta");
                }
            }
            catch(Exception e)
            {
                System.out.println("Fatal:" + e);
                txtErrorUser.setVisible(true);

            }
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try
        {
            Query queryUser = em.createNamedQuery("User.findAll");
            List<User> users= queryUser.getResultList();
            if(users.isEmpty())
            {
                
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(FXMLDocumentController.class.getResource("/vistas/InserPass.fxml"));
                AnchorPane page = (AnchorPane) loader.load();
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Edit Person");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                Window primaryStage = null;
                dialogStage.initOwner(primaryStage);
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);
                InserPassController controller = loader.getController();
                controller.setDialogStage(dialogStage);
                 dialogStage.showAndWait();
                /*User user = new User();
                Converter convert = new Converter();
                user.setUsername("admin");
                String pass = convert.encriptarCadena("admin");
                user.setPass(pass);
                em.getTransaction().begin();
                em.persist(user);
                em.getTransaction().commit();*/
            }else
            {
                
               
            }
            
        }
        catch(Exception e) 
        {
             System.out.println("Fatal:" + e);
        }
       
    }    

    

   
    
}
