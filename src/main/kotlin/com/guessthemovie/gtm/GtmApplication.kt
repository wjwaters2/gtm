package com.guessthemovie.gtm

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter
import java.util.ArrayList

@SpringBootApplication
open class GtmApplication

fun main(args: Array<String>) {
    runApplication<GtmApplication>(*args)
}