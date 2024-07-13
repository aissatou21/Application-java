package serviceWebSoapTest;
import jakarta.xml.ws.Endpoint;


public class SoapServer {

    public static void main(String[] args) {
        String url = "http://localhost:8081/user";
        Endpoint.publish(url, new UserService());
        System.out.println("Serveur SOAP à l'écoute à l'adresse : " + url);
    }
}