package com.technical.demo.controller;

import com.technical.demo.beans.dto.MusicaTipoDTO;
import com.technical.demo.beans.response.Response;
import com.technical.demo.enumeration.EstadosEnum;
import com.technical.demo.service.MusicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "v1/musica")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MusicaController {

    @Autowired
    private MusicaService musicaService;

    @GetMapping(value = "/musica-tipos")
    public ResponseEntity<Response<List<MusicaTipoDTO>>> getMusicaTipos() {

        Response<List<MusicaTipoDTO>> response = musicaService.getMusicaTipoList();

        return EstadosEnum.STATUS_SUCCESSFULL.isCode(response.getStatus())
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
