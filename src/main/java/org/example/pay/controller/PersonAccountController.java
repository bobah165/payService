package org.example.pay.controller;


import lombok.RequiredArgsConstructor;
import org.example.pay.dto.PersonAccount;
import org.example.pay.service.PersonAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
public class PersonAccountController {

  private final PersonAccountService service;

  @PostMapping("/save")
  public ResponseEntity createAccount(@RequestBody PersonAccount personAccount) {
    service.save(personAccount);
    return ResponseEntity.ok("Account with name " + personAccount.getName() + " and sum = " + personAccount.getSum() + " was created ");
  }

  @GetMapping("/status/{accountId}")
  public ResponseEntity getCurrentStatus(@PathVariable("accountId") String accountId) {
    var account = service.findById(accountId);
    return ResponseEntity.ok("Current account sum is " + account.getSum());
  }
}
