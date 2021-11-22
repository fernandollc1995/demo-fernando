package com.technical.demo.service;

import com.technical.demo.beans.dto.MusicaTipoDTO;
import com.technical.demo.beans.response.Response;

import java.util.List;

public interface MusicaService {

    Response<List<MusicaTipoDTO>> getMusicaTipoList();
}
