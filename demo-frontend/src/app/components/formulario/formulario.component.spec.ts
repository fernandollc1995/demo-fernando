import { HttpClientTestingModule } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { EncuestaService } from '@services/encuesta.service';
import { MusicaService } from '@services/musica.service';
import { FormularioComponent } from './formulario.component';

describe('prueba', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule,
        FormsModule,
        ReactiveFormsModule,
        RouterTestingModule,
      ],
      declarations: [FormularioComponent],
      providers: [MusicaService, EncuestaService],
    }).compileComponents();
  });

  it('Debe de existir el FormularioComponent', () => {
    const fixture = TestBed.createComponent(FormularioComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });

  it('Debe tener correo invalido', () => {
    const fixture = TestBed.createComponent(FormularioComponent);
    const app = fixture.componentInstance;
    fixture.detectChanges();

    const email = app.encuestaForm.controls['email'];
    email.setValue('pruebaError');

    expect(app.encuestaForm.invalid).toBeTrue();
  });

  it('Debe tener tipo de musica invalido', () => {
    const fixture = TestBed.createComponent(FormularioComponent);
    const app = fixture.componentInstance;
    fixture.detectChanges();

    const email = app.encuestaForm.controls['musicaTipoSelected'];
    email.setValue('pruebaError');

    expect(app.encuestaForm.invalid).toBeTrue();
  });

  it('Debe retornar formulario valido', () => {
    const fixture = TestBed.createComponent(FormularioComponent);
    const app = fixture.componentInstance;
    fixture.detectChanges();

    let email = app.encuestaForm.controls['email'];
    let musicaTipoSelected = app.encuestaForm.controls['musicaTipoSelected'];

    email.setValue('prueba@gmail.com');
    musicaTipoSelected.setValue({
      pkMusicaTipo: 0,
      name: '',
    });
    expect(app.encuestaForm.invalid).toBeFalse();
  });
});
