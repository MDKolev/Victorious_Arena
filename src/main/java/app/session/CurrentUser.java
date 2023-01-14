package app.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class CurrentUser {

    private long id;

    private String username;


    public CurrentUser(long id, String username) {
        this.id = id;
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
