package com.acs.kotlin.slave

import org.slf4j.LoggerFactory
import java.io.PrintWriter
import java.net.ServerSocket


val PORT = 6969
val log = LoggerFactory.getLogger("com.acs.kotlin.SlaveSimulator")

fun main(args: Array<String>) {
    val serverSocker = ServerSocket(PORT)
    var index :Byte= 0;
    while (true) {
        log.info("Loop")
        val connectionSocket = serverSocker.accept()
        connectionSocket.use {
            log.info("Connected")
            val input = it.getInputStream()
            val output = it.getOutputStream()

            var response = ByteArray(1)
            response[0] = index++
            input.bufferedReader().use {
                log.info("Received: {}", it.readLine())
                output.write(response)
                output.flush()
                it.close()
            }
        }
    }
}