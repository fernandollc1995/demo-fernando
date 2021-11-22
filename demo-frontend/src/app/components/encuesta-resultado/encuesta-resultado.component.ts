import { Component, OnInit } from '@angular/core';
import { Encuesta } from '@models/encuesta.model';
import { EncuestaService } from '@services/encuesta.service';

@Component({
  selector: 'app-encuesta-resultado',
  templateUrl: './encuesta-resultado.component.html',
  styleUrls: ['./encuesta-resultado.component.css']
})
export class EncuestaResultadoComponent implements OnInit {

  encuestaResultados: Encuesta[] = [];

  constructor(private encuestaService: EncuestaService) { }

  ngOnInit(): void {

    this.encuestaService.getListaEncuesta().subscribe(
      (resp:any) => this.encuestaResultados = resp.data,
      err => console.error(err)
    )
  }

}
