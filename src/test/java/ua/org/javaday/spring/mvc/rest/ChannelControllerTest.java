package ua.org.javaday.spring.mvc.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.org.javaday.spring.mvc.dao.ChannelRepository;
import ua.org.javaday.spring.mvc.domain.Channel;
import ua.org.javaday.spring.mvc.rest.exception.ChannelNotFoundException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ChannelControllerTest {
    @Mock
    private ChannelRepository channelRepository;

    @Mock
    private Channel channel;

    @InjectMocks
    private ChannelController channelController = new ChannelController();

    @Test
    public void itShouldFindMovie() {
        when(channelRepository.findOne(1)).thenReturn(channel);

        assertThat(channelController.getMovie(1), is(channel));
    }

    @Test(expected = ChannelNotFoundException.class)
    public void itShouldNotFoundMovie() {
        when(channelRepository.findOne(-1)).thenReturn(null);

        channelController.getMovie(-1);
    }

}
