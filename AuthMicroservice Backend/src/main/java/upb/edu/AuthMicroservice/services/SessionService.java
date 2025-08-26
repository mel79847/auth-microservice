package upb.edu.AuthMicroservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upb.edu.AuthMicroservice.interactors.RefreshTokenInteractor;
import upb.edu.AuthMicroservice.interactors.SessionInteractor;
import upb.edu.AuthMicroservice.exceptions.InvalidRefreshTokenException;
import upb.edu.AuthMicroservice.exceptions.InvalidSessionException;

import java.util.UUID;

@Service
public class SessionService {

    @Autowired
    private SessionInteractor interactor;

    @Autowired
    private RefreshTokenInteractor refreshTokenInteractor;

    public String generateSession(int userId) {
        return interactor.execute(userId).toString();
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