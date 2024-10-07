package superheroes.model;

import java.util.Objects;

import org.eclipse.microprofile.graphql.Name;

/**
 * City where a superhero is located or operates
 */
@Name("City")
public class City {

    /**
     * The name of the city
     */
    private String name;
    /**
     * A short abbreviation or symbolic representation of the city
     */
    private String symbol;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, symbol);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        City other = (City) obj;
        return Objects.equals(name, other.name) && Objects.equals(symbol, other.symbol);
    }

    @Override
    public String toString() {
        return "City [name=" + name + ", symbol=" + symbol + "]";
    }
}
