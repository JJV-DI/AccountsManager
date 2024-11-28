package model;

import javafx.collections.ObservableList;

public interface SN_DAO {
    public ObservableList<SocialNetwork> loadSocialNetworks();
    public void insertSocialNetwork(SocialNetwork socialNetwork);
    public void updateSocialNetwork(SocialNetwork socialNetwork);
    public void deleteSocialNetwork(SocialNetwork socialNetwork);
}
