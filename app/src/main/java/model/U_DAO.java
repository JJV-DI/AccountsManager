package model;

import javafx.collections.ObservableList;

public interface U_DAO {
    public ObservableList<User> loadUsersFromDB();
    public void insertUser(User user);
    public void updateUser(User newUser, User oldUser);
    public void deleteUser(User user);
}
