package cn.letsky.cloud

import com.alibaba.csp.sentinel.annotation.SentinelResource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient
import org.springframework.context.annotation.Bean
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import java.util.concurrent.ThreadLocalRandom

@RestController
class HelloController {

    @Autowired
    private lateinit var restTemplate: RestTemplate
    @Autowired
    private lateinit var loadBalancerClient: LoadBalancerClient
    @Autowired
    private lateinit var feignclient: Feignclient

    @GetMapping("/hello/{id}")
    fun hello(@PathVariable("id") id: String): String {
        print("id: $id")
        return "hello $id"
    }

    @GetMapping("/consume")
    fun consume(): String? {
        val client = loadBalancerClient.choose("cloud-server")
        val url = "http://${client.host}:${client.port}/hello/${ThreadLocalRandom.current().nextInt(100)}"
        return restTemplate.getForObject(url, String::class.java)
    }

    @GetMapping("/feign/{id}")
    fun feign(@PathVariable("id") id: String): String? {
        return feignclient.getHello(id)
    }

    @Bean
    fun restTemplate(): RestTemplate {
        return RestTemplate()
    }
}