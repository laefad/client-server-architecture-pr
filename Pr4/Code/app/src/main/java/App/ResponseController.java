package App;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.util.HtmlUtils;

@Controller
public class ResponseController {

	@GetMapping(path = "/")
    public String home() {
        return "index";
    }

    @MessageMapping("/hello")
    @SendTo("/topic/messages")
    public Response greeting(Message message) throws Exception {
        return new Response(HtmlUtils.htmlEscape(message.getValue()));
    }

}
