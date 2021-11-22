package com.technical.demo.service;

import com.technical.demo.beans.dto.ResultadoEncuestaDTO;
import com.technical.demo.beans.request.EncuestaRequest;
import com.technical.demo.beans.response.Response;

import java.util.List;

public interface EncuestaService {

    Response<List<ResultadoEncuestaDTO>> getVotesPorMusicaTipo();

    Response<Integer> agregarEncuesta(EncuestaRequest encuestaRequest);
}
