package com.basis.colatina.service.resource;

import com.basis.colatina.service.builder.AnexoBuilder;
import com.basis.colatina.service.domain.Anexo;
import com.basis.colatina.service.service.mapper.AnexoMapper;
import com.basis.colatina.service.util.IntTestComum;
import com.basis.colatina.service.util.TestUtil;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@Transactional
public class AnexoIT extends IntTestComum {

    private static final String URL = "/api/anexo";

    @Autowired
    private AnexoBuilder anexoBuilder;

    @Autowired
    private AnexoMapper anexoMapper;

    @Test
    @SneakyThrows
    public void salvar() {
        Anexo anexo = anexoBuilder.construir();

        getMockMvc().perform(post(URL + "/teste")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(anexoMapper.toDto(anexo))))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}

