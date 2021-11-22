export interface Encuesta {
  pkMusicaTipo: number;
  name: string;
  votos: number;
}

export interface AddEncuestaREquest {
  pkMusicaTipo: number;
  email: string;
}