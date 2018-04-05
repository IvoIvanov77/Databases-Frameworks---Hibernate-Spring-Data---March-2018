package app.terminal;


import app.controllers.GameController;
import app.controllers.OrderController;
import app.controllers.UserController;
import app.entities.User;
import app.repositories.GameRepository;
import app.repositories.OrderRepository;
import app.repositories.UserRepository;
import app.services.contracts.GameService;
import app.services.contracts.OrderService;
import app.services.contracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.jws.soap.SOAPBinding;

//@Component
public class Tester implements CommandLineRunner {


    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final GameService gameService;
    private final OrderService orderService;
    private final UserController userController;
    private final GameController gameController;
    private final OrderController orderController;

    @Autowired
    public Tester(UserRepository userRepository, GameRepository gameRepository,
                  OrderRepository orderRepository, UserService userService,
                  GameService gameService, OrderService orderService,
                  UserController userController, GameController gameController,
                  OrderController orderController) {
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.gameService = gameService;
        this.orderService = orderService;
        this.userController = userController;
        this.gameController = gameController;
        this.orderController = orderController;
    }


    @Override
    public void run(String... strings) throws Exception {
        User user = this.userRepository.getOneWithGames(1L);
        System.out.println(user);
    }



}
