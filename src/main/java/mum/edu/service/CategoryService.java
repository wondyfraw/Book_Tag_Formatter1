package mum.edu.service;

import java.util.List;

import mum.edu.domain.Book;
import mum.edu.domain.Category;

public interface CategoryService {
    
    List<Category> getAll();
    Category getOne(int id);
 }
