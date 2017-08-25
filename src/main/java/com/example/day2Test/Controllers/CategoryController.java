package com.example.day2Test.Controllers;

import com.example.day2Test.DTO.LoginDTO;
import com.example.day2Test.DTO.Status;
import com.example.day2Test.Domains.Category;
import com.example.day2Test.Service.CategoryService;
import com.example.day2Test.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.NotSupportedException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.EmptyStackException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResponseEntity addCategory(@RequestBody Category category,HttpServletRequest request){
        Category category1=null;

        if( category==null ){
            return new ResponseEntity(new Status("Unauthourized"),HttpStatus.NOT_ACCEPTABLE);
        }
        try{
            category1=categoryService.addCategory(category,request);
        }
        catch (NotSupportedException e){
            return new ResponseEntity(new Status("Unauthourized"), HttpStatus.NOT_ACCEPTABLE);
        }
        catch (SQLIntegrityConstraintViolationException e){
            return new ResponseEntity(new Status("Category should be unique"), HttpStatus.NOT_ACCEPTABLE);
        }

        if (category1 == null) {
            return new ResponseEntity(new Status("Fill all the Details"),HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(new Status("Category Added"),HttpStatus.OK);

    }


    @RequestMapping(value = "/getCategoryList",method = RequestMethod.GET)
    public ResponseEntity getCategoryList(){
        List<Category> categoryList=categoryService.getCategoryList();
        if(categoryList==null)
            return new ResponseEntity(new Status("Empty List"),HttpStatus.NO_CONTENT);
        return new ResponseEntity(categoryList,HttpStatus.OK);
    }

}
