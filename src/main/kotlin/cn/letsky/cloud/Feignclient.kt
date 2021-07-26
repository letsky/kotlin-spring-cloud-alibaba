package cn.letsky.cloud

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "cloud-server")
interface Feignclient {

    @GetMapping("hello/{id}")
    fun getHello(@PathVariable("id") id: String): String
}