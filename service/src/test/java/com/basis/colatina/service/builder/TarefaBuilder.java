package com.basis.colatina.service.builder;

import com.basis.colatina.service.domain.Tarefa;
import com.basis.colatina.service.service.TarefaService;
import com.basis.colatina.service.service.dto.TarefaDTO;
import com.basis.colatina.service.service.mapper.TarefaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Collection;

@Component
public class TarefaBuilder extends ConstrutorEntidade<Tarefa> {

    @Autowired
    private TarefaService tarefaService;

    @Autowired
    private TarefaMapper tarefaMapper;

    @Autowired
    private ResponsavelBuilder responsavelBuilder;

    @Override
    public Tarefa construirEntidade() throws ParseException {

        Tarefa tarefa = new Tarefa();

        tarefa.setTitulo("Titulo da tarefa");
        tarefa.setDescricao("Descriçao da tarefa");
        tarefa.setDataInicio(LocalDate.now());
        tarefa.setDataTermino(LocalDate.now());
        tarefa.setDataInicioPrevista(LocalDate.now());
        tarefa.setDataTerminoPrevista(LocalDate.now());
        tarefa.setStatus("A fazer");
        tarefa.setTipo("Tarefa Pós");
        tarefa.setComentarios("Comentarios");
        tarefa.setResponsavel(responsavelBuilder.construir());

        return tarefa;
    }

    @Override
    protected Tarefa persistir(Tarefa entidade) {
        TarefaDTO dto = tarefaService.saveTarefa(tarefaMapper.toDto(entidade));
        return tarefaMapper.toEntity(dto);
    }

    @Override
    protected Collection<Tarefa> obterTodos() {
        return null;
    }

    @Override
    protected Tarefa obterPorId(Integer id) {
        return null;
    }
}
