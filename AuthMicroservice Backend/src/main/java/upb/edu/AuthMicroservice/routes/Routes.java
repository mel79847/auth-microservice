package upb.edu.AuthMicroservice.routes;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;
import static org.springframework.web.servlet.function.RouterFunctions.route;

import upb.edu.AuthMicroservice.controllers.UserController;

@Configuration
public class Routes {

    @Bean
    public RouterFunction<ServerResponse> userRoutes(UserController controller) {
        return route()
            .POST("/register-user", controller::registerUser)
            .build();
    }
}
