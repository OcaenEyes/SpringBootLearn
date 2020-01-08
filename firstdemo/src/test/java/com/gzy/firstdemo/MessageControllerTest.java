package com.gzy.firstdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@SpringBootTest
@AutoConfigureMockMvc
public class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void saveMessages() throws Exception {
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("text", "one text");
        params.add("summary", "one summary");
        String mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/msg/save")
                .params(params)).andReturn().getResponse().getContentAsString();
        System.out.println("Result=====" + mvcResult);
    }

    @Test
    //批量保存消息测试
    public void saveManyMessages() {
        for (int i = 1; i < 10; i++) {
            final MultiValueMap<String, String> parms = new LinkedMultiValueMap<>();
            parms.add("text", "text" + i);
            parms.add("summary", "summary" + i);
            try {
                String mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/msg/save").params(parms)).andReturn().getResponse().getContentAsString();
                System.out.println(mvcResult);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    //测试获取所有消息
    public void getAllMessages() throws Exception {
        String mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/msg/findAll")).andReturn().getResponse().getContentAsString();
        System.out.println("读取所有结果"+mvcResult);
    }
}
