package br.khlf.moviesapi.repository;

import br.khlf.moviesapi.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SerieRepository extends JpaRepository<Serie, Long> {
}
