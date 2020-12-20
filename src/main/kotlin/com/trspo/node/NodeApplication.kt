package com.trspo.node

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
@EnableAsync
class NodeApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<NodeApplication>(*args)
        }
    }
}

