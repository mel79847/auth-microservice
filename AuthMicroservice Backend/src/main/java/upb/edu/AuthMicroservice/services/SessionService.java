package upb.edu.AuthMicroservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upb.edu.AuthMicroservice.interactors.GenerateSessionInteractor;

@Service
public class SessionService {

    @Autowired
    private GenerateSessionInteractor interactor;

    public String generateSession(int userId) {
        return interactor.execute(userId).toString();
    }
}
