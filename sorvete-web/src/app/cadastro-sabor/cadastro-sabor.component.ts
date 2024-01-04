import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { SaborService } from '../service/SaborService';

@Component({
  selector: 'app-cadastro-sabor',
  templateUrl: './cadastro-sabor.component.html',
  styleUrls: ['./cadastro-sabor.component.css']
})
//Chamando os métodos para serem utilizados no html
export class CadastroSaborComponent implements OnInit {

  //Defino o formulário e a lista de sabores
  form!: FormGroup;
  saborList: any[] = [];

  //Defino o construtor
  constructor(
    private formBuilder: FormBuilder,
    private saborService: SaborService 
  ){}

  //Defino o método para iniciar o formulário
  ngOnInit() {

    this.form = this.formBuilder.group({
      id: ['', []],
      codigo: ['', [
        Validators.required
      ]],
      nome: ['', [
        Validators.required
      ]],
      descricao: ['', [
        Validators.required
      ]],
    });

    this.load();

  }

  //Defino os métodos para deletar, atualizar, carregar, salvar e cancelar
  onDelete(sabor: any){
    this.saborService.delete(sabor.id).subscribe(
      response => {
        location.reload();
      }
    );
  }

  onUpdate(sabor: any){
    this.form.patchValue(sabor);
  }

  load(){
    this.saborService.findAll().subscribe((response: any) => {
      response = this.saborList = response;
    });
  }

  //Defino o método para salvar o sabor
  onSubmit(){
    if(this.form.valid){
     if(this.form.value.id){
      this.saborService.update(this.form.value).subscribe(
        response => {
          if (response) {
            location.reload();
          }
        }
      );
     }else{
      this.saborService.save(this.form.value).subscribe(
        response => {
          if (response) {
            location.reload();
          }
        }
      );
     }
    }
  }

  //Defino o método para cancelar o sabor
  onCancel(){
    this.form.reset();
  }

}
