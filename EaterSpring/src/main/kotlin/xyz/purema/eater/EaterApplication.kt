package xyz.purema.eater

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EaterApplication

fun main(args: Array<String>) {
    runApplication<EaterApplication>(*args)
}
