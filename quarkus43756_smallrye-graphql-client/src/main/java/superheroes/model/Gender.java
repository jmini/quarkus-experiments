package superheroes.model;

import org.eclipse.microprofile.graphql.Name;

/**
 * Gender of a superhero
 */
@Name("Gender")
public enum Gender {

    /**
     * Female
     */
    FEMALE,
    /**
     * Male
     */
    MALE,
    /**
     * Not applicable or unknown
     */
    NOT_APPLICABLE;

}
