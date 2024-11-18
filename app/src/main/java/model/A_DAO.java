package model;

import javafx.collections.ObservableList;

public interface A_DAO {
    public ObservableList<Account> loadAccountsFromDB(User user);
}
