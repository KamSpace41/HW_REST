// UserRepository.java
package com.example.demo.repository;

import com.example.demo.permissions.Authorities;
import org.springframework.stereotype.Repository;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository {

    private final ConcurrentHashMap<String, UserCredentials> users = new ConcurrentHashMap<>();

    public UserRepository() {

        users.put("admin", new UserCredentials("admin123",
                Arrays.asList(Authorities.READ, Authorities.WRITE, Authorities.DELETE)));
        users.put("user", new UserCredentials("user123",
                Arrays.asList(Authorities.READ)));
        users.put("guest", new UserCredentials("guest123",
                Arrays.asList()));
    }

    public List<Authorities> getUserAuthorities(String user, String password) {
        UserCredentials credentials = users.get(user);

        if (credentials != null && credentials.getPassword().equals(password)) {
            return credentials.getAuthorities();
        }

        return null;
    }


    private static class UserCredentials {
        private final String password;
        private final List<Authorities> authorities;

        public UserCredentials(String password, List<Authorities> authorities) {
            this.password = password;
            this.authorities = authorities;
        }

        public String getPassword() {
            return password;
        }

        public List<Authorities> getAuthorities() {
            return authorities;
        }
    }
}