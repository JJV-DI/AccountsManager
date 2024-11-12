/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model;

import javafx.collections.ObservableList;

/**
 *
 * @author pepej
 */
public interface U_DAO {
    public ObservableList<User> loadUsersFromDB();
}
