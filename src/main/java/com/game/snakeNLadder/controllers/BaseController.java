package com.game.snakeNLadder.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class BaseController {

    @ExceptionHandler({Throwable.class, Exception.class})
    public ModelAndView handleError(HttpServletRequest req, Exception ex) {
        log.error("Request: " + req.getRequestURL() + ", exception : ", ex);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
