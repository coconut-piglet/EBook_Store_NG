package se228.richard.ebookstore.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class WishListService {

    private final static Logger logger = LoggerFactory.getLogger(WishListService.class);

    public final JdbcTemplate jdbcTemplate;

    public WishListService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    //@Transactional(propagation = Propagation.REQUIRES_NEW)
    //@Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void addBooksToWishList(String... books) {
        for (String book : books) {
            logger.info("Add " + book + " to the wish list.");
            jdbcTemplate.update("insert into wishlist(bookname) values (?)", book);
        }
    }

    public List<String> getWishList() {
        return jdbcTemplate.query("select bookname from wishlist", (rs, rowNum) -> rs.getString("bookname"));
    }
}
