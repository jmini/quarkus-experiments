package rest;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import converter.PersonService;
import converter.Person;
import io.quarkiverse.renarde.Controller;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import swapi.SwapiService;

/**
 * This defines a REST controller, each method will be available under the "Classname/method" URI by convention
 */
public class Ui extends Controller {

    @Inject
    PersonService peopleService;

    /**
     * This defines templates available in src/main/resources/templates/Classname/method.html by convention
     */
    @CheckedTemplate
    public static class Templates {
        /**
         * This specifies that the Ui/index.html template does not take any parameter
         */
        public static native TemplateInstance index();
        /**
         * This specifies that the Ui/people.html template takes a Ui parameter of type List&lt;Todo&gt;
         */
        public static native TemplateInstance people(Person p);
    }

    // This overrides the convention and makes this method available at "/renarde"
    @Path("/")
    public TemplateInstance index() {
        // renders the Ui/index.html template
        return Templates.index();
    }

    @Path("/{id}")
    public TemplateInstance people(@PathParam(value = "id") int id) {
        Person p = peopleService.getPerson(id);
        return Templates.people(p);
    }
    
}