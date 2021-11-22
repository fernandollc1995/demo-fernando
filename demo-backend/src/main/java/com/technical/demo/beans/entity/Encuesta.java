package com.technical.demo.beans.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Table(value = "DEMO_ENCUESTA")
@Data
public class Encuesta {

    @Id
    private Integer pkEncuesta;

    private String email;

    private MusicaTipo musicaTipo;

}
