package superheroes.model;

import java.util.Objects;

import org.eclipse.microprofile.graphql.Name;

/**
 * A superpower that a superhero possesses
 */
@Name("Superpower")
public class Superpower implements Ability {

    /**
     * The name of the superpower that a superherow has
     */
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Superpower other = (Superpower) obj;
        return Objects.equals(name, other.name);
    }

    @Override
    public String toString() {
        return "Superpower [name=" + name + "]";
    }
}
