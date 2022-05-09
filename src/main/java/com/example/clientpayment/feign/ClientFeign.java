package com.example.clientpayment.feign;

import com.example.clientpayment.model.ClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("client-core-api")
public interface ClientFeign{
//
//    @GetMapping("/check")
//    String checkClient();

    @GetMapping("/client")
    ClientResponse getClientById(@RequestParam String clientId);

    @GetMapping("/client/all")
    List<ClientResponse> getAllClient();

}
