package upb.edu.AuthMicroservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import upb.edu.AuthMicroservice.models.Session;
import upb.edu.AuthMicroservice.models.GenerateSessionRequest;
import upb.edu.AuthMicroservice.repositories.SessionRepository;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/")
public class SessionController {

    @Autowired
    private SessionRepository sessionRepository;

    @PostMapping("/generate-session")
    public Map<String, Object> generateSession(@RequestBody GenerateSessionRequest request) {
        Session session = new Session();
        session.setId(UUID.randomUUID());
        session.setUserId(request.getUser_id());
        session.setCreatedAt(LocalDateTime.now());
        session.setExpiresAt(LocalDateTime.now().plusHours(1));
        session.setIsValid(true);

        sessionRepository.save(session);

        Map<String, Object> response = new HashMap<>();
        response.put("code", 201);
        response.put("msg", "Sesión creada exitosamente");
        response.put("session_id", session.getId().toString());

        return response;
    }
}
