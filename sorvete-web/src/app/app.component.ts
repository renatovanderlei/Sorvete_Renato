import { Component } from '@angular/core';

@Component({
  selector: 'app-root', //ele aponta para o router-outlet, que tem as rotas das páginas do projeto
  template: '<router-outlet></router-outlet>'
})
export class AppComponent {
  title = 'sorvete';
}
