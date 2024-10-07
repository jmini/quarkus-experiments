package superheroes.model;

import java.util.Objects;

import org.eclipse.microprofile.graphql.Name;

/**
 * A general attribute that a superhero possesses
 */
@Name("Attribute")
public class Attribute implements Ability {

    /**
     * Explanation why the rating is given
     */
    private String motivation;
    /**
     * Attribute name (e.g. intelligence, strength, speed ...) that describes a superhero
     */
    private String name;
    /**
     * Value from 0-100 indicating their rating for that particular attribute
     */
    private Integer rating;

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public int hashCode() {
        return Objects.hash(motivation, name, rating);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Attribute other = (Attribute) obj;
        return Objects.equals(motivation, other.motivation)
                && Objects.equals(name, other.name)
                && Objects.equals(rating, other.rating);
    }

    @Override
    public String toString() {
        return "Attribute [motivation=" + motivation + ", name=" + name + ", rating=" + rating + "]";
    }
}
