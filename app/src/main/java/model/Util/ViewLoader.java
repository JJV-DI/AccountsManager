package model.Util;

import app.MainApp;
import controllers.AC_Tab_BtnController;
import controllers.AccountCreatorController;
import controllers.AdminAccessPassConfirmController;
import controllers.AdminNewPassConfirmController;
import controllers.AdminUpdatePassConfirmController;
import controllers.ConfirmDelController;
import controllers.MainAppController;
import controllers.PC_Account_CardController;
import controllers.PS_AddController;
import controllers.PS_BtnController;
import controllers.PrivPassConfirmController;
import controllers.ProfileCreatorController;
import controllers.ProfileSelectController;
import controllers.ReportAuxDisplayController;
import controllers.SN_CardController;
import controllers.SocialNetworkController;
import controllers.SocialNetworkCreatorController;
import controllers.UF_Account_AddBtnController;
import controllers.UF_Account_CardController;
import controllers.UserInfoController;
import controllers.UsersGuideDisplayController;
import java.io.IOException;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Account;
import model.SocialNetwork;
import model.User;

public class ViewLoader {
    /*MAIN APP*/
    public Parent loadMainApp() {
        try {
            FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/vistas/frmMainApp.fxml"));
            Parent parent = fXMLLoader.load();
            MainAppController mainAppController = fXMLLoader.getController();
            mainAppController.initGuideListener();
            return parent;
        } catch (IOException e) {
            System.err.println("Error in " + this.getClass().toString() + " loading main app fxml file");
            System.err.println(e.getCause());
            return null;
        }
    }
    
    
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
    
    public void loadUserButton(GridPane gridPane, int col, int row, List<User> users, int index, VBox vboxBody, ViewManager viewManager, ProfileSelectController psController) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistas/frmPS_Btn.fxml"));
            gridPane.add((Node) fxmlLoader.load(), col, row);
            PS_BtnController ps_BtnController = fxmlLoader.getController();
            ps_BtnController.setUserOwner(users.get(index));
            ps_BtnController.setText();
            ps_BtnController.setImage();
            ps_BtnController.setVboxBody(vboxBody);
            ps_BtnController.setViewManager(viewManager);
            ps_BtnController.setPsController(psController);
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
    
    public void loadUsersNotFoundCard (GridPane gridPane) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistas/frmUserNotFound_Card.fxml"));
            gridPane.add((Node) fxmlLoader.load(), 0, 0, 4, 1);            
        } catch (IOException ex) {
            System.err.println("Error in " + this.getClass().toString() + " loading user not found fxml file");
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
    
    public void loadProfileCreatorUserAccountsCards(FlowPane flowPaneAccounts, Account account, User userOwner, ProfileCreatorController profileCreatorController) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistas/frmPC_Account_Card.fxml"));
            flowPaneAccounts.getChildren().add(fxmlLoader.load());
            PC_Account_CardController pc_Account_CardController = fxmlLoader.getController();
            pc_Account_CardController.setPcController(profileCreatorController);
            pc_Account_CardController.setAccountOwner(account);
            pc_Account_CardController.setUserOwner(userOwner);
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
    
    public void loadUserInfoAccountCards(VBox vboxScrollBody, Account account, User userOwner, UserInfoController userInfoController) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistas/frmUF_Account_Card.fxml"));
            vboxScrollBody.getChildren().add(fxmlLoader.load());
            UF_Account_CardController uf_Account_CardController = fxmlLoader.getController();
            uf_Account_CardController.setUserInfoController(userInfoController);
            uf_Account_CardController.setUserOwner(userOwner);
            uf_Account_CardController.setAccountOwner(account);
        } catch (IOException e) {
            System.err.println("Error in " + this.getClass().toString() + " loading user info card fxml file");
            System.err.println(e.getCause());
        }
    }
    
    public void loadAccountNotFoundCard(VBox vboxScrollBody) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistas/frmAccountNotFound_Card.fxml"));
            vboxScrollBody.getChildren().add(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println("Error in " + this.getClass().toString() + " loading account not found card fxml file");
            System.err.println(e.getCause());
        }
    }
    
    public void loadUserInfoAccountAddButton(VBox vboxScrollBody, User userOwner, UserInfoController userInfoController) {
        try{
            FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/vistas/frmUF_Account_AddBtn.fxml"));
            vboxScrollBody.getChildren().add(fXMLLoader.load());
            UF_Account_AddBtnController uf_A_AddBntController = fXMLLoader.getController();
            uf_A_AddBntController.setUserOwner(userOwner);
            uf_A_AddBntController.setUfController(userInfoController);
        } catch (IOException e) {
            System.err.println("Error in " + this.getClass().toString() + " loading account add button fxml file");
            System.err.println(e.getCause());
        }
    }
    
    /*ACCOUNT CREATOR VIEWS*/    
    public boolean loadAccountCreator(Account accountOwner, User userOwner) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistas/frmAccountCreator.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(fxmlLoader.load()));
            AccountCreatorController acController = fxmlLoader.getController();
            acController.setUserOwner(userOwner);
            acController.setAccountOwner(accountOwner);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.getScene().getStylesheets().add(MainApp.chargeStylesheet());
            stage.showAndWait();
            return acController.getResult();
        } catch (IOException e) {
            System.err.println("Error in " + this.getClass().toString() + " loading account creator fxml file");
            System.err.println(e.getCause());
            return false;
        }
    }
    
    public boolean loadAccountCreator(User userOwner) {
        return loadAccountCreator(null, userOwner);
    }
    
    public AC_Tab_BtnController loadAccountCreatorSocialNetworkCard(VBox vboxScrollBody, SocialNetwork socialNetwork) {
        FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/vistas/frmAC_Tab_Btn.fxml"));
        try {
            vboxScrollBody.getChildren().add(fXMLLoader.load());
            AC_Tab_BtnController ac_Tab_BtnController = fXMLLoader.getController();
            ac_Tab_BtnController.setSocialNetworkOwner(socialNetwork);
            return ac_Tab_BtnController;
        } catch (IOException e) {
            System.err.println("Error in " + this.getClass().toString() + " loading social network card fxml file");
            System.err.println(e.getCause());
            return null;
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
    
    public void loadSocialNetworksCard(VBox vBoxScrollBody, SocialNetwork socialNetwork, SocialNetworkController snController) {
        try {
            FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/vistas/frmSN_Card.fxml"));
            vBoxScrollBody.getChildren().add(fXMLLoader.load());
            SN_CardController sN_CardController = fXMLLoader.getController();
            sN_CardController.setSocialNetworkOwner(socialNetwork);
            sN_CardController.setText();
            sN_CardController.setImage();
            sN_CardController.setSocialNetworkController(snController);
        } catch (IOException e) {
            System.err.println("Error in " + this.getClass().toString() + " failed chargin social networks cards");
        }
    }
    
    public void loadSocialNetworksNotFoundCard(VBox vBoxScrollBody) {
        try {
            FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/vistas/frmSN_NotFound_Card.fxml"));
            vBoxScrollBody.getChildren().add(fXMLLoader.load());
        } catch (IOException e) {
            System.err.println("Error in " + this.getClass().toString() + " failed chargin social networks not found card");
        }
    }
    
    public boolean loadSocialNetworkCreator(SocialNetwork socialNetworkOwner){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistas/frmSocialNetworkCreator.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(fxmlLoader.load()));
            SocialNetworkCreatorController snCreatorController = fxmlLoader.getController();
            snCreatorController.setSocialNetworkOwner(socialNetworkOwner);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.getScene().getStylesheets().add(MainApp.chargeStylesheet());
            stage.showAndWait();
            
            return snCreatorController.getResult();
        } catch (IOException ex) {
            System.err.println("Error in " + this.getClass().toString() + " loading social network creator fxml file");
            System.err.println(ex.getCause());
            return false;
        }
    }
    
    public boolean loadSocialNetworkCreator(){
        return loadSocialNetworkCreator(null);
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
    
    /*REPORTS*/
    public void loadReports(VBox vbBody) {
        vbBody.getChildren().clear();
        try {
            vbBody.getChildren().add(FXMLLoader.load(getClass().getResource("/vistas/frmReports.fxml")));
        } catch (IOException e) {
            System.err.println("Error in " + this.getClass().toString() + " loading reports fxml file");
            System.err.println(e.getCause());
        }
    }
    
    public void loadReportAuxDisplay(String reportPath, String stylePath) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistas/frmReportAuxDisplay.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(fxmlLoader.load()));
            ReportAuxDisplayController reportADController = fxmlLoader.getController();
            reportADController.setStylePath(stylePath);
            reportADController.setReportPath(reportPath);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.getScene().getStylesheets().add(MainApp.chargeStylesheet());
            stage.showAndWait();
        } catch (IOException e) {
            System.err.println("Error in " + this.getClass().toString() + " loading aux report display fxml file");
            System.err.println(e.getCause());
        }
    }
    
    /*USERS GUIDE*/
    public Stage loadUsersGuideDisplay() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistas/frmUsersGuideDisplay.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(fxmlLoader.load()));
            UsersGuideDisplayController usersGuideDisplayController = fxmlLoader.getController();
            usersGuideDisplayController.setStylePath(getWebStyle());
            usersGuideDisplayController.setReportPath("Accounts-Manager-Manual-de-usuario.html");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.getScene().getStylesheets().add(MainApp.chargeStylesheet());
            stage.getIcons().add(new Image("/vistas/media/cur_icons/appIcon.png"));
            stage.show();
            return stage;
        } catch (IOException e) {
            System.err.println("Error in " + this.getClass().toString() + " loading aux report display fxml file");
            System.err.println(e.getCause());
            return null;
        }
    }
    
    private String getWebStyle() {
        switch (new ConfigProvider().loadTheme()) {
            case "Dark theme" -> {
                return getClass().getResource("/vistas/styles/web/webdarktheme.css").toString();
            }
            case "Light theme" -> {
                return getClass().getResource("/vistas/styles/web/weblighttheme.css").toString();
            }
            default -> {
                return null;
            }
        }
    }
    
    //CONFIRMATION WINDOWS
    
    /*USER PRIVATE PASS*/
    public boolean loadUserPassConfirm(User userOwner) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistas/frmPrivPassConfirm.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(fxmlLoader.load()));
            PrivPassConfirmController passConfirmController = fxmlLoader.getController();
            passConfirmController.setUserOwner(userOwner);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.getScene().getStylesheets().add(MainApp.chargeStylesheet());
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
            stage.getScene().getStylesheets().add(MainApp.chargeStylesheet());
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
            stage.getScene().getStylesheets().add(MainApp.chargeStylesheet());
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
            stage.getScene().getStylesheets().add(MainApp.chargeStylesheet());
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
            stage.getScene().getStylesheets().add(MainApp.chargeStylesheet());
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