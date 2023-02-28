package starb.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="example")
public class ExampleController {

    @RequestMapping(
            method=RequestMethod.GET,
            produces="text/plain")
    public String exampleGet() {
        return "Hello from ExampleController!";
    }

}
