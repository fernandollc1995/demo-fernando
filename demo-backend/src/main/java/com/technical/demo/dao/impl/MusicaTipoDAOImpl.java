package com.technical.demo.dao.impl;

import com.technical.demo.beans.dto.MusicaTipoDTO;
import com.technical.demo.dao.MusicaTipoDAO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MusicaTipoDAOImpl implements MusicaTipoDAO {

    private JdbcTemplate jdbcTemplate;

    public MusicaTipoDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<MusicaTipoDTO> getMusicaTipoList() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM DEMO_MUSICA_TIPO");

        return jdbcTemplate.query(sb.toString(), (rs, rowNum) -> {
            MusicaTipoDTO musicaTipoDTO = new MusicaTipoDTO();
            musicaTipoDTO.setName(rs.getString("name"));
            musicaTipoDTO.setPkMusicaTipo(rs.getInt("pk_musica_tipo"));
            return musicaTipoDTO;
        });
    }
}
