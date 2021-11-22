export class MusicaTipo {
  pkMusicaTipo: number = 0;
  name: string = '';

  constructor(obj?: Partial<MusicaTipo>) {
    if (obj) Object.assign(this, obj);
    else {
      this.pkMusicaTipo = 0;
      this.name = '';
    }
  }
}
