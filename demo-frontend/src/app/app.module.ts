import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormularioComponent } from './components/formulario/formulario.component';
import { RouterModule } from '@angular/router';
import { EncuestaResultadoComponent } from './components/encuesta-resultado/encuesta-resultado.component';
import { NotificationComponent } from './@shared/notification/notification.component';

@NgModule({
  declarations: [AppComponent, FormularioComponent, EncuestaResultadoComponent, NotificationComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    RouterModule
  ],

  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
