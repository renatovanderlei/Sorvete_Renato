import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ScreenAppComponent } from './screen-app/screen-app.component';
import { CadastroSaborComponent } from './cadastro-sabor/cadastro-sabor.component';
import { CadastroTipoSorveteComponent } from './cadastro-tipo-sorvete/cadastro-tipo-sorvete.component';
import { MontarSorveteComponent } from './montar-sorvete/montar-sorvete.component';

const routes: Routes = [
  {
    path: 'cadastro-sorvete',
    component: CadastroSaborComponent
  },
  {
    path: 'cadastro-tipo-sorvete',
    component: CadastroTipoSorveteComponent
  },
  {
    path: 'montar-sorvete',
    component: MontarSorveteComponent
  },
  {
    path: 'app',
    component: ScreenAppComponent
  },
  {
    path: "", redirectTo: "/app", pathMatch: "full"
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
