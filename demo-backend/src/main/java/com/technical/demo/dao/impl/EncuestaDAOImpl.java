package com.technical.demo.dao.impl;

import com.technical.demo.beans.dto.EncuestaDTO;
import com.technical.demo.beans.dto.ResultadoEncuestaDTO;
import com.technical.demo.beans.request.EncuestaRequest;
import com.technical.demo.dao.EncuestaDAO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EncuestaDAOImpl implements EncuestaDAO {

    final JdbcTemplate jdbcTemplate;

    public EncuestaDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ResultadoEncuestaDTO> getVotosPorMusicaTipo() {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT DMT.PK_MUSICA_TIPO, DMT.NAME, COUNT(*) VOTOS ");
        builder.append("FROM DEMO_ENCUESTA AS DE ");
        builder.append("INNER JOIN DEMO_MUSICA_TIPO AS DMT ");
        builder.append("ON DE.FK_MUSICA_TIPO = DMT.PK_MUSICA_TIPO ");
        builder.append("GROUP BY DMT.PK_MUSICA_TIPO, DMT.NAME");
        return jdbcTemplate.query(builder.toString(), (rs, rowNum) -> {
            ResultadoEncuestaDTO resultadoEncuestaDTO = new ResultadoEncuestaDTO();
            resultadoEncuestaDTO.setName(rs.getString("name"));
            resultadoEncuestaDTO.setPkMusicaTipo(rs.getInt("pk_musica_tipo"));
            resultadoEncuestaDTO.setVotos(rs.getLong("votos"));
            return resultadoEncuestaDTO;
        });
    }

    @Override
    public Integer agregarEncuesta(EncuestaRequest encuestaRequest) {

        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO DEMO_ENCUESTA(EMAIL, FK_MUSICA_TIPO) ");
        builder.append("VALUES(?,?) ");

        return jdbcTemplate.update(
                builder.toString(),
                encuestaRequest.getEmail(),
                encuestaRequest.getPkMusicaTipo());
    }

    @Override
    public List<EncuestaDTO> getEncuestaPorEmail(String email) {

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM DEMO_ENCUESTA WHERE EMAIL = ?");

        return jdbcTemplate.query(sb.toString(),
                (rs, rowNum) -> {
                    EncuestaDTO encuesta = new EncuestaDTO();
                    encuesta.setEmail(rs.getString("email"));
                    encuesta.setFkMusicaTipo(rs.getInt("fk_musica_tipo"));
                    encuesta.setPkEncuesta(rs.getInt("pk_encuesta"));
                    return encuesta;
                }, email);
    }
}
