package com.technical.demo.controller;


import com.technical.demo.beans.dto.ResultadoEncuestaDTO;
import com.technical.demo.beans.request.EncuestaRequest;
import com.technical.demo.beans.response.Response;
import com.technical.demo.enumeration.EstadosEnum;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@EnableConfigurationProperties
public class EncuestaControllerTest {

    @Autowired
    private EncuestaController encuestaController;

    @Test
    public void obtenerListaEncuestaOK() {
        Response<List<ResultadoEncuestaDTO>> responseBody = encuestaController.getEncuestas().getBody();
        Assertions.assertThat(responseBody.getStatus().getCode())
                .isEqualTo(EstadosEnum.STATUS_SUCCESSFULL.create().getCode());
        Assertions.assertThat(responseBody.getData().get(0).getName())
                .isEqualTo("Rock");
        Assertions.assertThat(responseBody.getData().get(0).getVotos())
                .isEqualTo(2);
        Assertions.assertThat(responseBody.getData().get(0).getPkMusicaTipo())
                .isEqualTo(1);
    }

    @Test
    public void agregarEncuestaOK() {
        EncuestaRequest encuestaRequest = new EncuestaRequest();
        encuestaRequest.setPkMusicaTipo(2);
        encuestaRequest.setEmail("jose@gmail.com");
        Response<Integer> responseBody = encuestaController.agregarEncuesta(encuestaRequest, null).getBody();
        Assertions.assertThat(responseBody.getStatus().getCode())
                .isEqualTo(EstadosEnum.STATUS_SUCCESSFULL.create().getCode());
        Assertions.assertThat(responseBody.getData())
                .isEqualTo(1);
    }
}
