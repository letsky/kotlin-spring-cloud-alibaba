package cn.letsky.cloud

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
class CloudApplication

fun main(args: Array<String>) {
	runApplication<CloudApplication>(*args)
}
