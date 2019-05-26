package server;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

/**
 * @GitHub : https://github.com/zacscoding
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ServerTests {

    @Autowired
    protected MockMvc mockMvc;

    @Test
    public void checkVersion130() throws Exception {
        MvcResult result = mockMvc.perform(get("/check/V1_3"))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();

        assertThat(result.getResponse().getContentAsString()).contains("V1_3");
    }

    @Test
    public void checkVersion140() throws Exception {
        MvcResult result = mockMvc.perform(get("/check/V1_4"))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();

        assertThat(result.getResponse().getContentAsString()).contains("V1_4");
    }

    @Test
    public void checkVersionWithInvalidAndThenReturnError() throws Exception {
        MvcResult result = mockMvc.perform(get("/check/V1000"))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andReturn();

        assertThat(result.getResponse().getContentAsString()).contains("V1000");
    }

    @Test
    public void client130() throws Exception {
        MvcResult result = mockMvc.perform(get("/client/V1_3"))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();
    }
}
