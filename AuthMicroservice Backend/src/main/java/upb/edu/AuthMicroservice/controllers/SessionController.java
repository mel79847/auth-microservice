package upb.edu.AuthMicroservice.controllers;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;
import upb.edu.AuthMicroservice.dtos.GenerateSessionRequest;
import upb.edu.AuthMicroservice.services.SessionService;
import upb.edu.AuthMicroservice.dtos.RefreshTokenRequest;
import upb.edu.AuthMicroservice.exceptions.InvalidRefreshTokenException;
import upb.edu.AuthMicroservice.exceptions.InvalidSessionException;
import upb.edu.AuthMicroservice.models.Response;

import java.util.Map;

@Component
public class SessionController {

    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    public ServerResponse generateSession(ServerRequest request) {
        Map<String, Object> json;
        try {
            json = request.body(Map.class);
        } catch (Exception e) {
            return ServerResponse
                    .badRequest()
                    .body(Map.of(
                            "code", 400,
                            "msg",  "JSON inválido: " + e.getMessage()
                    ));
        }

        GenerateSessionRequest dto = new GenerateSessionRequest();
        try {
            dto.setUser_id((Integer) json.get("user_id"));
        } catch (Exception e) {
            return ServerResponse.badRequest().body(Map.of("code", 400, "msg", "JSON inválido: " + e.getMessage()));
        }
        String sessionId = sessionService.generateSession(dto.getUser_id());

        Map<String, Object> body = Map.of(
                "code",       201,
                "msg",        "Sesión creada exitosamente",
                "session_id", sessionId
        );
        return ServerResponse
                .status(201)
                .body(body);
    }

    public ServerResponse refreshToken(ServerRequest request) {
        Map<String, Object> json;
        try {
            json = request.body(Map.class);
        } catch (Exception e) {
            return ServerResponse.badRequest().body(new Response("400", "JSON inválido: " + e.getMessage()));
        }

        RefreshTokenRequest dto = new RefreshTokenRequest();
        try {
            Object raw = json.get("refresh_token");
            if (raw == null) {
                throw new IllegalArgumentException("Campo 'refresh_token' requerido");
            }
            dto.setRefresh_token(raw.toString());
        } catch (Exception e) {
            return ServerResponse.badRequest().body(new Response("400", "JSON inválido: " + e.getMessage()));
        }
        
        try {
            String newAccess = sessionService.refreshAccessToken(dto.getRefresh_token());
            return ServerResponse.ok().body(Map.of(
                "code", 200,
                "msg", "Token renovado exitosamente",
                "access_token", newAccess));
        } catch (InvalidSessionException ex) {
            return ServerResponse.status(403).body(new Response("403", "La sesión asociada no es válida"));
        } catch (InvalidRefreshTokenException ex) {
            return ServerResponse.status(401).body(new Response("401", "Refresh token inválido o expirado"));
        } catch (IllegalArgumentException ex) {
            return ServerResponse.badRequest().body(new Response("400", "JSON inválido: " + ex.getMessage()));
        }
    }
}