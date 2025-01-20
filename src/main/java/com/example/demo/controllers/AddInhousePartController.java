package com.example.demo.controllers;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.Part;
import com.example.demo.service.InhousePartService;
import com.example.demo.service.InhousePartServiceImpl;
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
 */
@Controller
public class AddInhousePartController{
    @Autowired
    private ApplicationContext context;

    @GetMapping("/showFormAddInPart")
    public String showFormAddInhousePart(Model theModel){
        InhousePart inhousepart=new InhousePart();
        theModel.addAttribute("inhousepart",inhousepart);
        return "InhousePartForm";
    }

    @PostMapping("/showFormAddInPart")
    public String submitForm(@Valid @ModelAttribute("inhousepart") InhousePart part, BindingResult theBindingResult, Model theModel){
        theModel.addAttribute("inhousepart",part);
        if(theBindingResult.hasErrors()){
            return "InhousePartForm";
        }
        else{
        InhousePartService repo=context.getBean(InhousePartServiceImpl.class);
        InhousePart ip=repo.findById((int)part.getId());

        int minval = Integer.parseInt(theBindingResult.getFieldValue("minInv").toString());
        int maxval = Integer.parseInt(theBindingResult.getFieldValue("maxInv").toString());
        if(maxval<minval || minval>maxval) {
            theBindingResult.rejectValue("minInv", "error.invalid", "Please enter reasonable min inventory#");
            theBindingResult.rejectValue("maxInv", "error.invalid", "Please enter reasonable max inventory#");
            return "InhousePartForm";
        }
        int inv = Integer.parseInt(theBindingResult.getFieldValue("inv").toString());

        if(inv<minval || inv>maxval) {
            theBindingResult.rejectValue("inv", "error.invalid", "Please enter inventory in range");
            return "InhousePartForm";
        }
        if(ip!=null) {
            part.setProducts(ip.getProducts());

            ip.setMinInv(minval);
            ip.setMaxInv(maxval);
            if (!ip.isValidInv(
                Integer.parseInt(theBindingResult.getFieldValue("inv").toString()))) {
                theBindingResult.rejectValue("inv", "error.invalid",
                    "Inventory must between min and max inventory");
                return "InhousePartForm";
            }
        }
            repo.save(part);

        return "confirmationaddpart";}
    }

}
