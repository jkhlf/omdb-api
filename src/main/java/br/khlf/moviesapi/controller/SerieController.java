package br.khlf.moviesapi.controller;

import br.khlf.moviesapi.dto.SerieDTO;
import br.khlf.moviesapi.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping("/series")
@RestController
public class SerieController {

    @Autowired
    private SerieService service;

    @GetMapping
    public List<SerieDTO> getSeries() {
        return service.allSeries();

    }

    @GetMapping("/top5")
    public List<SerieDTO> getTopFive() {
        return service.topFive();
    }


    @GetMapping("/lancamentos")
    public  List<SerieDTO> getAiring() {
        return  service.getAiring();
    }

    @GetMapping("/{id}")
    public SerieDTO getById(@PathVariable Long id){
        return service.getById(id);
    }
}