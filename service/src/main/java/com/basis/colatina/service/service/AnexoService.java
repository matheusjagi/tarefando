package com.basis.colatina.service.service;

import com.basis.colatina.service.domain.Anexo;
import com.basis.colatina.service.repository.AnexoRepository;
import com.basis.colatina.service.service.dto.AnexoDTO;
import com.basis.colatina.service.service.mapper.AnexoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AnexoService {

    private final AnexoRepository anexoRepository;
    private final AnexoMapper anexoMapper;

    public List<AnexoDTO> getAll(){
        List<Anexo> anexos = anexoRepository.findAll();
        return anexoMapper.toDto(anexos);
    }

    public AnexoDTO getById(Long idAnexo){
        Anexo anexo = anexoRepository.findById(idAnexo)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        return anexoMapper.toDto(anexo);
    }

    public AnexoDTO save(AnexoDTO anexoDTO){
        Anexo anexo = anexoMapper.toEntity(anexoDTO);
        anexoRepository.save(anexo);
        return anexoMapper.toDto(anexo);
    }

    public void remove(Long idAnexo){
        anexoRepository.deleteById(idAnexo);
    }
}
