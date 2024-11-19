package br.khlf.moviesapi;

import br.khlf.moviesapi.principal.Principal;
import br.khlf.moviesapi.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MoviesapiApplication implements CommandLineRunner {
	@Autowired
	private SerieRepository repositorio;

	public static void main(String[] args) {
		SpringApplication.run(MoviesapiApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repositorio);

		principal.exibeMenu();
	}

}
