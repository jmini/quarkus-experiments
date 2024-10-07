package superheroes.model;

import java.util.List;
import java.util.Objects;

import org.eclipse.microprofile.graphql.Name;

/**
 * Representation of a Superhero
 */
@Name("Superhero")
public class Superhero {

    /**
     * A list of abilities the superhero possesses, which can be superpowers or general attributes
     */
    private List<Ability> abilities;
    /**
     * The city where the superhero is based or operates
     */
    private City city;
    /**
     * The gender of the superhero
     */
    private Gender gender;
    /**
     * The name of the superhero
     */
    private String name;

    public List<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(abilities, city, gender, name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Superhero other = (Superhero) obj;
        return Objects.equals(abilities, other.abilities) && Objects.equals(city, other.city) && Objects.equals(gender, other.gender) && Objects.equals(name, other.name);
    }

    @Override
    public String toString() {
        return "Superhero [abilities=" + abilities + ", city=" + city + ", gender=" + gender + ", name=" + name + "]";
    }

}
