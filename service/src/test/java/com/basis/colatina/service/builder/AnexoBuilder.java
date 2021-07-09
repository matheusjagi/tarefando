package com.basis.colatina.service.builder;

import com.basis.colatina.service.domain.Anexo;
import com.basis.colatina.service.service.AnexoService;
import com.basis.colatina.service.service.dto.AnexoDTO;
import com.basis.colatina.service.service.mapper.AnexoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.text.ParseException;
import java.util.Collection;
import java.util.UUID;

@Component
public class AnexoBuilder extends ConstrutorEntidade<Anexo> {

    @Autowired
    private AnexoService anexoService;

    @Autowired
    private AnexoMapper anexoMapper;

    @Autowired
    private TarefaBuilder tarefaBuilder;

    @Override
    public Anexo construirEntidade() throws ParseException {

        Anexo anexo = new Anexo();
        anexo.setTitulo("Titulo do anexo");
        anexo.setHash(UUID.randomUUID().toString());
        anexo.setTamanho(25966L);
        anexo.setTipo("pdf");
        anexo.setTarefa(tarefaBuilder.construir());

        return anexo;
    }

    @Override
    protected Anexo persistir(Anexo entidade) {
        AnexoDTO dto = anexoService.saveReturn(anexoMapper.toDto(entidade));
        return anexoMapper.toEntity(dto);
    }

    @Override
    protected Collection<Anexo> obterTodos() {
        return null;
    }

    @Override
    protected Anexo obterPorId(Integer id) {
        return null;
    }
}
