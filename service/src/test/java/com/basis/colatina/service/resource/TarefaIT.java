package com.basis.colatina.service.resource;

import com.basis.colatina.service.builder.TarefaBuilder;
import com.basis.colatina.service.config.containers.ContainersFactory;
import com.basis.colatina.service.domain.Tarefa;
import com.basis.colatina.service.service.mapper.TarefaMapper;
import com.basis.colatina.service.util.IntTestComum;
import com.basis.colatina.service.util.TestUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@Transactional
@Slf4j
public class TarefaIT extends IntTestComum {

    private static final String URL = "/api/tarefa";

    @Autowired
    private TarefaBuilder tarefaBuilder;

    @Autowired
    private TarefaMapper tarefaMapper;

    public static ContainersFactory containers = ContainersFactory.getInstances();

    @Test
    @SneakyThrows
    public void listar() {
        tarefaBuilder.construir();
        getMockMvc().perform(get(URL))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    public void salvar() {
        Tarefa tarefa = tarefaBuilder.construir();

        getMockMvc().perform(post(URL + "/teste")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(tarefaMapper.toDto(tarefa))))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
