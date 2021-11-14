package com.example.demo.controller;

import com.example.demo.models.Products;
import com.example.demo.repository.IProductsRepository;
import com.example.demo.service.IProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ProductsController {
    private final IProductsRepository iProductsRepository;
    @Autowired
    private IProductsService iProductsService;
    @Autowired
    public ProductsController(IProductsRepository productsRepository, IProductsService iProductsService) {
        this.iProductsRepository =productsRepository;
        this.iProductsService = iProductsService;
    }
    // displiegue listado
    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listProducts", iProductsService.getAllProducts());
        return "index";
    }
    @GetMapping("/showNewProductsForm")
    public String showNewProductsForm(Model model) {
        //crear atributo de modelo para vincular datos de formulario
        Products product = new Products();
        model.addAttribute("products", product);
        return "add-Products";
    }
    @PostMapping("/addProducts")
    public String addProducts (@Valid Products products, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-products";
        }
        iProductsService.saveProducts(products); //Guarda datos al servicio
        return "redirect:/";
    }
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Products product = iProductsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("products", product);
        return "update-products";
    }
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {

        //obtener producto del servicio
        Products product = iProductsService.getProductsById(id);
        //establecer empleado como atributo de modelo para rellenar previamente el formulario
        model.addAttribute("products", product);
        return "update-products";
    }
    @PostMapping("/saveProducts")
    public String saveProducts(@ModelAttribute("products") Products product) {
        // save employee to database
        iProductsService.saveProducts(product);
        return "redirect:/";
    }
    @GetMapping("/deleteProducts/{id}")
    public String deleteProducts(@PathVariable (value = "id") long id) {

        // call delete employee method
        this.iProductsService.deleteProductsById(id);
        return "redirect:/";
    }
}
