package com.ofss.repository;
import com.ofss.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> { boolean existsByCardNumber(String cardNumber); List<CreditCard> findByCustomerId(Long customerId); }
