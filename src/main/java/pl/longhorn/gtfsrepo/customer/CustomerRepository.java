package pl.longhorn.gtfsrepo.customer;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Map;

@Repository
public class CustomerRepository {

    private final SimpleJdbcInsert simpleJdbcInsert;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final CustomerRowMapper customerRowMapper;

    public CustomerRepository(
            NamedParameterJdbcTemplate namedParameterJdbcTemplate
    ) {
        simpleJdbcInsert = new SimpleJdbcInsert(namedParameterJdbcTemplate.getJdbcTemplate())
                .withTableName("gtfs_customer")
                .usingGeneratedKeyColumns("id");
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        customerRowMapper = new CustomerRowMapper();
    }

    public Customer save(Customer customer) {
        var id = simpleJdbcInsert.executeAndReturnKey(Map.of(
                "name", customer.getName()));
        customer.setId(id.intValue());
        return customer;
    }

    public Customer findByName(String name) {
        try {
            return namedParameterJdbcTemplate.queryForObject(
                    "SELECT * FROM gtfs_customer WHERE name = :name",
                    Map.of("name", name),
                    customerRowMapper
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private class CustomerRowMapper implements RowMapper<Customer> {
        @Override
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Customer.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .build();
        }
    }
}
