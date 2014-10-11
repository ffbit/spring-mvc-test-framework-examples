package ua.org.javaday.spring.mvc.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.org.javaday.spring.mvc.dao.ChannelRepository;
import ua.org.javaday.spring.mvc.domain.Channel;
import ua.org.javaday.spring.mvc.rest.exception.ChannelNotFoundException;

@Controller
@RequestMapping("/channels")
public class ChannelController {
    @Autowired
    private ChannelRepository channelRepository;

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Channel getMovie(@PathVariable int id) {
        Channel channel = channelRepository.findOne(id);

        if (channel != null) {
            return channel;
        }

        throw new ChannelNotFoundException();
    }

}
