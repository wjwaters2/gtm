package com.guessthemovie.gtm

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter
import java.util.ArrayList

@Controller
class HtmlController {

    @GetMapping("/")
    fun gtm(model: Model): String {
        model["title"] = "Guess The Movie"
        return "gtm"
    }

    @GetMapping("/buildData")
    fun buildData(){
        buildRating()
        buildBasic()
    }
    private fun buildRating() {
        val br = BufferedReader(FileReader("title.ratings.tsv"))
        val bw = BufferedWriter(FileWriter("title.ratings.csv"))

        while (br.ready()) {
            var line = br.readLine()
            line = line.replace(",".toRegex(), "_")
            line = line.replace("\t".toRegex(), ",")
            bw.write(line + "\n")
        }
        br.close()
        bw.close()
    }

    private fun buildBasic(){
        val br = BufferedReader(FileReader("title.basics.tsv"))
        val bw = BufferedWriter(FileWriter("title.basics.csv"))

        while (br.ready()) {
            var line = br.readLine()
            if (!line.contains("tvEpisode")
                    && !line.contains("tvSpecial")
                    && !line.contains("tvSeries")
                    && !line.contains("tvShort")
                    && !line.contains("video")
                    && !line.contains("short")
                    && !line.contains("tvMiniSeries")
                    && !line.contains("videoGame")) {
                line = line.replace(",".toRegex(), "_")
                line = line.replace("\t".toRegex(), ",")
                var newLine = ""
                for (i in line.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray().indices) {
                    if (i == 0) {
                        newLine += line.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[i]
                    } else if (i == 7) {
                        newLine += "," + leftPad(line.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[i])
                    } else {
                        newLine += "," + line.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[i]
                    }
                }
                bw.write(newLine + "\n")
            }
        }
        br.close()
        bw.close()
    }

    private fun leftPad(str: String): String {
        if (str.length == 1) {
            return "000$str"
        }
        if (str.length == 2) {
            return "00$str"
        }
        return if (str.length == 3) {
            "0$str"
        } else str
    }
}