package upb.edu.AuthMicroservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upb.edu.AuthMicroservice.dtos.GenerateSessionRequest;
import upb.edu.AuthMicroservice.services.SessionService;

import java.util.Map;

@RestController
@RequestMapping("/")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @PostMapping("/generate-session")
    public ResponseEntity<Map<String, Object>> generateSession(@RequestBody GenerateSessionRequest request) {
        String sessionId = sessionService.generateSession(request.getUser_id());

        return ResponseEntity.status(201).body(
                Map.of(
                        "code", 201,
                        "msg", "Sesión creada exitosamente",
                        "session_id", sessionId
                )
        );
    }
}
