import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {TarefaComponent} from "./tarefa-form/tarefa.component";
import {SharedModule} from "../../shared/shared.module";
import {ReactiveFormsModule} from "@angular/forms";
import {TarefaRoutingModule} from "./tarefa-routing.module";

@NgModule({
  declarations: [TarefaComponent],
  imports: [
      CommonModule,
      TarefaRoutingModule,
      SharedModule,
      ReactiveFormsModule
  ]
})
export class TarefaModule { }
