package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.ValidationUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public Meal create(Meal meal) {
        int userId = AuthorizedUser.id();
        ValidationUtil.checkNew(meal);
        log.info("create meal {} by user {}", meal.getId(), userId);
        return service.create(meal, userId);
    }

    public Meal update(Meal meal, int id) {
        int userId = AuthorizedUser.id();
        ValidationUtil.assureIdConsistent(meal, id);
        log.info("update meal {} by user {}", meal.getId(), userId);
        return service.update(meal, userId);
    }

    public void delete(int id) {
        int userId = AuthorizedUser.id();
        log.info("delete meal {} by user {}", id, userId);
        service.delete(id, userId);
    }

    public Meal get(int id) {
        int userId = AuthorizedUser.id();
        log.info("get meal {} by user {}", id, userId);
        return service.get(id, userId);
    }

    public List<MealWithExceed> getAll() {
        int userId = AuthorizedUser.id();
        log.info("get all meals by user {}", userId);
        return MealsUtil.getWithExceeded(service.getAll(userId), AuthorizedUser.getCaloriesPerDay());
    }

    public List<MealWithExceed> getBetween(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        int userId = AuthorizedUser.id();
        log.info("get between dates ({} - {}) time ({} - {}) by user {}", startDate, endDate, startTime, endTime, userId);

        List<Meal> mealsDateFiltered = service.getBetweenDates(
                startDate == null ? DateTimeUtil.DATE_MIN : startDate,
                endDate == null ? DateTimeUtil.DATE_MAX : endDate,
                userId);

        return MealsUtil.getFilteredWithExceeded(mealsDateFiltered, AuthorizedUser.getCaloriesPerDay(),
                startTime == null ? LocalTime.MIN : startTime,
                endTime == null ? LocalTime.MAX : endTime);
    }
}