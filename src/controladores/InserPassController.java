/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import clases.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.swing.JOptionPane;
import utils.Converter;
import utils.ValidateData;
import utils.ErrorWindowns;

/**
 * FXML Controller class
 *
 * @author migmosquera
 */
public class InserPassController implements Initializable {

  
    @PersistenceUnit(unitName="petProgramPU")
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("petProgramPU");
    EntityManager em = emf.createEntityManager();
    @FXML
    private TextField txtUser;
    @FXML
    private TextField txtPhone;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtPass;
    @FXML
    private PasswordField txtPassRepeat;
    @FXML 
    private Button btnSave;
    
     private Stage dialogStage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    @FXML
    private void RegisterUser(ActionEvent event) {
        try
        {
            ValidateData compare = new ValidateData();
            ErrorWindowns error = new ErrorWindowns();
            if(compare.comparePass(txtPass.getText(),txtPassRepeat.getText()) == true)
            {
                User user = new User();
                Converter convert = new Converter();
                if(compare.validEmail(txtUser.getText()) != true)
                {
                    error.errorWindows("Error Email", "Email no valido", "Debe Ingresar un Email correcto");
                    
                }
                else if(compare.validPhone(txtPhone.getText()) != true)
                {
                    error.errorWindows("Error telefono", "Telefono no valido", "Debe Ingresar un telefono correcto");
                }
                else{
                    user.setUsername(txtUser.getText());
                    user.setEmail(txtEmail.getText());
                    user.setPhone(Float.parseFloat(txtPhone.getText()));
                    String pass = convert.encriptarCadena(txtPass.getText());
                    user.setPass(pass);
                    /*em.getTransaction().begin();
                    em.persist(user);
                    em.getTransaction().commit();*/
                    JOptionPane.showMessageDialog(null,"Su usuario se ha guardado");
                    Stage stage = (Stage) btnSave.getScene().getWindow();
                    stage.close();
                }
            }else
            {
                error.errorWindows("Error Contrasena", "Contrasena no esta igual", "La contrasena no esta igual");
            }
            

        }
        catch(Exception e)
        {
            ErrorWindowns error = new ErrorWindowns();
            error.errorWindows("Error", "Error", e.toString());
        }
    }
    
}
