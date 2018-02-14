package ru.myori.web.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.myori.model.Order;
import ru.myori.service.OrderService;


public abstract class AbstractOrderController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final int userId=100000;

    @Autowired
    private OrderService service;

/*    public Product get(int id) {
        int userId = AuthorizedUser.id();
        log.info("get meal {} for User {}", id, userId);
        return service.get(id, userId);
    }*/

    public void delete(int id) {
//        int userId = AuthorizedUser.id();
        log.info("delete meal {} for User {}", id, userId);
        service.delete(id);
    }

/*    public List<MealWithExceed> getAll() {
        int userId = AuthorizedUser.id();
        log.info("getAll for User {}", userId);
        return MealsUtil.getWithExceeded(service.getAll(userId), AuthorizedUser.getCaloriesPerDay());
    }*/

    public Order create(Order order) {
//        int userId = AuthorizedUser.id();
//        checkNew(meal);
        log.info("create {} for User {}", order, userId);
        return service.create(order);
    }

    public void update(Order order, int id) {
//        int userId = AuthorizedUser.id();
//        assureIdConsistent(meal, id);
        log.info("update {} for User {}", order, userId);
//        service.update(product, userId);
    }

    /**
     * <ol>Filter separately
     *   <li>by date</li>
     *   <li>by time for every date</li>
     * </ol>
     */
/*    public List<MealWithExceed> getBetween(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        int userId = AuthorizedUser.id();
        log.info("getBetween dates({} - {}) time({} - {}) for User {}", startDate, endDate, startTime, endTime, userId);

        return MealsUtil.getFilteredWithExceeded(
                service.getBetweenDates(
                        startDate != null ? startDate : DateTimeUtil.MIN_DATE,
                        endDate != null ? endDate : DateTimeUtil.MAX_DATE, userId),
                startTime != null ? startTime : LocalTime.MIN,
                endTime != null ? endTime : LocalTime.MAX,
                AuthorizedUser.getCaloriesPerDay()
        );
    }*/
}