package org.launchcode.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 *
 */
@Controller
public class HelloController {

    @RequestMapping(value = "")
    @ResponseBody
    public String index(HttpServletRequest request) {

        String userName = request.getParameter("name");

        if (userName == null) {
            userName = "World";
        }

        return "Hello " + userName + "!";
    }

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    @ResponseBody
    public String helloForm() {

        String html = "<form method='post'>" +
                "<input type='text' name='name' />" +
                "<select id='language' name='language'>" +
                    "<option value='english'>English</option>" +
                    "<option value='french'>French</option>" +
                    "<option value='italian'>Italian</option>" +
                    "<option value='japanese'>Japanese</option>" +
                    "<option value='turkish'>Turkish</option>" +
                    "<option value='persian'>Persian</option>" +
                "</select>" +
                "<input type='submit' value='Greet Me!'/>" +
                "</form>";

        return html;
    }

    @RequestMapping(value = "hello", method = RequestMethod.POST)
    @ResponseBody
    public String helloPost(HttpServletRequest request) {

        String name = request.getParameter("name");
        String lang = request.getParameter("language");

        return createMessage(lang, name);
    }

    public static String createMessage(String selection, String name) {

        String greeting;
        switch (selection) {
            case "french":
                greeting = "Bonjour";
                break;
            case "italian":
                greeting = "Ciao";
                break;
            case "japanese":
                greeting = "Konnichiwa";
                break;
            case "turkish":
                greeting = "Merhaba";
                break;
            case "persian":
                greeting = "Salaam";
                break;
            default:
                greeting = "Hello";
        }


        return greeting + " " + name;
    }

    @RequestMapping(value = "hello/{name}")
    @ResponseBody
    public String helloUrlSegment(@PathVariable String name) {
        return "Hello" + name;
    }

    @RequestMapping(value = "goodbye")
    public String goodbye() {
        return "redirect:/";
    }
}
