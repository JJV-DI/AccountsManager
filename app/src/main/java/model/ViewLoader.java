package model;

import controllers.AC_Tab_BtnController;
import controllers.AccountCreatorController;
import controllers.AdminAccessPassConfirmController;
import controllers.AdminNewPassConfirmController;
import controllers.AdminUpdatePassConfirmController;
import controllers.ConfirmDelController;
import controllers.PC_Account_CardController;
import controllers.PS_AddController;
import controllers.PS_BtnController;
import controllers.PrivPassConfirmController;
import controllers.ProfileCreatorController;
import controllers.ProfileSelectController;
import controllers.SN_CardController;
import controllers.SocialNetworkCreatorController;
import controllers.UF_Account_CardController;
import controllers.UserInfoController;
import java.io.IOException;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ViewLoader {
    
    /*PROFILE SELECT VIEWS*/
    public void loadProfileSelect(VBox vbBody, ViewManager viewManager) {
        vbBody.getChildren().clear();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistas/frmProfileSelect.fxml"));
            vbBody.getChildren().add(fxmlLoader.load());
            ProfileSelectController profileSelectController = fxmlLoader.getController();
            profileSelectController.setVboxBody(vbBody);
            profileSelectController.setViewManager(viewManager);
            profileSelectController.showUsers();
        } catch (IOException e) {
            System.err.println("Error in " + this.getClass().toString() + " loading profile select fxml file");
            System.err.println(e.getCause());
        }
    }
    
    public void loadUserButton(GridPane gridPane, int col, int row, List<User> users, int index, VBox vboxBody, ViewManager viewManager) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistas/frmPS_Btn.fxml"));
            gridPane.add((Node) fxmlLoader.load(), col, row);
            PS_BtnController ps_BtnController = fxmlLoader.getController();
            ps_BtnController.setUserOwner(users.get(index));
            ps_BtnController.setText();
            ps_BtnController.setImage();
            ps_BtnController.setVboxBody(vboxBody);
            ps_BtnController.setViewManager(viewManager);
        } catch (IOException e) {
            System.err.println("Error in " + this.getClass().toString() + " loading user button fxml file");
            System.err.println(e.getMessage());
        }
    }
    
    public void loadAddButton (GridPane gridPane, int col, int row, VBox vboxBody, ViewManager viewManager) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistas/frmPS_Add.fxml"));
            gridPane.add((Node) fxmlLoader.load(), col, row);
            PS_AddController ps_AddController = fxmlLoader.getController();
            ps_AddController.setVboxBody(vboxBody);
            ps_AddController.setViewManager(viewManager);
            
        } catch (IOException ex) {
            System.err.println("Error in " + this.getClass().toString() + " loading profile creator fxml file");
            System.err.println(ex.getCause());
        }
    }
    
    /*PROFILE CREATOR*/
    public void loadProfileCreator(VBox vboxBody, ViewManager viewManager, User userOwner, boolean updating) {
        vboxBody.getChildren().clear();
        viewManager.setStatus(ViewStatus.OTHER);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistas/frmProfileCreator.fxml"));
            vboxBody.getChildren().add(fxmlLoader.load());
            ProfileCreatorController profileCreatorController = fxmlLoader.getController();
            profileCreatorController.setUserOwner(userOwner);
            profileCreatorController.setVboxBody(vboxBody);
            profileCreatorController.setViewManager(viewManager);
            profileCreatorController.initComponents(updating);
        } catch (IOException e) {
            System.err.println("Error in " + this.getClass().toString() + " loading profile creator fxml file");
            System.err.println(e.getCause());
        }
    }
    
    public void loadProfileCreator(VBox vboxBody, ViewManager viewManager) {
        loadProfileCreator(vboxBody, viewManager, null, false);
    }
    
    public void loadProfileCreatorUserAccountsCards(FlowPane flowPaneAccounts, Account account) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistas/frmPC_Account_Card.fxml"));
            flowPaneAccounts.getChildren().add(fxmlLoader.load());
            PC_Account_CardController pc_Account_CardController = fxmlLoader.getController();
            pc_Account_CardController.setAccountOwner(account);
            pc_Account_CardController.setName();
            pc_Account_CardController.setImage();
        } catch (IOException e) {
            System.err.println("Error in " + this.getClass().toString() + " failed chargin accounts cards");
        }
    }
    
    public void backToProfileSelect(VBox vboxBody, ViewManager viewManager) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistas/frmProfileSelect.fxml"));
            vboxBody.getChildren().add(fxmlLoader.load());
            ProfileSelectController profileSelectController = fxmlLoader.getController();
            profileSelectController.setVboxBody(vboxBody);
            profileSelectController.setViewManager(viewManager);
            profileSelectController.showUsers();
        } catch (IOException e) {
            System.err.println("Error in " + this.getClass().toString() + " loading profile select fxml file");
        }
    }
    
    /*USER INFO VIEWS*/
    public void loadUserInfo(VBox vboxBody, ViewManager viewManager, User userOwner) {
        vboxBody.getChildren().clear();
        viewManager.setStatus(ViewStatus.OTHER);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistas/frmUserInfo.fxml"));
            vboxBody.getChildren().add(fxmlLoader.load());
            UserInfoController userInfoController = fxmlLoader.getController();
            userInfoController.setVboxBody(vboxBody);
            userInfoController.setUserOwner(userOwner);
            userInfoController.setViewManager(viewManager);
            userInfoController.initComponents();
        } catch (IOException e) {
            System.err.println("Error in " + this.getClass().toString() + " loading user info fxml file");
            System.err.println(e.getCause());
        }
    }
    
    public void loadUserInfoAccountCards(VBox vboxScrollBody, Account account) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistas/frmUF_Account_Card.fxml"));
            vboxScrollBody.getChildren().add(fxmlLoader.load());
            UF_Account_CardController uf_Account_CardController = fxmlLoader.getController();
            uf_Account_CardController.setAccountOwner(account);
        } catch (IOException e) {
            System.err.println("Error in " + this.getClass().toString() + " loading user info card fxml file");
            System.err.println(e.getCause());
        }
    }
    
    public void loadUserInfoAccountAddButton(VBox vboxScrollBody) {
        try{
            FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/vistas/frmUF_Account_AddBtn.fxml"));
            vboxScrollBody.getChildren().add(fXMLLoader.load());
        } catch (IOException e) {
            System.err.println("Error in " + this.getClass().toString() + " loading account add button fxml file");
            System.err.println(e.getCause());
        }
    }
    
    /*ACCOUNT CREATOR VIEWS*/    
    public void loadAccountCreator(Account accountOwner) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistas/frmAccountCreator.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(fxmlLoader.load()));
            AccountCreatorController acController = fxmlLoader.getController();
            acController.setAccountOwner(accountOwner);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setAlwaysOnTop(true);
            stage.showAndWait();
        } catch (IOException e) {
            System.err.println("Error in " + this.getClass().toString() + " loading account creator fxml file");
            System.err.println(e.getCause());
        }
    }
    
    public void loadAccountCreator() {
        loadAccountCreator(null);
    }
    
    public void loadAccountCreatorSocialNetworks(VBox vboxScrollBody, SocialNetwork socialNetwork) {
        FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/vistas/frmAC_Tab_Btn.fxml"));
        try {
            vboxScrollBody.getChildren().add(fXMLLoader.load());
            AC_Tab_BtnController ac_Tab_BtnController = fXMLLoader.getController();
            ac_Tab_BtnController.setSocialNetworkOwner(socialNetwork);
        } catch (IOException e) {
            System.err.println("Error in " + this.getClass().toString() + " loading social network card fxml file");
            System.err.println(e.getCause());
        }
    }
    
    /*SOCIAL NETWORKS*/
    public void loadSocialNetworks(VBox vbBody, ViewManager viewManager) {
        vbBody.getChildren().clear();
        try {
            vbBody.getChildren().add(FXMLLoader.load(getClass().getResource("/vistas/frmSocialNetworks.fxml")));
        } catch (IOException e) {
            System.err.println("Error in " + this.getClass().toString() + " loading social networks fxml file");
            System.err.println(e.getCause());
        }
    }
    
    public void loadSocialNetworksAccounts(VBox vBoxScrollBody, SocialNetwork socialNetwork) {
        try {
            FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/vistas/frmSN_Card.fxml"));
            vBoxScrollBody.getChildren().add(fXMLLoader.load());
            SN_CardController sN_CardController = fXMLLoader.getController();
            sN_CardController.setSocialNetworkOwner(socialNetwork);
            sN_CardController.setText();
            sN_CardController.setImage();
        } catch (IOException e) {
            System.err.println("Error in " + this.getClass().toString() + " failed chargin social networks cards");
        }
    }
    
    public void loadSocialNetworkCreator(SocialNetwork socialNetworkOwner){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistas/frmSocialNetworkCreator.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(fxmlLoader.load()));
            SocialNetworkCreatorController snCreatorController = fxmlLoader.getController();
            snCreatorController.setSocialNetworkOwner(socialNetworkOwner);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setAlwaysOnTop(true);
            stage.showAndWait();
        } catch (IOException ex) {
            System.err.println("Error in " + this.getClass().toString() + " loading social network creator fxml file");
            System.err.println(ex.getCause());
        }
    }
    
    public void loadSocialNetworkCreator(){
        loadSocialNetworkCreator(null);
    }
    
    /*SETTINGS*/
    public void loadSettings(VBox vbBody) {
        vbBody.getChildren().clear();
        try {
            vbBody.getChildren().add(FXMLLoader.load(getClass().getResource("/vistas/frmSettings.fxml")));
        } catch (IOException e) {
            System.err.println("Error in " + this.getClass().toString() + " loading settings fxml file");
            System.err.println(e.getCause());
        }
    }
    
    //CONFIRMATION WINDOWS
    
    /*USER PRIVATE PASS*/
    public boolean loadUserPassConfirm() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistas/frmPrivPassConfirm.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(fxmlLoader.load()));
            PrivPassConfirmController passConfirmController = fxmlLoader.getController();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setAlwaysOnTop(true);
            stage.showAndWait();
            
            return passConfirmController.getResult();
        } catch (IOException e) {
            System.err.println("Error in " + this.getClass().toString() + " loading private pass confirmation fxml file");
            System.err.println(e.getCause());
        }
        return false;
    }
    
    /*DELETION CONFIRMATION*/
    public boolean loadDeletionConfirm(String type, String element) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistas/frmConfirmDel.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(fxmlLoader.load()));
            
            ConfirmDelController confirmDelController = fxmlLoader.getController();
            confirmDelController.setType(type);
            confirmDelController.setElement(element);            
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setAlwaysOnTop(true);
            stage.showAndWait();
            
            return confirmDelController.getResult();
        } catch (IOException e) {
            System.err.println("Error in " + this.getClass().toString() + " loading deletion confirmation fxml file");
            System.err.println(e.getCause());
            return false;
        }
    }
    
    /*ADMIN PASS CONFIRMATION VIEWS*/
    public boolean loadAdminPassConfirm() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistas/frmAdminAccessPassConfirm.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setAlwaysOnTop(true);
            stage.showAndWait();
            AdminAccessPassConfirmController adminAccessPassConfirmController = fxmlLoader.getController();
            
            return adminAccessPassConfirmController.getResult();
        } catch (IOException ex) {
            System.err.println("Error in " + this.getClass().toString() + " loading admin access pass fxml file");
            System.err.println(ex.getCause());
            return false;
        }
    }
    
    public boolean loadAdminNewPassConfirm() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistas/frmAdminNewPassConfirm.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setAlwaysOnTop(true);
            stage.showAndWait();
            AdminNewPassConfirmController adminNewPassController = fxmlLoader.getController();
            return adminNewPassController.getResult();
        } catch (IOException ex) {
            System.err.println("Error in " + this.getClass().toString() + " loading admin new pass fxml file");
            System.err.println(ex.getCause());
            return false;
        }
    }
    
    public boolean loadAdminUpdatePassConfirm() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistas/frmAdminUpdatePassConfirm.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(fxmlLoader.load()));            
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setAlwaysOnTop(true);
            stage.showAndWait();
            AdminUpdatePassConfirmController adminUpdatePassController = fxmlLoader.getController();
            return adminUpdatePassController.getResult();
        } catch (IOException ex) {
            System.err.println("Error in " + this.getClass().toString() + " loading admin update pass fxml file");
            System.err.println(ex.getCause());
            return false;
        }
    }
}