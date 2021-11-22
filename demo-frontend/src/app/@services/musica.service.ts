import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MusicaTipo } from '@models/musica-tipo.model';
import { environment as env } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class MusicaService {
  musica = `${env.api_demo}/v1/musica`;

  constructor(private _http: HttpClient) {}

  getListaEncuesta() {
    const url = this.musica + '/musica-tipos';
    console.log(url)
    return this._http.get<Array<MusicaTipo>>(url);
  }

}
