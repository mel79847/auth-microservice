package upb.edu.AuthMicroservice.routes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import upb.edu.AuthMicroservice.controllers.UserController;
import upb.edu.AuthMicroservice.models.Response;
import upb.edu.AuthMicroservice.models.User;
import upb.edu.AuthMicroservice.controllers.RedisController;


import static org.springframework.web.servlet.function.RouterFunctions.route;
import java.util.Map;

@Configuration
public class Routes {

    @Autowired
    private UserController userController;
    @Autowired
    private RedisController redisController;


    @Bean
    public RouterFunction<ServerResponse> userRoutes() {
        return route()
                .POST("/register-user", request -> {
                    User user = request.body(User.class);
                    userController.registerUser(user);
                    return ServerResponse.ok().body(new Response("201", "OK"));
                })
                .build();
    }
}