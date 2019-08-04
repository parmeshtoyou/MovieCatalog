package io.javabrains.moviecatalogservice.resource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class MovieCatalogResource {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient.Builder webClientBuilder;

	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		
		UserRating userRating = restTemplate.getForObject("", UserRating.class);
		
		// for each movie id, call movie info service and get details
		return userRating.getUserRating().stream().map(rating -> {
			
			Movie movie = restTemplate.getForObject("http://localhost:3001/movies/" + rating.getMovieId(), Movie.class);
			
			return new CatalogItem(movie.getName(), "Description", rating.getRating());
		}).collect(Collectors.toList());

		// put them all together
	}
	
	/*Movie movie = webClientBuilder.build()
	.get()
	.uri("http://localhost:3001/movies/" + rating.getMovieId())
	.retrieve()
	.bodyToMono(Movie.class) //get the data and convert into Movie object
	.block();
	*/

}
