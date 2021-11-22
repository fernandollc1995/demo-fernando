package com.technical.demo.service;

import com.technical.demo.beans.dto.MusicaTipoDTO;
import com.technical.demo.beans.response.Response;
import com.technical.demo.dao.MusicaTipoDAO;
import com.technical.demo.enumeration.EstadosEnum;
import com.technical.demo.service.impl.MusicaServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
public class MusicaServiceImplTest {

    @Mock
    private MusicaTipoDAO musicaTipoDAO;

    @InjectMocks
    private MusicaServiceImpl musicaServiceImpl;

    @Test
    public void getMusicaTipoListOK() throws IOException {
        //given
        MusicaTipoDTO musicaTipo = new MusicaTipoDTO();
        musicaTipo.setPkMusicaTipo(1);
        musicaTipo.setName("Rock");
        List<MusicaTipoDTO> musicaTipoList = Collections.singletonList(musicaTipo);
        given(musicaTipoDAO.getMusicaTipoList()).willReturn(musicaTipoList);
        //when
        Response<List<MusicaTipoDTO>> result = musicaServiceImpl.getMusicaTipoList();
        //then
        verify(musicaTipoDAO).getMusicaTipoList();
        Assertions.assertThat(result.getStatus().getCode()).isEqualTo(EstadosEnum.STATUS_SUCCESSFULL.create().getCode());
        Assertions.assertThat(result.getData().get(0).getPkMusicaTipo()).isEqualTo(1);
        Assertions.assertThat(result.getData().get(0).getName()).isEqualTo("Rock");
        Assertions.assertThat(result.getData().size()).isEqualTo(1);
    }

    @Test
    public void getMusicaTipoListThrowException() throws IOException {
        //given
        given(musicaTipoDAO.getMusicaTipoList()).willThrow(new RuntimeException());
        //when
        Response<List<MusicaTipoDTO>> result = musicaServiceImpl.getMusicaTipoList();
        //then
        Assertions.assertThat(result.getStatus().getCode()).isEqualTo(EstadosEnum.STATUS_FAIL.create().getCode());
    }
}
