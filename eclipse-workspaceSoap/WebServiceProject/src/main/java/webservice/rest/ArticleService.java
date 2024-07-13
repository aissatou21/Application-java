package webservice.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/articles")
public class ArticleService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getArticles() {
        return "[{\"title\": \"Article 1\", \"content\": \"Contenu de l'article 1\"}]";
    }
}
