import { CommonModule } from '@angular/common';
import { DEFAULT_CURRENCY_CODE, LOCALE_ID, NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ScreenAppComponent } from './screen-app/screen-app.component';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CadastroSaborComponent } from './cadastro-sabor/cadastro-sabor.component';
import { CadastroTipoSorveteComponent } from './cadastro-tipo-sorvete/cadastro-tipo-sorvete.component';
import { HttpClientModule } from '@angular/common/http';
import { MontarSorveteComponent } from './montar-sorvete/montar-sorvete.component';
import { EmitirRelatorioComponent } from './emitir-relatorio/emitir-relatorio.component';
import { ToolbarComponent } from './toolbar/toolbar.component';

@NgModule({
  declarations: [
    AppComponent,
    CadastroSaborComponent,
    ScreenAppComponent,
    CadastroTipoSorveteComponent,
    MontarSorveteComponent,
    EmitirRelatorioComponent,
    ToolbarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    CommonModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [
    { provide: LOCALE_ID, useValue: 'pt' },    
    { provide: DEFAULT_CURRENCY_CODE, useValue: 'BRL' },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
