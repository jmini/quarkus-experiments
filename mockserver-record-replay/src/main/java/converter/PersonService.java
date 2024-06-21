package converter;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import jakarta.enterprise.context.ApplicationScoped;
import swapi.SwapiPeople;
import swapi.SwapiService;

@ApplicationScoped
public class PersonService {

	@RestClient
	SwapiService swapiClient;

	public Person getPerson(int id) {
		SwapiPeople people = swapiClient.getPeopleById("" + id);
		return toPerson(people);
	}

	private Person toPerson(SwapiPeople people) {
		Person result = new Person();
		result.name = people.getName();
		result.height = people.getHeight();
		result.mass = people.getMass();
		result.hair_color = people.getHairColor();
		result.skin_color = people.getSkinColor();
		result.eye_color = people.getEyeColor();
		result.birth_year = people.getBirthYear();
		result.gender = people.getGender();
		return result ;
	}
}