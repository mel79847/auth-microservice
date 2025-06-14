package upb.edu.AuthMicroservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import upb.edu.AuthMicroservice.interactors.RedisInteractor;
import upb.edu.AuthMicroservice.models.Response;

import java.util.Map;

@RestController
public class RedisController {

    @Autowired
    private RedisInteractor redisInteractor;

    @PostMapping("/redis-test")
    public Response saveKeyValue(@RequestBody Map<String, String> payload) {
        String key = payload.get("key");
        String value = payload.get("value");

        redisInteractor.saveKeyValue(key, value);

        return new Response("201", "Valor guardado correctamente");
    }
}
