package model.DAO;

import javafx.collections.ObservableList;
import model.SocialNetwork;

public interface SN_DAO {
    public ObservableList<SocialNetwork> loadSocialNetworks();
    public void insertSocialNetwork(SocialNetwork socialNetwork);
    public void updateSocialNetwork(SocialNetwork socialNetwork);
    public void deleteSocialNetwork(SocialNetwork socialNetwork);
}