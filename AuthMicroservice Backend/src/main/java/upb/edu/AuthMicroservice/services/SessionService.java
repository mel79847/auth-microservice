package upb.edu.AuthMicroservice.services;

import java.util.Optional;
import java.util.UUID;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upb.edu.AuthMicroservice.interactors.RefreshTokenInteractor;
import upb.edu.AuthMicroservice.interactors.SessionInteractor;

import upb.edu.AuthMicroservice.exceptions.InvalidRefreshTokenException;
import upb.edu.AuthMicroservice.exceptions.InvalidSessionException;

import upb.edu.AuthMicroservice.models.Session;
import upb.edu.AuthMicroservice.repositories.SessionRepository;
import upb.edu.AuthMicroservice.repositories.UserRepository;


@Service
public class SessionService {

    @Autowired
    private SessionInteractor interactor;

    @Autowired

    private RefreshTokenInteractor refreshTokenInteractor;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository sessionRepository;

    public static class SessionCreationResult {
        private final UUID sessionId;
        private final UUID accessToken;
        private final UUID refreshToken;

        public SessionCreationResult(UUID sessionId, UUID accessToken, UUID refreshToken) {
            this.sessionId = sessionId;
            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
        }
        public UUID getSessionId() { return sessionId; }
        public UUID getAccessToken() { return accessToken; }
        public UUID getRefreshToken() { return refreshToken; }
    }

    public SessionCreationResult generateSession(int userId) {
        if (userId <= 0) {
            throw new IllegalArgumentException("El user_id proporcionado no es válido o no existe");
        }
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("El user_id proporcionado no es válido o no existe");
        }

        UUID sessionId = interactor.execute(userId);

        Optional<Session> maybe = sessionRepository.findById(sessionId);
        if (maybe.isEmpty()) {
            throw new RuntimeException("La sesión fue creada pero no se pudo recuperar");
        }
        Session created = maybe.get();

        UUID refreshToken = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();
        created.setRefreshToken(refreshToken);
        created.setRefreshTokenExpiresAt(now.plusDays(7));

        sessionRepository.save(created);

        return new SessionCreationResult(created.getId(), created.getAccessToken(), created.getRefreshToken());
    }

    public Optional<Session> getSessionById(UUID sessionId) {
        return interactor.findById(sessionId);
    }

    public String refreshAccessToken(String refreshToken) {
        UUID rt;
        try {
            rt = UUID.fromString(refreshToken);
        } catch (Exception e) {
            throw new IllegalArgumentException("refresh_token no es un UUID válido");
        }
        try {
            UUID newAccess = refreshTokenInteractor.execute(rt, 15);
            if (newAccess == null) {
                throw new InvalidRefreshTokenException("Refresh token inválido o expirado");
            }
            return newAccess.toString();
        } catch (IllegalStateException ex) {
            if ("SESSION_INVALID".equals(ex.getMessage())) {
                throw new InvalidSessionException("La sesión asociada no es válida");
            }
            throw ex;
        }
    }
}