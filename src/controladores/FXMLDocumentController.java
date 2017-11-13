/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import clases.User;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
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
                System.out.println(user.getPass());
                Converter convert = new Converter();
                String pass = convert.descifra(user.getPass().getBytes(Charset.forName("UTF-8")));
                System.out.println(pass);
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
                User user = new User();
                Converter convert = new Converter();
                user.setUsername("admin");
                byte[] pass = convert.cifra("admin");
                user.setPass(Arrays.toString(pass));
                em.getTransaction().begin();
                em.persist(user);
                em.getTransaction().commit();
            }
            
        }
        catch(Exception e) 
        {
             System.out.println("Fatal:" + e);
        }
       
        
    }    

   
    
}
