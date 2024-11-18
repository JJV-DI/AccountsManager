package model;

import javafx.collections.ObservableList;

public interface U_DAO {
    public ObservableList<User> loadUsersFromDB();
}
