package ua.org.javaday.spring.mvc.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ua.org.javaday.spring.mvc.dao.ChannelRepository;
import ua.org.javaday.spring.mvc.domain.Channel;
import ua.org.javaday.spring.mvc.rest.exception.ChannelNotFoundException;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class ChannelControllerStandaloneIT {
    @Mock
    private ChannelRepository channelRepository;

    private Channel channel = new Channel(1, "MTV");

    @InjectMocks
    private ChannelController channelController = new ChannelController();

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = standaloneSetup(channelController)
                .alwaysDo(print())
                .build();
    }

    @Test
    public void itShouldFindChannel() throws Exception {
        when(channelRepository.findOne(1)).thenReturn(channel);

        mockMvc.perform(get("/channels/1").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("MTV"));
    }

    @Test
    public void itShouldNotFindChannel() throws Exception {
        given(channelRepository.findOne(-1)).willReturn(null);

        MvcResult mvcResult = mockMvc
                .perform(get("/channels/-1")
                        .accept(APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        assertThat(mvcResult.getResolvedException(),
                instanceOf(ChannelNotFoundException.class));
    }

}
