package com.example.day2Test.Service;

import com.example.day2Test.DTO.Status;
import com.example.day2Test.Domains.Category;
import com.example.day2Test.Domains.Login;
import com.example.day2Test.Repositories.CategoryRepository;
import com.example.day2Test.Repositories.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.NotSupportedException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.EmptyStackException;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private Authorization authorization;

    public Category addCategory(Category category,HttpServletRequest request) throws SQLIntegrityConstraintViolationException,NotSupportedException,EmptyStackException{


        Boolean authorized = authorization.checkAuthorization("Super Admin",request);

        if(!authorized){
            throw new NotSupportedException("Unauthorized");
        }
        if(category.getCategoryName()==null || category.getCategoryName().isEmpty() )
            return null;

        Category category1=categoryRepository.save(category);
        return category1;

    }


    public List<Category> getCategoryList(){

        List<Category> categoryList=categoryRepository.findAll();
        return categoryList;
    }
}
