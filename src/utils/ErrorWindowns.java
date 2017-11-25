/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author migmosquera
 */
public class ErrorWindowns {
    
    public String errorWindows(String title, String context, String content)
    {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(context);
        alert.setContentText(content);

        alert.showAndWait();
        return "error";
    }
    
}
