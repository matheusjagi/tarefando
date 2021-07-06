import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {TarefaComponent} from "./tarefa-form/tarefa.component";

const routes: Routes = [
    { path: '', component: TarefaComponent}
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class TarefaRoutingModule { }
