import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { TipoSorveteService } from '../service/TipoSorveteService';

@Component({
  selector: 'app-cadastro-tipo-sorvete',
  templateUrl: './cadastro-tipo-sorvete.component.html',
  styleUrls: ['./cadastro-tipo-sorvete.component.css']
})
export class CadastroTipoSorveteComponent implements OnInit  {
  form!: FormGroup;
  tipoSaborList: any[]= [];

  constructor(
    private formBuilder: FormBuilder,
    private tipoSorveteService: TipoSorveteService
  ){}

  ngOnInit() {

    this.form = this.formBuilder.group({
      id: ['', []],
      codigo: ['', [
        Validators.required
      ]],
      tipo: ['', [
        Validators.required
      ]],
      qtdBola: ['', [
        Validators.required
      ]],
      peso: ['', [
        Validators.required
      ]],
      descricao: ['', [
        Validators.required
      ]],
      valor: ['', [
        Validators.required
      ]],
    });

    this.load();

  }

  onDelete(tipoSorvete: any){
    this.tipoSorveteService.delete(tipoSorvete.id).subscribe(
      response => {
        location.reload();
      }
    );
  }

  onUpdate(tipoSorvete: any){
    this.form.patchValue(tipoSorvete);
  }

  load(){
    this.tipoSorveteService.findAll().subscribe((response: any) => {
      response = this.tipoSaborList = response;
    });
  }

  onSubmit(){
    if(this.form.valid){

      if(this.form.value.id){
        this.tipoSorveteService.update(this.form.value).subscribe(
          response => {
            if (response) {
              location.reload();
            }
          }
        );
      }else{
        this.tipoSorveteService.save(this.form.value).subscribe(
          response => {
            if (response) {
              location.reload();
            }
          }
        );
      }
      }
  }

  onCancel(){
    this.form.reset();
  }

}
