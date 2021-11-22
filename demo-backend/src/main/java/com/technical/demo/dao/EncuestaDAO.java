package com.technical.demo.dao;

import com.technical.demo.beans.dto.EncuestaDTO;
import com.technical.demo.beans.dto.ResultadoEncuestaDTO;
import com.technical.demo.beans.request.EncuestaRequest;

import java.util.List;

public interface EncuestaDAO {

    List<ResultadoEncuestaDTO> getVotosPorMusicaTipo();

    Integer agregarEncuesta(EncuestaRequest encuestaRequest);

    List<EncuestaDTO> getEncuestaPorEmail(String email);
}
