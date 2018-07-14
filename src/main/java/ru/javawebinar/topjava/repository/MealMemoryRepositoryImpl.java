package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealMemoryRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> mealMemoryMap = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    public MealMemoryRepositoryImpl() {
        MealsUtil.MEALS.forEach(this::createAndUpdate);
    }

    @Override
    public Meal createAndUpdate(Meal meal) {
        if (meal.getId() == null) {
            meal.setId(counter.incrementAndGet());
        }
        mealMemoryMap.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public void delete(int id) {
        mealMemoryMap.remove(id);
    }

    @Override
    public Meal get(int id) {
        return mealMemoryMap.get(id);
    }

    @Override
    public Collection<Meal> getAll() {
        return mealMemoryMap.values();
    }
}
