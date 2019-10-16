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

internal var names = ArrayList<String>()

fun main(args: Array<String>) {
    buildRating()
    buildBasic()
    buildPrincipals()
    buildPrincipalsWriters()
    runApplication<GtmApplication>(*args)
}

fun buildRating() {
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

fun buildBasic(){
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

fun buildPrincipals(){
    // these are people from Scream 3
    names.add("nm0001937")
    names.add("m0000274")
    names.add("m0000117")
    names.add("m0001073")
    names.add("m0000630")
    names.add("m0000127")
    names.add("m0932078")
    names.add("m0472567")
    names.add("m0465298")
    names.add("m0534543")

    val br = BufferedReader(FileReader("title.principals.tsv"))
    val bw = BufferedWriter(FileWriter("title.principals.csv"))

    var line = br.readLine()
    line = line.replace(",".toRegex(), "_")
    line = line.replace("\t".toRegex(), ",")
    bw.write(line + "\n")

    while (br.ready()) {
        line = br.readLine()
        line = line.replace(",".toRegex(), "_")
        line = line.replace("\t".toRegex(), ",")
        if (containsScream3Actor(line)) {
            bw.write(line + "\n")
        }
    }
    br.close()
    bw.close()
}

fun buildPrincipalsWriters(){
    // these are people from Scream 3
    names.add("tt1028528")

    val br = BufferedReader(FileReader("title.principals.tsv"))
    val bw = BufferedWriter(FileWriter("title.principals.csv"))

    var line = br.readLine()
    line = line.replace(",".toRegex(), "_")
    line = line.replace("\t".toRegex(), ",")
    bw.write(line + "\n")

    while (br.ready()) {
        line = br.readLine()
        line = line.replace(",".toRegex(), "_")
        line = line.replace("\t".toRegex(), ",")
        if (containsScream3Actor(line)) {
            bw.write(line + "\n")
        }
    }
    br.close()
    bw.close()
}

private fun containsScream3Actor(line: String): Boolean {
    for (i in names.indices) {
        if (line.contains(names.get(i))) {
            return true
        }
    }
    return false
}

fun leftPad(str: String): String {
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