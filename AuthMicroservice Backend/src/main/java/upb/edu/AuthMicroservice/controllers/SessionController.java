package upb.edu.AuthMicroservice.controllers;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;
import upb.edu.AuthMicroservice.dtos.GenerateSessionRequest;
import upb.edu.AuthMicroservice.models.Response;
import upb.edu.AuthMicroservice.services.SessionService;
import upb.edu.AuthMicroservice.dtos.RefreshTokenRequest;
import upb.edu.AuthMicroservice.exceptions.InvalidRefreshTokenException;
import upb.edu.AuthMicroservice.exceptions.InvalidSessionException;

import java.util.Map;
import java.util.UUID;

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
                    .body(new Response("400", "JSON inválido: " + e.getMessage()));
        }

        try {
            SessionService.SessionCreationResult res = sessionService.generateSession(dto.getUser_id());

            Map<String, Object> body = Map.of(
                    "code", 201,
                    "msg", "Sesión creada exitosamente",
                    "session_id", res.getSessionId().toString(),
                    "access_token", res.getAccessToken().toString()
                    "refresh_token", res.getRefreshToken().toString() 
            );
            return ServerResponse.status(201).body(body);

        } catch (IllegalArgumentException e) {
            return ServerResponse.badRequest().body(new Response("400", "El user_id proporcionado no es válido o no existe"));
        } catch (Exception e) {
            return ServerResponse.status(500).body(new Response("500", "Error interno"));
        }
    }
    

    public ServerResponse getSession(ServerRequest request) {
        String idStr = request.pathVariable("id");
        UUID sessionId;
        try {
            sessionId = UUID.fromString(idStr);
        } catch (IllegalArgumentException e) {
            return ServerResponse.badRequest().body(new Response("400", "session_id inválido"));
        }
        return sessionService.getSessionById(sessionId)
                .map(s -> ServerResponse.ok().body(Map.of(
                        "id", s.getId().toString(),
                        "access_token", s.getAccessToken().toString(),
                        "user_id", s.getUserId(),
                        "created_at", s.getCreatedAt().toString(),
                        "expires_at", s.getExpiresAt().toString(),
                        "is_valid", s.isValid()
                )))
                .orElseGet(() -> ServerResponse.status(404).body(new Response("404", "Sesión no encontrada")));
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