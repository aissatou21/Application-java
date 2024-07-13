package webservice.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebService
public class UserService {
    private List<User> users = new ArrayList<>();
    private String authToken = UUID.randomUUID().toString(); // Génération d'un jeton d'authentification

    public UserService() {
        // Adding a default user for testing
        users.add(new User("TestUser", "password"));
    }

    @WebMethod
    public String getUser(@WebParam(name = "name") String name) {
        for (User user : users) {
            if (user.getName().equals(name)) {
                return "Hello, " + user.getName();
            }
        }
        return "User not found";
    }

    @WebMethod
    public List<User> listUsers(@WebParam(name = "authToken") String token) {
        if (!validateToken(token)) {
            throw new SecurityException("Invalid authentication token");
        }
        return users;
    }

    @WebMethod
    public String addUser(@WebParam(name = "user") User user, @WebParam(name = "authToken") String token) {
        if (!validateToken(token)) {
            throw new SecurityException("Invalid authentication token");
        }
        users.add(user);
        return "User added: " + user.getName();
    }

    @WebMethod
    public String deleteUser(@WebParam(name = "name") String name, @WebParam(name = "authToken") String token) {
        if (!validateToken(token)) {
            throw new SecurityException("Invalid authentication token");
        }
        for (User user : users) {
            if (user.getName().equals(name)) {
                users.remove(user);
                return "User deleted: " + name;
            }
        }
        return "User not found";
    }

    @WebMethod
    public String updateUser(@WebParam(name = "user") User user, @WebParam(name = "authToken") String token) {
        if (!validateToken(token)) {
            throw new SecurityException("Invalid authentication token");
        }
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getName().equals(user.getName())) {
                users.set(i, user);
                return "User updated: " + user.getName();
            }
        }
        return "User not found";
    }

    @WebMethod
    public String authenticate(@WebParam(name = "name") String name, @WebParam(name = "password") String password) {
        for (User user : users) {
            if (user.getName().equals(name) && user.getPassword().equals(password)) {
                return "Authentication successful. Token: " + authToken;
            }
        }
        return "Authentication failed";
    }

    @WebMethod
    public String getAuthToken() {
        return authToken;
    }

    private boolean validateToken(String token) {
        return authToken.equals(token);
    }
}
