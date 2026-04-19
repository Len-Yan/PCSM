package com.example.demo.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.demo.domain.InhousePart;

@Component
public class PartValidator implements Validator {
    @Override
    public void validate(Object target, Errors errors) {
      InhousePart part = (InhousePart) target;
//      if (errors.hasFieldErrors("inv") || errors.hasFieldErrors("minInv") || errors.hasFieldErrors("maxInv")
//          || errors.hasFieldErrors("price")) {
//        return;
//      }

      if (part.getMinInv() > part.getMaxInv()) {
        errors.rejectValue("minInv", "error.range", "Please enter reasonable min inventory#");
      }
      if (part.getMaxInv() == 0 || part.getMinInv() > part.getMaxInv()) {
        errors.rejectValue("maxInv", "error.range", "Please enter reasonable max inventory#");
      }
      if (part.getInv() < part.getMinInv() || part.getInv() > part.getMaxInv() || part.getInv() <= 0) {
        errors.rejectValue("inv", "error.range", "Please enter inventory in range");
      }

      if(part.getPrice() <= 0){
        errors.rejectValue("price", "error.range", "price must be greater than 0");
      }
      if(part.getName().isEmpty() || part.getName() == ""){
        errors.rejectValue("name", "error.range", "name must not be empty");
      }
    }

  @Override
  public boolean supports(Class<?> clazz) {
    return InhousePart.class.equals(clazz);
  }


}
