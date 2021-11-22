import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AddEncuestaREquest, Encuesta } from '@models/encuesta.model';
import { environment as env } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class EncuestaService {

  encuesta = `${env.api_demo}/v1/encuesta`;

  constructor(private _http: HttpClient) {}

  getListaEncuesta() {
    return this._http.get<Array<Encuesta>>(this.encuesta);
  }

  addEncuesta(addEncuestaRequest: AddEncuestaREquest) {
    return this._http.post<String>(this.encuesta, addEncuestaRequest);
  }
}
