package venus.logic.service;

import venus.dal.model.User;

public interface SecurityService {
    User getUser();
    Boolean isAuthenticated();
    String getUsername();
}
