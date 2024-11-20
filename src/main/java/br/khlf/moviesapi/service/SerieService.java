package br.khlf.moviesapi.service;

import br.khlf.moviesapi.dto.EpisodioDTO;
import br.khlf.moviesapi.dto.SerieDTO;
import br.khlf.moviesapi.model.Categoria;
import br.khlf.moviesapi.model.Serie;
import br.khlf.moviesapi.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SerieService {

    public List<SerieDTO> getAiring(){
        return  converter(serieRepository.encontrarEpisodiosMaisRecentes());
    }

    @Autowired
    private SerieRepository serieRepository;

    public List<SerieDTO> allSeries(){
        return converter(serieRepository.findAll());
    }

    public List<SerieDTO> topFive() {
        return  converter(serieRepository.findTop5ByOrderByAvaliacaoDesc());
    }

    private List<SerieDTO> converter(List<Serie> series) {
        return series.stream()
                .map(s -> new SerieDTO(s.getId(), s.getTitulo(), s.getTotalTemporadas(), s.getAvaliacao(),
                        s.getGenero(), s.getAtores(), s.getPoster(), s.getSinopse()))
                .collect(Collectors.toList());
    }

    public SerieDTO getById(Long id) {
        Optional<Serie> serie = serieRepository.findById(id);

        if (serie.isPresent()) {
            Serie s = serie.get();
            return new SerieDTO(s.getId(),
                    s.getTitulo(), s.getTotalTemporadas(), s.getAvaliacao(), s.getGenero(),
                    s.getAtores(), s.getPoster(), s.getSinopse());
        }
        return null;
    }

    public List<EpisodioDTO> getTemp(Long id) {
        Optional<Serie> serie = serieRepository.findById(id);

        if (serie.isPresent()) {
            Serie s = serie.get();
            return s.getEpisodios().stream()
                    .map(e -> new EpisodioDTO(e.getTemporada(), e.getNumeroEpisodio(), e.getTitulo()))
                    .collect(Collectors.toList());
        }
        return null;
    }

    public List<EpisodioDTO> getTempIndex(Long id, Long index) {
        return  serieRepository.obterEpisodiosPorTemporada(id, index).stream()
                .map(e -> new EpisodioDTO(e.getTemporada(), e.getNumeroEpisodio(), e.getTitulo()))
                .collect(Collectors.toList());
    }

    public List<SerieDTO> getCategoria(String nomeGenero) {
        Categoria categoria = Categoria.fromPortugues(nomeGenero);
        return converter(serieRepository.findByGenero(categoria));
    }
}
