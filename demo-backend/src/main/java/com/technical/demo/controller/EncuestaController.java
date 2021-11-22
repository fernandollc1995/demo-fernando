package com.technical.demo.controller;

import com.technical.demo.beans.dto.ResultadoEncuestaDTO;
import com.technical.demo.beans.request.EncuestaRequest;
import com.technical.demo.beans.response.Response;
import com.technical.demo.enumeration.EstadosEnum;
import com.technical.demo.service.EncuestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "v1/encuesta")
public class EncuestaController {

    @Autowired
    private EncuestaService encuestaService;

    @GetMapping
    public ResponseEntity<Response<List<ResultadoEncuestaDTO>>> getEncuestas() {

        Response<List<ResultadoEncuestaDTO>> response = encuestaService.getVotesPorMusicaTipo();

        return EstadosEnum.STATUS_SUCCESSFULL.isCode(response.getStatus())
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping
    public ResponseEntity<Response<Integer>> agregarEncuesta(@Valid @RequestBody EncuestaRequest encuestaRequest, Errors error) {
        if (error != null && error.hasErrors()) {
            return EstadosEnum.BAD_FORMAT.internalError(Integer.class);
        } else {
            Response<Integer> response = encuestaService.agregarEncuesta(encuestaRequest);
            return EstadosEnum.STATUS_SUCCESSFULL.isCode(response.getStatus())
                    ? new ResponseEntity<>(response, HttpStatus.OK)
                    : new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
