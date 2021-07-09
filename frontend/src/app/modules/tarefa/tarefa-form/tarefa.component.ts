import {Component, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {PageNotificationService} from '@nuvem/primeng-components';
import {SelectItem} from 'primeng/api';
import {TarefaService} from '../../../services/tarefa.service';
import {ResponsavelService} from '../../../services/responsavel.service';
import {FileUpload} from 'primeng';
import {AnexoModel} from '../../../domain/anexo.model';
import {TarefaModel} from '../../../domain/tarefa.model';
import {finalize} from 'rxjs/operators';
import {AnexoService} from '../../../services/anexo.service';
import {TarefaAnexosModel} from '../../../domain/tarefa-anexos.model';

@Component({
  selector: 'app-tarefa',
  templateUrl: './tarefa.component.html',
  styleUrls: ['./tarefa.component.css']
})
export class TarefaComponent implements OnInit {

    @ViewChild('fileUpload') fileUpload: FileUpload;

    form: FormGroup;
    tarefas: TarefaModel[] = [];
    responsaveis: SelectItem[] = [];
    uploadedFiles: any[] = [];
    files: AnexoModel[] = [];
    tarefa: TarefaAnexosModel = new TarefaAnexosModel();
    anexo: AnexoModel;

    constructor(
        protected formBuilder: FormBuilder,
        protected notification: PageNotificationService,
        protected tarefaService: TarefaService,
        protected responsavelService: ResponsavelService,
    ) { }

    ngOnInit() {
        this.buildReactiveForm();
        this.getAllTarefas();
        this.getAllResponsaveis();
    }

    buildReactiveForm() {
        this.form = this.formBuilder.group({
            id: [null],
            titulo: [null, [Validators.required]],
            descricao: [null, [Validators.required]],
            dataInicioPrevista: [null, [Validators.required]],
            dataTerminoPrevista: [null, [Validators.required]],
            dataInicio: [null, [Validators.required]],
            dataTermino: [null, [Validators.required]],
            tipo: [null, [Validators.required]],
            status: [null, [Validators.required]],
            responsavelId: [null, [Validators.required]],
        });
    }

    getAllTarefas() {
        this.tarefaService.getAll().subscribe(
            (result) => {
                this.tarefas = result;
            }
        );
    }

    getAllResponsaveis() {
        this.responsavelService.getAll().subscribe(
            (result) => {
                this.responsaveis = result.map(responsavel => {
                    return {label: responsavel.nome, value: responsavel.id};
                });
            }
        );
    }

    upload(event) {
        const files = event.originalEvent.target.files;

        for (const file of files) {
            const reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onloadend = () => {
                const blob: any = reader.result;

                this.anexo = new AnexoModel();
                this.anexo.id = null;
                this.anexo.titulo = file.name;
                this.anexo.hash = null;
                this.anexo.conteudo = blob.split(',')[1];
                this.anexo.tamanho = file.size;
                this.anexo.tipo = file.type;
                this.anexo.tarefaId = null;

                this.files.push(this.anexo);
            };
        }
    }

    save() {
        this.tarefa.tarefaDTO = this.form.getRawValue();
        this.tarefa.anexosDTO = this.files;

        this.tarefaService.save(this.tarefa)
            .pipe(finalize(() => {
                this.form.reset();
                this.getAllTarefas();
            }))
            .subscribe(
                (result) => {
                    this.notification.addSuccessMessage('Tarefa criada com sucesso!');
                },
                () => {
                    this.notification.addErrorMessage('Falha ao criar tarefa.');
                }
            );
    }
}
