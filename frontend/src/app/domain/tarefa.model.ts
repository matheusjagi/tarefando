export class TarefaModel {
    id?: number;
    titulo?: string;
    descricao?: string;
    dataInicioPrevista?: Date;
    dataTerminoPrevista?: Date;
    dataInicio?: Date;
    dataTermino?: Date;
    tipo?: string;
    status?: string;
    responsavelId?: number;
}
