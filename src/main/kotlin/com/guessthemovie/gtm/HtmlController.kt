package com.guessthemovie.gtm

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HtmlController {

    @GetMapping("/")
    fun gtm(model: Model): String {
        model["title"] = "Guess The Movie"
        return "gtm"
    }

}