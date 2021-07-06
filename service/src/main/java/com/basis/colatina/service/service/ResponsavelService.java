package com.basis.colatina.service.service;

import com.basis.colatina.service.domain.Responsavel;
import com.basis.colatina.service.repository.ResponsavelRepository;
import com.basis.colatina.service.service.dto.ResponsavelDTO;
import com.basis.colatina.service.service.event.ResponsavelEvent;
import com.basis.colatina.service.service.mapper.ResponsavelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ResponsavelService {

    private final ResponsavelRepository responsavelRepository;

    private final ResponsavelMapper responsavelMapper;

    private final ApplicationEventPublisher applicationEventPublisher;

    public List<ResponsavelDTO> getAll(){
        List<Responsavel> responsaveis = responsavelRepository.findAll();
        return responsavelMapper.toDto(responsaveis);
    }

    public ResponsavelDTO getById(Long idResponsavel){
        Responsavel responsavel = responsavelRepository.findById(idResponsavel)
                .orElseThrow(() -> new RuntimeException("Responsavel não encontrado"));
        return responsavelMapper.toDto(responsavel);
    }

    public ResponsavelDTO save(ResponsavelDTO responsavelDTO){
        Responsavel responsavel = responsavelMapper.toEntity(responsavelDTO);
        responsavelRepository.save(responsavel);
        applicationEventPublisher.publishEvent(new ResponsavelEvent(responsavel.getId()));
        return responsavelMapper.toDto(responsavel);
    }

    public void remove(Long idResponsavel){
        responsavelRepository.deleteById(idResponsavel);
    }
}
