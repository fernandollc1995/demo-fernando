package com.technical.demo.beans.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class EncuestaRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Pattern(regexp = "^\\S{1,}@\\S{2,}\\.\\S{2,}$")
    private String email;

    @NotNull
    private Integer pkMusicaTipo;
}
