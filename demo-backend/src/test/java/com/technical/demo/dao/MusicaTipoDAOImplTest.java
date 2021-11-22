package com.technical.demo.dao;

import com.technical.demo.beans.dto.MusicaTipoDTO;
import com.technical.demo.beans.dto.ResultadoEncuestaDTO;
import com.technical.demo.dao.impl.EncuestaDAOImpl;
import com.technical.demo.dao.impl.MusicaTipoDAOImpl;
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
public class MusicaTipoDAOImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private MusicaTipoDAOImpl musicaTipoDAO;

    @Test
    public void getMusicaTipoListOK() throws IOException {
        //given
        MusicaTipoDTO musicaTipoDTO = new MusicaTipoDTO();
        musicaTipoDTO.setName("Rock");
        musicaTipoDTO.setPkMusicaTipo(1);
        List<MusicaTipoDTO> list = Collections.singletonList(musicaTipoDTO);
        given(jdbcTemplate.query(anyString(), any(RowMapper.class))).willReturn(list);
        //when
        List<MusicaTipoDTO> result = musicaTipoDAO.getMusicaTipoList();
        //then
        Assertions.assertThat(result.size()).isEqualTo(1);
    }
}
