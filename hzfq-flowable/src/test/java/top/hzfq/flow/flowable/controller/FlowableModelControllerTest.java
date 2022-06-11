package top.hzfq.flow.flowable.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author huzhifengqing@qq.com
 * @since 2022/3/17 19:11
 */
@AutoConfigureMockMvc
@SpringBootTest
public class FlowableModelControllerTest {

    @Resource
    private MockMvc mockMvc;

    @Test
    void add() {
    }

    @Test
    void upload() {
    }

    @Test
    void upgrade() {
    }

    @Transactional
    @Rollback
    @Test
    void deploy() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/model/deploy")
                .param("modelId", "831be618-a546-11ec-b957-f4b3014993bf"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void xml() {
    }

    @Test
    void png() {
    }
}