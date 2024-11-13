package org.example.pay.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.pay.dto.PersonAccount;
import org.example.pay.exception.NotEnoughSumException;
import org.example.pay.repository.PersonAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonAccountService {

  private final PersonAccountRepository repository;

  @Transactional
  public void save(PersonAccount personAccount) {
    repository.save(personAccount);
  }

  @Transactional
  public PersonAccount findById(String id) {
    return repository.findById(id).orElseThrow(() -> new RuntimeException("No account with " + id + " was created"));
  }

  @Transactional
  public void recalculate(Long price) {
    repository.findById("1")
      .ifPresent(personAccount -> {
        var currentSum = personAccount.getSum() - price;
        if (currentSum >= 0) {
          personAccount.setSum(currentSum);
          personAccount.setIsWithdrawn(true);
          repository.save(personAccount);
        } else {
          throw new NotEnoughSumException("Not enough sum in account");
        }
      });
  }

  @Transactional
  public void recoverSum(Long price) {
    repository.findById("1")
      .ifPresent(personAccount -> {
        var currentSum = personAccount.getSum() + price;
        personAccount.setSum(currentSum);
        personAccount.setIsWithdrawn(false);
        repository.save(personAccount);
      });
  }

  @Transactional
  public void setWithdrawnToFalse(){
    repository.findById("1")
      .ifPresent(personAccount -> {
        personAccount.setIsWithdrawn(false);
        repository.save(personAccount);
      });
  }
}
