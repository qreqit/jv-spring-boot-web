package mate.academy.springbootwebgreqit.controller;

import mate.academy.springbootwebgreqit.service.CategoryService;

public class CategoryControllerImpl extends CategoryController {
    public CategoryControllerImpl(CategoryService categoryService) {
        super(categoryService);
    }
}
