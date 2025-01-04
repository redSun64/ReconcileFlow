package com.reconcile.flow.example.account_service.controller;


import com.reconcile.flow.example.account_service.dto.DeductDTO;
import com.reconcile.flow.example.account_service.service.AccountService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/account")
@RestController
public class AccountController {

    @Resource
    private AccountService accountService;

    @RequestMapping(value = "/deduct", method = RequestMethod.POST)
    private void deduct(@RequestBody DeductDTO dto) {
        accountService.deduct(dto.getUserId(), dto.getOpId(), dto.getOpAmount());
    }
}
