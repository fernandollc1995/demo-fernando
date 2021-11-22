package com.technical.demo.service.impl;

import com.technical.demo.beans.dto.EncuestaDTO;
import com.technical.demo.beans.dto.ResultadoEncuestaDTO;
import com.technical.demo.beans.request.EncuestaRequest;
import com.technical.demo.beans.response.Response;
import com.technical.demo.dao.EncuestaDAO;
import com.technical.demo.enumeration.EstadosEnum;
import com.technical.demo.service.EncuestaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EncuestaServiceImpl implements EncuestaService {

    private static final Logger logger = LoggerFactory.getLogger(EncuestaServiceImpl.class);

    @Autowired
    private EncuestaDAO encuestaDAO;

    @Override
    public Response<List<ResultadoEncuestaDTO>> getVotesPorMusicaTipo() {
        Response<List<ResultadoEncuestaDTO>> response = new Response<>();
        try {
            List<ResultadoEncuestaDTO> data = encuestaDAO.getVotosPorMusicaTipo();
            response.setData(data);
            response.setStatus(EstadosEnum.STATUS_SUCCESSFULL.create());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.setStatus(EstadosEnum.STATUS_FAIL.create());
        }

        return response;
    }

    @Override
    public Response<Integer> agregarEncuesta(EncuestaRequest encuestaRequest) {

        Response<Integer> response = new Response<>();
        try {
            List<EncuestaDTO> list =  encuestaDAO.getEncuestaPorEmail(encuestaRequest.getEmail());
            if (list.size()>0){
                response.setStatus(EstadosEnum.EMAIL_USED.create());
                return response;
            }
            response.setData(encuestaDAO.agregarEncuesta(encuestaRequest));
            response.setStatus(EstadosEnum.STATUS_SUCCESSFULL.create());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.setStatus(EstadosEnum.STATUS_FAIL.create());
        }
        return response;
    }

}
