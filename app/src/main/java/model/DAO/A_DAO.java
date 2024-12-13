package model.DAO;

import javafx.collections.ObservableList;
import model.Account;
import model.User;

public interface A_DAO {
    public ObservableList<Account> loadAccountsFromDB(User user);
    public ObservableList<Account> loadAccountsByName(String accountName, User user);
    public ObservableList<Account> loadAccountsBySN(String snName, User user);
    public void insertAccount(Account account, User user);
    public void updateAccount(Account newAccount, Account oldAccount, User user);
    public void deleteAccount(Account account, User user);
}
