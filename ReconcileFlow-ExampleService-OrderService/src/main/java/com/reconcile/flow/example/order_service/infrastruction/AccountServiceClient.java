package com.reconcile.flow.example.order_service.infrastruction;

import com.reconcile.flow.example.order_service.dto.DeductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(
        name = "account-service",
        url = "http://localhost:8081"
)
public interface AccountServiceClient {

    @RequestMapping(value = "/account/deduct", method = RequestMethod.POST)
    void deduct(DeductDTO deductDTO);

}
