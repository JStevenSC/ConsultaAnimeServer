package com.example.anime.web.Service;

import com.example.anime.domain.dto.Anime;
import com.example.anime.domain.dto.RespuestaTotal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AnimeService {

    private final RestTemplate restTemplate;

    @Autowired
    public AnimeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public RespuestaTotal buscarAnime(String parametro) {
        String apiUrl = "https://api.jikan.moe/v4/anime?q=" + parametro;
        Anime anime = restTemplate.getForObject(apiUrl, Anime.class);

        String[] imageUrl = new String[anime.data.size()];
        String[] title = new String[anime.data.size()];
        String[] malID = new String[anime.data.size()];
        String[] score = new String[anime.data.size()];
        String[] scoreString = new String[anime.data.size()];

        for (int i = 0; i < anime.data.size(); i++) {
            imageUrl[i] = anime.data.get(i).images.jpg.image_url;
            title[i] = anime.data.get(i).title;
            malID[i] = anime.data.get(i).mal_id;
            score[i] = anime.data.get(i).score;

            if (score[i] != null) {
                scoreString[i] = obtenerMensajePuntuacion(Double.valueOf(score[i]));
            } else {
                scoreString[i] = "Sin puntuacion";
            }

        }

        RespuestaTotal respuestaTotal = new RespuestaTotal();
        respuestaTotal.setTitle(title);
        respuestaTotal.setScoreString(scoreString);
        respuestaTotal.setScore(score);
        respuestaTotal.setImagenUrl(imageUrl);
        respuestaTotal.setMailID(malID);
        respuestaTotal.setData(restTemplate.getForObject(apiUrl, Object.class));

        return respuestaTotal;
    }

    private String obtenerMensajePuntuacion(Double puntuacion) {
        String mensaje;
        if (puntuacion >= 1 && puntuacion <= 4) {
            mensaje = "No lo recomiendo.";
        } else if (puntuacion >= 5 && puntuacion <= 7) {
            mensaje = "Puedes divertirte.";
        } else {
            mensaje = "Genial, esto es uno de los mejores animes.";
        }
        return mensaje;
    }
}