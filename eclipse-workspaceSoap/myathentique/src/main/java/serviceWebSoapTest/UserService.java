package serviceWebSoapTest;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@WebService(serviceName = "UserService")
public class UserService {
    private List<User> users = new ArrayList<>();
    private String authToken = UUID.randomUUID().toString();

    public UserService() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date birthDate = dateFormat.parse("1990-01-01");
            users.add(new User("John", "Doe", "Male", "Paris", birthDate, "123 Rue de Paris", "pass"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @WebMethod
    public String getUser(@WebParam(name = "name") String name) {
        for (User user : users) {
            if (user.getLastName().equals(name)) {
                return "Hello, " + user.getLastName();
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
        users.add(user);
        return "User added: " + user.getLastName();
    }

    @WebMethod
    public String deleteUser(@WebParam(name = "name") String name, @WebParam(name = "authToken") String token) {
        if (!validateToken(token)) {
            throw new SecurityException("Invalid authentication token");
        }
        for (User user : users) {
            if (user.getLastName().equals(name)) {
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
            if (users.get(i).getLastName().equals(user.getLastName())) {
                users.set(i, user);
                return "User updated: " + user.getLastName();
            }
        }
        return "User not found";
    }

    @WebMethod
    public String authenticate(@WebParam(name = "name") String name, @WebParam(name = "password") String password) {
        for (User user : users) {
            if (user.getLastName().equals(name) && user.getPassword().equals(password)) {
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
