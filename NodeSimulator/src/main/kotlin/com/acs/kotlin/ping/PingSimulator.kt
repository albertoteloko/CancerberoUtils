package com.acs.kotlin.ping

import org.slf4j.LoggerFactory
import java.io.PrintWriter
import java.net.Socket


val IP = "192.168.1.66"
val PORT = 6969
val log = LoggerFactory.getLogger("com.acs.kotlin.PingSimulator")

fun main(args: Array<String>) {
    var index = 0
    while (true) {
        log.info("Loop")
        Socket(IP, PORT).use {
            log.info("Connected")
            val output = PrintWriter(it.getOutputStream(), true)
            val input = it.getInputStream()
            val message = "{\"name\":\"ping\", \"index\":\"${index++}\"}"
            log.info("Sending: {}", message)
            output.println(message)

            input.bufferedReader().use {
                log.info("Received: {}", it.readText())
            }
        }
        Thread.sleep(200)
    }
}