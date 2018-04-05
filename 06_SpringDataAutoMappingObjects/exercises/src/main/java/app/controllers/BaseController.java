package app.controllers;

public class BaseController {


    protected static UserSession userSession;


    protected BaseController() {
        if(userSession == null){
            userSession = new UserSession();
        }
    }


}
