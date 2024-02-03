package CSC340.example.Assignment2;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class LukeLeongApplication {

	public static void main(String[] args) {
		SpringApplication.run(LukeLeongApplication.class, args);
		getStarWarsMovieInfo();
		System.exit(0);
	}

	/**
	 * This method returns info about ROTS
	 */
	public static void getStarWarsMovieInfo() {
    	 try {
             String url = "https://swapi.dev/api/films/6";
             RestTemplate restTemplate = new RestTemplate();
             ObjectMapper mapper = new ObjectMapper();

             String jSonInfo = restTemplate.getForObject(url, String.class);
             JsonNode root = mapper.readTree(jSonInfo);

             String Title = root.findValue("title").asText();
             String Director = root.findValue("director").asText();
             String release_date = root.findValue("release_date").asText();
             String opening_crawl = root.findValue("opening_crawl").asText();
             
             System.out.println("**********Revenge of the Sith********\n");
             System.out.println(Title + " is a Star Wars movie directed by " + Director);
             System.out.println("\nIt was released " + release_date);
             System.out.println("\nIt starts with the opening: \n" + opening_crawl);

         } catch (JsonProcessingException ex) {
             Logger.getLogger(LukeLeongApplication.class.getName()).log(
                     Level.SEVERE,
                     null, ex);
             System.out.println("error in getStarWarsMovieInfo");

         }
    }
}
