package com.technical.demo.beans.entity;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Table(value = "DEMO_MUSICA_TIPO")
@Data
public class MusicaTipo {

    private Integer pkMusicaTipo;

    private String name;
}
