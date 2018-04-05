package app.controllers;

import app.entities.Game;
import app.entities.User;

import java.util.HashSet;
import java.util.Set;


public class UserSession {

    private User currentUser;

    private boolean isCurrentUserAdmin;

    private boolean isCurrentUserLoggedIn;

    private Set<Game> shoppingCart;

    public UserSession() {
        this.shoppingCart = new HashSet<>();
    }

    public void login(User user){
        this.setCurrentUser(user);
        this.setCurrentUserLoggedIn(true);
        this.setCurrentUserAdmin(user.isAdmin());
    }

    public void logout(){
        this.setCurrentUser(null);
        this.setCurrentUserLoggedIn(false);
        this.setCurrentUserAdmin(false);
    }

    public User getCurrentUser() {
        return currentUser;
    }

    private void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public boolean isCurrentUserAdmin() {
        return this.isCurrentUserLoggedIn() && this.currentUser.isAdmin();
    }

    private void setCurrentUserAdmin(boolean currentUserAdmin) {
        isCurrentUserAdmin = currentUserAdmin;
    }

    public boolean isCurrentUserLoggedIn() {
        return isCurrentUserLoggedIn;
    }

    private void setCurrentUserLoggedIn(boolean currentUserLoggedIn) {
        isCurrentUserLoggedIn = currentUserLoggedIn;
    }

    public boolean addItemToShoppingCart(Game game){
        return this.shoppingCart.add(game);
    }

    public boolean deleteItemFromShoppingCart(Game game){
        return this.shoppingCart.remove(game);
    }

    public void clearShoppingCart(){
        this.shoppingCart.clear();
    }

    public Set<Game> getShoppingCart() {
        return shoppingCart;
    }
}
