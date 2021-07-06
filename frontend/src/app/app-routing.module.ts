import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
    {
        path: '', children: [
            { path: '', redirectTo: 'tarefas', pathMatch: 'full' },
            { path: 'tarefas', loadChildren: () => import('./modules/tarefa/tarefa.module').then(m => m.TarefaModule) },
            { path: 'responsaveis', loadChildren: () => import('./modules/responsavel/responsavel.module').then(m => m.ResponsavelModule) }
        ]
    }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
