package pl.longhorn.gtfsrepo.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customer/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public int add(@RequestBody String name) {
        return customerService.saveIfNotExist(name).getId();
    }
}
