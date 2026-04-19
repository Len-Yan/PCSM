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
      if (part.getMinInv() > part.getMaxInv()) {
        errors.rejectValue("minInv", "error.range", "check stock input");
      }
      if (part.getInv() < part.getMinInv() || part.getInv() > part.getMaxInv()) {
        errors.rejectValue("inv", "error.range", "check stock input");
      }
      if (part.getPartId() <= 0){
        errors.rejectValue("partId", "error.range", "parId must be greater than 0");
      }
      if(part.getPrice() <= 0){
        errors.rejectValue("price", "error.range", "price must be greater than 0");
      }
      if(part.getName().isEmpty()){
        errors.rejectValue("name", "error.range", "name must not be empty");
      }
    }

  @Override
  public boolean supports(Class<?> clazz) {
    return InhousePart.class.equals(clazz);
  }


}
