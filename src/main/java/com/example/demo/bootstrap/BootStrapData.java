package com.example.demo.bootstrap;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.repositories.OutsourcedPartRepository;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.service.OutsourcedPartService;
import com.example.demo.service.OutsourcedPartServiceImpl;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

/**
 *
 *
 */
@Component
public class BootStrapData implements CommandLineRunner {

    private final PartRepository partRepository;
    private final ProductRepository productRepository;

    private final OutsourcedPartRepository outsourcedPartRepository;

    public BootStrapData(PartRepository partRepository, ProductRepository productRepository, OutsourcedPartRepository outsourcedPartRepository) {
        this.partRepository = partRepository;
        this.productRepository = productRepository;
        this.outsourcedPartRepository=outsourcedPartRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        OutsourcedPart gpu1= new OutsourcedPart();
        gpu1.setCompanyName("Nvidia");
        gpu1.setName("RTX 4090");
        gpu1.setInv(3);
        gpu1.setPrice(1500.0);
        gpu1.setId(9002);
        gpu1.setMinInv(0);
        gpu1.setMaxInv(5);

        OutsourcedPart gpu2= new OutsourcedPart();
        gpu2.setCompanyName("Nvidia");
        gpu2.setName("RTX 4070");
        gpu2.setInv(5);
        gpu2.setPrice(600.0);
        gpu2.setId(9001);
        gpu2.setMinInv(0);
        gpu2.setMaxInv(10);

        OutsourcedPart motherboard1= new OutsourcedPart();
        motherboard1.setCompanyName("MSI");
        motherboard1.setName("B550");
        motherboard1.setInv(10);
        motherboard1.setPrice(150.0);
        motherboard1.setId(2001);
        gpu1.setMinInv(0);
        gpu1.setMaxInv(20);

        OutsourcedPart motherboard2= new OutsourcedPart();
        motherboard2.setCompanyName("MSI");
        motherboard2.setName("B650");
        motherboard2.setInv(5);
        motherboard2.setPrice(250.0);
        motherboard2.setId(2002);
        gpu2.setMinInv(0);
        gpu2.setMaxInv(10);

        OutsourcedPart harddrive1= new OutsourcedPart();
        harddrive1.setCompanyName("Western Digital");
        harddrive1.setName("SN 750 1TB");
        harddrive1.setInv(20);
        harddrive1.setPrice(60.0);
        harddrive1.setId(4001);
        harddrive1.setMinInv(0);
        harddrive1.setMaxInv(50);


        OutsourcedPart thePart=null;
        List<OutsourcedPart> sampleOutParts= Arrays.asList(gpu1,gpu2,motherboard1,harddrive1);
        HashSet <String> outparts=new HashSet<>();
        List<OutsourcedPart> outsourcedParts=(List<OutsourcedPart>) outsourcedPartRepository.findAll();
        for(OutsourcedPart part:outsourcedParts){
            System.out.println(part.getName()+" "+part.getCompanyName());
            outparts.add(part.getName());
        }
        for(OutsourcedPart part:sampleOutParts) {
            if (!outparts.contains(part.getName())) outsourcedPartRepository.save(part);
        }


        Product worker= new Product("Worker",1000.0,5);
        Product gamer= new Product("Gamer",1500.0,5);
        Product grapher= new Product("Grapher",2300.0,3);


        List<Product> sampleProducts= Arrays.asList(worker,gamer,grapher);
        HashSet<String> addProducts= new HashSet<>();
        List<Product> products = (List<Product>) productRepository.findAll();
        for(Product product:products){
            addProducts.add(product.getName());
        }
        for(Product product:sampleProducts){
            if(!addProducts.contains(product.getName())) productRepository.save(product);
        }

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Products"+productRepository.count());
        System.out.println(productRepository.findAll());
        System.out.println("Number of Parts"+partRepository.count());
        System.out.println(partRepository.findAll());

    }
}
