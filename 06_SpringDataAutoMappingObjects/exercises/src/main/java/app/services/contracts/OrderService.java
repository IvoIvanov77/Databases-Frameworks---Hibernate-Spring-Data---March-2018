package app.services.contracts;

import app.controllers.UserSession;

public interface OrderService {
    String createOrder(UserSession userSession);
}
