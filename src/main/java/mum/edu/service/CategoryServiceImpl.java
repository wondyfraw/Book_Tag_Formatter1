package mum.edu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import mum.edu.domain.Category;

@Service
public class CategoryServiceImpl implements CategoryService {
    
    /*
     * this implementation is not thread-safe
     */
    private List<Category> categories;
     
    public CategoryServiceImpl() {
        categories = new ArrayList<Category>();
        Category computingCategory = new Category(1, "Computing");
        Category travelCategory = new Category(2, "Travel");
        Category healthCategory = new Category(3, "Health");
        categories.add(computingCategory);
        categories.add(travelCategory);
        categories.add(healthCategory);
        
     }

    @Override
    public List<Category> getAll() {
        return categories;
    }
    
    @Override
    public Category getOne(int id) {
        for (Category category : categories) {
            if (id == category.getId()) {
                return category;
            }
        }
        return null;
    }

  }
