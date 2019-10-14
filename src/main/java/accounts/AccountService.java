package accounts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.ClazzUtil;

import java.util.HashMap;
import java.util.Map;

public class AccountService {

    private static final Logger logger = LogManager.getLogger(ClazzUtil.returnClazzName());

    private final Map<String, UserProfile> loginToProfile;
    private final Map<String, UserProfile> sessionIdToProfile;

    public AccountService(){
        this.loginToProfile = new HashMap<>();
        this.sessionIdToProfile = new HashMap<>();
    }

    public void addNewUser(UserProfile userProfile){
        loginToProfile.put(userProfile.getLogin(), userProfile);
        logger.debug("a new user added to the map");
    }

    public UserProfile getUserByLogin(String login){
        return loginToProfile.get(login);
    }

    public void addSession(String sessionId, UserProfile userProfile){
        sessionIdToProfile.put(sessionId, userProfile);
        logger.debug("a new session added to the map");
    }

    public UserProfile getUserBySessionId(String sessionId){
        return sessionIdToProfile.get(sessionId);
    }

    public void deleteSession(String sessionId){
        sessionIdToProfile.remove(sessionId);
    }



}
