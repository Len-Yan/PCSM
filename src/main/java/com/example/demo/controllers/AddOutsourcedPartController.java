package com.example.demo.controllers;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.service.OutsourcedPartService;
import com.example.demo.service.OutsourcedPartServiceImpl;
import com.example.demo.service.PartService;
import com.example.demo.service.PartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 *
 *
 *
 *
 */
@Controller
public class AddOutsourcedPartController {
    @Autowired
    private ApplicationContext context;

    @GetMapping("/showFormAddOutPart")
    public String showFormAddOutsourcedPart(Model theModel){
        Part part=new OutsourcedPart();
        theModel.addAttribute("outsourcedpart",part);
        return "OutsourcedPartForm";
    }

    @PostMapping("/showFormAddOutPart")
    public String submitForm(@Valid @ModelAttribute("outsourcedpart") OutsourcedPart part, BindingResult bindingResult, Model theModel){
        theModel.addAttribute("outsourcedpart",part);
        if(bindingResult.hasErrors()){
            return "OutsourcedPartForm";
        }
        else{
        OutsourcedPartService repo=context.getBean(OutsourcedPartServiceImpl.class);
        OutsourcedPart op=repo.findById((int)part.getId());

        int minval = Integer.parseInt(bindingResult.getFieldValue("minInv").toString());
        int maxval = Integer.parseInt(bindingResult.getFieldValue("maxInv").toString());
        if(maxval<minval || minval>maxval) {
            bindingResult.rejectValue("minInv", "error.invalid", "Please enter reasonable min inventory#");
            bindingResult.rejectValue("maxInv", "error.invalid", "Please enter reasonable max inventory#");
            return "OutsourcedPartForm";
        }
        int inv = Integer.parseInt(bindingResult.getFieldValue("inv").toString());
            if(inv<minval || inv>maxval) {
                bindingResult.rejectValue("inv", "error.invalid", "Please enter inventory in range");
                return "OutsourcedPartForm";
            }
        if(op!=null) {
            part.setProducts(op.getProducts());

            op.setMinInv(minval);
            op.setMaxInv(maxval);
            if (!op.isValidInv(Integer.parseInt(bindingResult.getFieldValue("inv").toString()))) {
                bindingResult.rejectValue("inv", "error.invalid",
                    "Inventory must between min and max inventory");
                return "OutsourcedPartForm";
            }
        }
            repo.save(part);
        return "confirmationaddpart";}
    }



}
