package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.repository.MealMemoryRepositoryImpl;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    private MealMemoryRepositoryImpl mealMemoryRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        mealMemoryRepository = new MealMemoryRepositoryImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("do get meals");
        response.setContentType("text/html;charset=utf-8");
        String action = request.getParameter("action");
        if (action == null) action = "all";
        switch (action) {
            case "create":
            case "update":
                log.debug("meal created or updated");
                final Meal meal = action.equals("create") ? new Meal(LocalDateTime.now(), "desr", 1000) : mealMemoryRepository.get(getId(request));
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("meal.jsp").forward(request, response);
                break;
            case "delete":
                int id = getId(request);
                mealMemoryRepository.delete(id);
                log.debug("meal {} deleted", id);
                response.sendRedirect("meals");
                break;
            case "all":
                log.debug("redirect to meals");
                List<MealWithExceed> meals = MealsUtil.getWithExceeded(mealMemoryRepository.getAll());
                request.setAttribute("meals", meals);
                request.getRequestDispatcher("meals.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("do post meals");
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");
        Meal meal = new Meal(id == "" ? null : getId(req),
                LocalDateTime.parse(req.getParameter("dateTime")),
                req.getParameter("description"),
                Integer.parseInt(req.getParameter("calories")));
        mealMemoryRepository.createAndUpdate(meal);
        resp.sendRedirect("meals");
    }

    private int getId(HttpServletRequest request) {
        String mealId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(mealId);
    }
}
