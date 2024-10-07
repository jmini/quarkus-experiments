package superheroes.model;

import org.eclipse.microprofile.graphql.Name;

import io.smallrye.graphql.api.Union;
import jakarta.json.bind.annotation.JsonbSubtype;
import jakarta.json.bind.annotation.JsonbTypeInfo;

/**
 * An ability that a superhero possesses, which can be either a superpower or a general attribute
 */
@Union
@JsonbTypeInfo(key = "__typename", value = {
        @JsonbSubtype(alias = "Attribute", type = Attribute.class),
        @JsonbSubtype(alias = "Superpower", type = Superpower.class)
})
@Name("Ability")
public interface Ability {
}
