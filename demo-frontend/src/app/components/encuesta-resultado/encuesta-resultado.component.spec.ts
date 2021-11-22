import { HttpClientTestingModule } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { EncuestaResultadoComponent } from './encuesta-resultado.component';

describe('prueba', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule, //TODO: <-----
      ],
      declarations: [EncuestaResultadoComponent],
    }).compileComponents();
  });

  it('Debe de existir el AppComponent', () => {
    const fixture = TestBed.createComponent(EncuestaResultadoComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy(); //TODO: ✔
  });

});
