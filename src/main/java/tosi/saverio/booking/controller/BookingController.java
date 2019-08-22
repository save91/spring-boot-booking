package tosi.saverio.booking.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {

    @RequestMapping(value = "/",method= RequestMethod.GET)
    public String helloWorld() {
        return "Hello world";
    }

}
