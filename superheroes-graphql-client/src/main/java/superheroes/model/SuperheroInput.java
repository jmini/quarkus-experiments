package superheroes.model;

import java.util.Objects;

import org.eclipse.microprofile.graphql.Name;

/**
 * Representation of a Superhero
 */
@Name("SuperheroInput")
public class SuperheroInput {

    /**
     * The city where the superhero is based or operates
     */
    private CityInput city;
    /**
     * The gender of the superhero
     */
    private Gender gender;
    /**
     * The name of the superhero
     */
    private String name;

    public CityInput getCity() {
        return city;
    }

    public void setCity(CityInput city) {
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
        return Objects.hash(city, gender, name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        SuperheroInput other = (SuperheroInput) obj;
        return Objects.equals(city, other.city)
                && Objects.equals(gender, other.gender)
                && Objects.equals(name, other.name);
    }

    @Override
    public String toString() {
        return "SuperheroInput [city=" + city + ", gender=" + gender + ", name=" + name + "]";
    }
}
