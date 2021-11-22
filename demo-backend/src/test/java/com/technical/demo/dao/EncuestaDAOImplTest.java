package com.technical.demo.dao;

import com.technical.demo.beans.dto.ResultadoEncuestaDTO;
import com.technical.demo.beans.request.EncuestaRequest;
import com.technical.demo.dao.impl.EncuestaDAOImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
public class EncuestaDAOImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private EncuestaDAOImpl encuestaDAO;

    @Test
    public void getVotosPorMusicaTipoOK() throws IOException {
        //given
        ResultadoEncuestaDTO resultadoEncuestaDTO = new ResultadoEncuestaDTO();
        resultadoEncuestaDTO.setName("Rock");
        resultadoEncuestaDTO.setVotos(23L);
        resultadoEncuestaDTO.setPkMusicaTipo(1);
        List<ResultadoEncuestaDTO> list = Collections.singletonList(resultadoEncuestaDTO);
        given(jdbcTemplate.query(anyString(), any(RowMapper.class))).willReturn(list);
        //when
        List<ResultadoEncuestaDTO> result = encuestaDAO.getVotosPorMusicaTipo();
        //then
        Assertions.assertThat(result.size()).isEqualTo(1);
    }

    @Test
    public void agregarEncuestaOK() {
        //given
        given(jdbcTemplate.update(anyString(), (Object[]) any())).willReturn(1);
        //when
        EncuestaRequest encuestaRequest = new EncuestaRequest();
        encuestaRequest.setEmail("fernando@gmail.com");
        encuestaRequest.setPkMusicaTipo(1);
        Integer result = encuestaDAO.agregarEncuesta(encuestaRequest);
        //then
        Assertions.assertThat(result).isEqualTo(1);
    }
}
