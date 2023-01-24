package app.session;

import app.domain.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class CurrentUser {

    private Long id;

    private String username;

    public CurrentUser() {
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
