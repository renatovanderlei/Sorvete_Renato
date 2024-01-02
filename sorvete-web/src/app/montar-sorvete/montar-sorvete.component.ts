import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { TipoSorveteService } from '../service/TipoSorveteService';
import { SaborService } from '../service/SaborService';
import { SorveteService } from '../service/SorveteService';

@Component({
  selector: 'app-montar-sorvete',
  templateUrl: './montar-sorvete.component.html',
  styleUrls: ['./montar-sorvete.component.css']
})
export class MontarSorveteComponent {
  form!: FormGroup;
  qtdMaxBola: number = 0;
  valor: number = 0;
  peso: number = 0;
  descricao!: string;
  tipoSorveteList: any[] = [];
  qtdArray!: any[];
  saborList: any[] = [];
  selectedSabores: { [key: number]: number } = {};
  sorveteList: any[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private tipoSorveteService: TipoSorveteService,
    private saborService: SaborService,
    private sorveteService: SorveteService
  ){}

  ngOnInit() {

    this.form = this.formBuilder.group({
      tipoSorvete: this.formBuilder.group({
        id: ['', [Validators.required]]
      }),
      sabores: this.formBuilder.array([0]),
    });

    this.load();

  }

  load(){
    this.tipoSorveteService.findAll().subscribe((response: any) => {
      response = this.tipoSorveteList = response;
    });

    this.saborService.findAll().subscribe((response: any) => {
      response = this.saborList = response;
    });

    this.sorveteService.findAll().subscribe((response: any) => {
      response = this.sorveteList = response;
    });
  }

  onSubmit(){
    const lentgh = this.form.get('sabores') as FormArray;
    if(lentgh.length != this.qtdMaxBola){
      return;
    }
  console.log(this.form.value);
    if(this.form.valid){
      this.sorveteService.save(this.form.value).subscribe(
        response => {
          if (response) {
            location.reload();
          }
        }
      );
    }
  }

  onCancel(){
    this.form.reset();
  }

  onChanges(index: number, event: any): void {
    const selectedSaborId = event?.target?.value;
    
    if (selectedSaborId !== undefined) {
      this.selectedSabores[index] = Number(selectedSaborId);
      this.atualizarFormArray();
    }
  }
  
  atualizarFormArray(): void {
    const saboresArray = this.form.get('sabores') as FormArray;
  
    while (saboresArray.length !== 0) {
      saboresArray.removeAt(0);
    }
  
    Object.keys(this.selectedSabores).forEach(key => {
      const index = Number(key);
      saboresArray.insert(index, this.formBuilder.control(this.selectedSabores[index]));
    });
  }
  

  onTipoSorveteChange() {
    const selectedTipoSorveteId = this.form.get('tipoSorvete.id')!.value;
    const selectedTipoSorvete = this.tipoSorveteList.find(tipo => tipo.id == selectedTipoSorveteId);

    if (selectedTipoSorvete) {
      this.qtdMaxBola = selectedTipoSorvete.qtdBola;
      this.valor = selectedTipoSorvete.valor;
      this.peso = selectedTipoSorvete.peso;
      this.descricao = selectedTipoSorvete.descricao;
      this.qtdArray = Array.from({ length: this.qtdMaxBola }, (_, index) => index + 1);
    } else {
      this.qtdMaxBola = 0;
      this.valor = 0;
      this.peso = 0;
      this.descricao = '';
      this.qtdArray = Array.from({ length: this.qtdMaxBola }, (_, index) => index + 1);
    }
  }
}
