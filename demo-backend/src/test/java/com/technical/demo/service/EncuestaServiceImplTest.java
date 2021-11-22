package com.technical.demo.service;

import com.technical.demo.beans.dto.EncuestaDTO;
import com.technical.demo.beans.dto.ResultadoEncuestaDTO;
import com.technical.demo.beans.request.EncuestaRequest;
import com.technical.demo.beans.response.Response;
import com.technical.demo.dao.EncuestaDAO;
import com.technical.demo.enumeration.EstadosEnum;
import com.technical.demo.service.impl.EncuestaServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
public class EncuestaServiceImplTest {

    @Mock
    private EncuestaDAO encuestaDAO;

    @InjectMocks
    private EncuestaServiceImpl encuestaService;

    @Captor
    ArgumentCaptor<EncuestaRequest> encuesta;

    @Test
    public void getVotosPorMusicaTipoOK() {
        //given
        ResultadoEncuestaDTO resultadoEncuesta = new ResultadoEncuestaDTO();
        resultadoEncuesta.setVotos(23L);
        resultadoEncuesta.setPkMusicaTipo(1);
        resultadoEncuesta.setName("Rock");
        List<ResultadoEncuestaDTO> resultadoEncuestaList = Collections.singletonList(resultadoEncuesta);
        given(encuestaDAO.getVotosPorMusicaTipo()).willReturn(resultadoEncuestaList);
        //when
        Response<List<ResultadoEncuestaDTO>> result = encuestaService.getVotesPorMusicaTipo();
        //then
        verify(encuestaDAO).getVotosPorMusicaTipo();
        Assertions.assertThat(result.getStatus().getCode()).isEqualTo(EstadosEnum.STATUS_SUCCESSFULL.create().getCode());
        Assertions.assertThat(result.getData().get(0).getPkMusicaTipo()).isEqualTo(1);
        Assertions.assertThat(result.getData().get(0).getName()).isEqualTo("Rock");
        Assertions.assertThat(result.getData().get(0).getVotos()).isEqualTo(23L);
        Assertions.assertThat(result.getData().size()).isEqualTo(1);
    }

    @Test
    public void getVotosPorMusicaTipoThrowException() {
        //given
        given(encuestaDAO.getVotosPorMusicaTipo()).willThrow(new RuntimeException());
        //when
        Response<List<ResultadoEncuestaDTO>> result = encuestaService.getVotesPorMusicaTipo();
        //then
        Assertions.assertThat(result.getStatus().getCode()).isEqualTo(EstadosEnum.STATUS_FAIL.create().getCode());
    }


    @Test
    public void agregarEncuestaOK() {
        //given
        given(encuestaDAO.agregarEncuesta(any())).willReturn(1);
        //when
        EncuestaRequest encuestaRequest = new EncuestaRequest();
        encuestaRequest.setEmail("fernando@gmail.com");
        encuestaRequest.setPkMusicaTipo(1);
        Response<Integer> result = encuestaService.agregarEncuesta(encuestaRequest);
        //then
        verify(encuestaDAO).agregarEncuesta(encuesta.capture());

        Assertions.assertThat(result.getStatus().getCode()).isEqualTo(EstadosEnum.STATUS_SUCCESSFULL.create().getCode());
        Assertions.assertThat(result.getData()).isEqualTo(1);
        Assertions.assertThat(encuesta.getValue().getEmail()).isEqualTo("fernando@gmail.com");
        Assertions.assertThat(encuesta.getValue().getPkMusicaTipo()).isEqualTo(1);
    }

    @Test
    public void agregarEncuestaThrowException() {
        //given
        given(encuestaDAO.agregarEncuesta(any())).willThrow(new RuntimeException());
        //when
        EncuestaRequest encuestaRequest = new EncuestaRequest();
        encuestaRequest.setEmail("fernando@gmail.com");
        encuestaRequest.setPkMusicaTipo(1);
        Response<Integer> result = encuestaService.agregarEncuesta(encuestaRequest);
        //then
        Assertions.assertThat(result.getStatus().getCode()).isEqualTo(EstadosEnum.STATUS_FAIL.create().getCode());
    }

    @Test
    public void agregarEncuestaThrowExceptionPorEmailRepetido() {
        //given
        EncuestaDTO encuestaDTO = new EncuestaDTO();
        List<EncuestaDTO> encuestaDTOList = Collections.singletonList(encuestaDTO);
        given(encuestaDAO.getEncuestaPorEmail(anyString())).willReturn(encuestaDTOList);
        //when
        EncuestaRequest encuestaRequest = new EncuestaRequest();
        encuestaRequest.setEmail("fernando@gmail.com");
        encuestaRequest.setPkMusicaTipo(1);
        Response<Integer> result = encuestaService.agregarEncuesta(encuestaRequest);
        //then
        Assertions.assertThat(result.getStatus().getCode()).isEqualTo(EstadosEnum.EMAIL_USED.create().getCode());
    }
}
