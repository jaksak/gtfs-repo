package pl.longhorn.gtfsrepo.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer saveIfNotExist(String name) {
        var existingCustomer = customerRepository.findByName(name);
        if (existingCustomer == null) {
            existingCustomer = customerRepository.save(Customer.builder()
                    .name(name)
                    .build());
        }
        return existingCustomer;
    }
}
