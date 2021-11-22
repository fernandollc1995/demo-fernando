import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AddEncuestaREquest } from '@models/encuesta.model';
import { MusicaTipo } from '@models/musica-tipo.model';
import { CommonService } from '@services/common.service';
import { EncuestaService } from '@services/encuesta.service';
import { MusicaService } from '@services/musica.service';
import { ntfError, ntfOk } from '@shared/functions';

@Component({
  selector: 'app-formulario',
  templateUrl: './formulario.component.html',
  styleUrls: ['./formulario.component.css'],
})
export class FormularioComponent implements OnInit {
  encuestaForm = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    musicaTipoSelected: new FormControl(null, [Validators.required]),
  });

  musicaTipoList: MusicaTipo[] = [];

  constructor(
    private musicaService: MusicaService,
    private encuestaService: EncuestaService,
    private commonService: CommonService,
    public router: Router
  ) {}

  ngOnInit(): void {
    this.musicaService.getListaEncuesta().subscribe(
      (response: any) => this.musicaTipoList = response.data,
      (err) =>
        (this.commonService.notification = ntfError(
          'Ocurrio un error al cargar los datos del formulario'
        ))
    );
  }

  submit() {
    if (this.encuestaForm.invalid) {
      this.markFormGroupTouched(this.encuestaForm);
      return;
    }
    let addEncuestaRequest = this.setAddEncuestaRequest(
      this.encuestaForm.value
    );
    this.encuestaService.addEncuesta(addEncuestaRequest).subscribe(
      (resp) => {
        this.router.navigate(['encuesta']);
        this.commonService.notification = ntfOk('Se registró correctamente');
      },
      (err) => {
        this.commonService.notification = ntfError(err.error.status.message);
      }
    );
  }

  setAddEncuestaRequest(values: any) {
    let addEncuestaRequest: AddEncuestaREquest = {
      email: values.email.trim(),
      pkMusicaTipo: values.musicaTipoSelected.pkMusicaTipo,
    };
    return addEncuestaRequest;
  }

  private markFormGroupTouched(formGroup: FormGroup) {
    (<any>Object).values(formGroup.controls).forEach((control: FormGroup) => {
      control.markAsTouched();
      if (control.controls) {
        this.markFormGroupTouched(control);
      }
    });
  }

  get formControls() {
    return this.encuestaForm.controls;
  }

  get isEmailInvalid() {
    return (
      this.formControls['email'].invalid &&
      (this.formControls['email'].dirty || this.formControls['email'].touched)
    );
  }

  get isNotMusicSelected() {
    return (
      this.formControls['musicaTipoSelected'].invalid &&
      (this.formControls['musicaTipoSelected'].dirty ||
        this.formControls['musicaTipoSelected'].touched)
    );
  }
}
