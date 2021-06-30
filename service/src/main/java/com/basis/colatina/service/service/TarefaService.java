package com.basis.colatina.service.service;

import com.basis.colatina.service.domain.Tarefa;
import com.basis.colatina.service.repository.TarefaRepository;
import com.basis.colatina.service.service.dto.TarefaDTO;
import com.basis.colatina.service.service.mapper.TarefaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final TarefaMapper tarefaMapper;

    public List<TarefaDTO> getAll(){
        List<Tarefa> tarefas = tarefaRepository.findAll();
        return tarefaMapper.toDto(tarefas);
    }

    public TarefaDTO getById(Long idTarefa){
        Tarefa tarefa = tarefaRepository.findById(idTarefa)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        return tarefaMapper.toDto(tarefa);
    }

    public TarefaDTO save(TarefaDTO tarefaDTO){
        Tarefa tarefa = tarefaMapper.toEntity(tarefaDTO);
        tarefaRepository.save(tarefa);
        return tarefaMapper.toDto(tarefa);
    }

    public void remove(Long idTarefa){
        tarefaRepository.deleteById(idTarefa);
    }
}
