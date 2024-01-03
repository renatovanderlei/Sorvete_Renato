import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { registerables, Chart } from 'chart.js';
import { RealtorioService } from '../service/RelatorioService';

export interface ListaAllDto {
  nome: String,
  quantidade: Number
}

@Component({
  selector: 'app-emitir-relatorio',
  templateUrl: './emitir-relatorio.component.html',
  styleUrls: ['./emitir-relatorio.component.css']
})
export class EmitirRelatorioComponent implements OnInit {
  form!: FormGroup;

  @ViewChild("tipoSorvete", { static: true }) elementoTipo!: ElementRef;
  @ViewChild("saborSorvete", { static: true }) elementoSabor!: ElementRef;

  chartTipo!: Chart<"bar", Number[], String>;
  chartSabor!: Chart<"bar", Number[], String>;

  listaSabor: ListaAllDto [] = [];
  listaTipo: ListaAllDto [] = [];

  title = false;

  constructor(
    private formBuilder: FormBuilder,
    private relatorioService: RealtorioService
  ){
    Chart.register(...registerables);
  }

ngOnInit(): void {
  this.form = this.formBuilder.group({
    data: ['', [
      Validators.required
    ]],
  });
}

  onSubmit(){
    this.title = true;
    this.relatorioService.findAllSabor(this.form.value.data).subscribe((resp : any) => {
      this.listaSabor = resp.body;
      this.graficoSaborDto();
    });
    this.relatorioService.findAllTipo(this.form.value.data).subscribe((resp : any) => {
      this.listaSabor = resp.body;
      this.graficoTipoDto();
    })
  }

  graficoTipoDto() {
    let labels = this.listaSabor.map(item => item.nome);
    let data = this.listaSabor.map(item => item.quantidade);

    let maxYLimit: any = 0;
    data.forEach(element => {
      if(element > maxYLimit){
        maxYLimit = element;
      }
    });

    if (this.chartTipo)
      this.chartTipo.destroy();

      const backgroundColors = this.generateRandomColors(data.length);

    this.chartTipo = new Chart(this.elementoTipo.nativeElement, {
      type: 'bar',

      data: {
        labels: labels,
        datasets: [
          {
            label: 'Sabor' ,
            data: data,
            backgroundColor: backgroundColors,
          }
        ]
      },
      options: {
        scales: {
          y: {
            beginAtZero: true,
            max: maxYLimit * 1.1,
          },
        }
      }
    });

  }

  graficoSaborDto() {
    let labels = this.listaSabor.map(item => item.nome);
    let data = this.listaSabor.map(item => item.quantidade);

    let maxYLimit: any = 0;
    data.forEach(element => {
      if(element > maxYLimit){
        maxYLimit = element;
      }
    });

    if (this.chartSabor)
    this.chartSabor.destroy();

      const backgroundColors = this.generateRandomColors(data.length);

    this.chartSabor = new Chart(this.elementoSabor.nativeElement, {
      type: 'bar',

      data: {
        labels: labels,
        datasets: [
          {
            label: 'Sabor' ,
            data: data,
            backgroundColor: backgroundColors,
          }
        ]
      },
      options: {
        scales: {
          y: {
            beginAtZero: true,
            max: maxYLimit * 1.1,
          },
        }
      }
    });

  }

  generateRandomColors(count: number): string[] {
    const colors: string[] = [];
    for (let i = 0; i < count; i++) {
      const color = this.getRandomColor();
      colors.push(color);
    }
    return colors;
  }
  
  getRandomColor(): string {
    // Gera uma cor aleatória em formato hexadecimal
    const letters = '0123456789ABCDEF';
    let color = '#';
    for (let i = 0; i < 6; i++) {
      color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
  }

}
