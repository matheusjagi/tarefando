package com.basis.colatina.service.service;

import com.basis.colatina.service.domain.Anexo;
import com.basis.colatina.service.repository.AnexoRepository;
import com.basis.colatina.service.service.dto.AnexoDTO;
import com.basis.colatina.service.service.dto.DocumentDTO;
import com.basis.colatina.service.service.event.AnexoEvent;
import com.basis.colatina.service.service.feign.DocumentClient;
import com.basis.colatina.service.service.mapper.AnexoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class AnexoService {

    private final AnexoRepository anexoRepository;

    private final AnexoMapper anexoMapper;

    private final DocumentClient documentClient;

    private final ApplicationEventPublisher applicationEventPublisher;

    public List<AnexoDTO> getAll(){
        List<Anexo> anexos = anexoRepository.findAll();
        return anexoMapper.toDto(anexos);
    }

    public AnexoDTO getById(Long idAnexo){
        Anexo anexo = anexoRepository.findById(idAnexo)
                .orElseThrow(() -> new RuntimeException("Anexo não encontrado"));
        return anexoMapper.toDto(anexo);
    }

    public AnexoDTO saveReturn(AnexoDTO anexoDTO){
        Anexo anexo = anexoMapper.toEntity(anexoDTO);
        anexo.setHash(documentClient.save(new DocumentDTO(anexo.getHash(), anexoDTO.getConteudo())));
        anexoRepository.save(anexo);
        applicationEventPublisher.publishEvent(new AnexoEvent(anexo.getId()));
        return anexoMapper.toDto(anexo);
    }

    public void save(AnexoDTO anexoDTO){
        Anexo anexo = anexoMapper.toEntity(anexoDTO);
        anexo.setHash(documentClient.save(new DocumentDTO(anexo.getHash(), anexoDTO.getConteudo())));
        anexoRepository.save(anexo);
        applicationEventPublisher.publishEvent(new AnexoEvent(anexo.getId()));
    }

    public void saveAll(List<AnexoDTO> anexos){
        anexos.forEach(doc -> save(doc));
    }

    public void remove(Long idAnexo){
        AnexoDTO anexoDTO = getById(idAnexo);
        documentClient.remove(anexoDTO.getHash());
        anexoRepository.deleteById(idAnexo);
    }
}
