package utez.edu.mx.dulceria.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import utez.edu.mx.dulceria.Utils.Message;
import utez.edu.mx.dulceria.Utils.SaveImage;
import utez.edu.mx.dulceria.product.model.Product;
import utez.edu.mx.dulceria.product.model.ProductDTO;
import utez.edu.mx.dulceria.product.service.ProductService;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = {"*"})
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    SaveImage saveImage;
    @GetMapping("/")
    public ResponseEntity<Message> getAll(){
        return productService.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Message> getById(@PathVariable("id") long id){
        return productService.findById(id);
    }

    @PostMapping("/")
    public ResponseEntity<Message> save(@RequestParam("file") MultipartFile multipartFile,@RequestParam("name") String name,@RequestParam("description") String description,@RequestParam("price") String price){
        System.out.println("name: "+name+" description: "+description+" price: "+price);
        return productService.save(new Product(name, description, Integer.parseInt(price), saveImage.upload(multipartFile)));
    }

    @PutMapping("/")
    public ResponseEntity<Message> update(@RequestBody ProductDTO productDTO){
        return productService.update(new Product(productDTO.getId(), productDTO.getName(), productDTO.getDescription(), productDTO.getPrice(), productDTO.getImage()));
    }
}
