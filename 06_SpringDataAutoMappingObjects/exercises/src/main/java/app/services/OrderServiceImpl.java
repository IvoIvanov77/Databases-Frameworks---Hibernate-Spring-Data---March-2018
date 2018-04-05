package app.services;

import app.controllers.UserSession;
import app.entities.Game;
import app.entities.Order;
import app.entities.User;
import app.repositories.OrderRepository;
import app.repositories.UserRepository;
import app.services.contracts.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Override
    public String createOrder(UserSession userSession){
        if(userSession.getShoppingCart().isEmpty()){
            return "Shopping cart is empty";
        }
        User user = userSession.getCurrentUser();
        Set<Game> currentShoppingCart = userSession.getShoppingCart();
        String gamesTitles = String.join(System.lineSeparator() + " -",
                currentShoppingCart.stream()
                        .map(Game::getTitle )
                        .collect(Collectors.toSet()));
        Order order = new Order();
        order.setBuyer(user);
        order.setProducts(currentShoppingCart);
        this.orderRepository.save(order);
        currentShoppingCart.forEach(game -> user.getGames().add(game));
        userSession.clearShoppingCart();
        this.userRepository.save(user);
        return String.format("Successfully bought games: \n -%s" , gamesTitles);
    }
}
