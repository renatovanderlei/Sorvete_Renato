import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { SaborService } from '../service/SaborService';

@Component({
  selector: 'app-cadastro-sabor',
  templateUrl: './cadastro-sabor.component.html',
  styleUrls: ['./cadastro-sabor.component.css']
})
export class CadastroSaborComponent implements OnInit {

  form!: FormGroup;
  saborList: any[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private saborService: SaborService 
  ){}

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

  onCancel(){
    this.form.reset();
  }

}
