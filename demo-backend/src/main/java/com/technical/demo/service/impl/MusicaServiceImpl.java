package com.technical.demo.service.impl;

import com.technical.demo.beans.dto.MusicaTipoDTO;
import com.technical.demo.beans.response.Response;
import com.technical.demo.dao.MusicaTipoDAO;
import com.technical.demo.enumeration.EstadosEnum;
import com.technical.demo.service.MusicaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicaServiceImpl implements MusicaService {

    private static final Logger logger = LoggerFactory.getLogger(MusicaServiceImpl.class);

    @Autowired
    private MusicaTipoDAO musicaTipoDAO;

    @Override
    public Response<List<MusicaTipoDTO>> getMusicaTipoList() {

        Response<List<MusicaTipoDTO>> response = new Response<>();
        try {
            List<MusicaTipoDTO> data = musicaTipoDAO.getMusicaTipoList();
            response.setData(data);
            response.setStatus(EstadosEnum.STATUS_SUCCESSFULL.create());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.setStatus(EstadosEnum.STATUS_FAIL.create());
        }
        return response;
    }

}
