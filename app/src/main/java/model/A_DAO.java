/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model;

import javafx.collections.ObservableList;

/**
 *
 * @author pepea
 */
public interface A_DAO {
    public ObservableList<Account> loadAccountsFromDB(User user);
}
