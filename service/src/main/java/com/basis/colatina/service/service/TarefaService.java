package com.basis.colatina.service.service;

import com.basis.colatina.service.domain.Tarefa;
import com.basis.colatina.service.repository.TarefaRepository;
import com.basis.colatina.service.service.dto.AnexoDTO;
import com.basis.colatina.service.service.dto.TarefaAnexosDTO;
import com.basis.colatina.service.service.dto.TarefaDTO;
import com.basis.colatina.service.service.dto.TarefaListagemDTO;
import com.basis.colatina.service.service.event.TarefaEvent;
import com.basis.colatina.service.service.mapper.TarefaListagemMapper;
import com.basis.colatina.service.service.mapper.TarefaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    private final TarefaMapper tarefaMapper;

    private final TarefaListagemMapper tarefaListagemMapper;

    private final ApplicationEventPublisher applicationEventPublisher;

    private final AnexoService anexoService;

    public List<TarefaListagemDTO> getAll(){
        List<Tarefa> tarefas = tarefaRepository.findAll();
        return tarefaListagemMapper.toDto(tarefas);
    }

    public TarefaDTO getById(Long idTarefa){
        Tarefa tarefa = tarefaRepository.findById(idTarefa)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        return tarefaMapper.toDto(tarefa);
    }

    public TarefaDTO save(TarefaAnexosDTO tarefaAnexosDTO){
        Tarefa tarefa = tarefaMapper.toEntity(tarefaAnexosDTO.getTarefaDTO());
        tarefaRepository.save(tarefa);
        applicationEventPublisher.publishEvent(new TarefaEvent(tarefa.getId()));

        saveAnexos(tarefaAnexosDTO.getAnexosDTO(), tarefa.getId());

        return tarefaMapper.toDto(tarefa);
    }

    public TarefaDTO saveTarefa(TarefaDTO tarefaDTO){
        Tarefa tarefa = tarefaMapper.toEntity(tarefaDTO);
        tarefaRepository.save(tarefa);
        applicationEventPublisher.publishEvent(new TarefaEvent(tarefa.getId()));
        return tarefaMapper.toDto(tarefa);
    }

    private void saveAnexos(List<AnexoDTO> anexos, Long idTarefa){
        anexos.forEach(anexo -> anexo.setTarefaId(idTarefa));
        anexoService.saveAll(anexos);
    }

    public void remove(Long idTarefa){
        tarefaRepository.deleteById(idTarefa);
    }
}
