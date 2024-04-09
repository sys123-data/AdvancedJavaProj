package comx.controller;

import comx.dto.ProductInventoryDTO;
import comx.dto.ProductInventoryUpdateDTO;
import comx.service.ManageProductsService;
import comx.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("admin")
public class ManageProductsController {
    @Autowired
    ManageProductsService manageProductsService;




    @GetMapping(value = "getProducts")
    public String getProducts( Model model) {
        model.addAttribute("product", new ProductInventoryDTO());
        model.addAttribute("productUpdate", new ProductInventoryUpdateDTO());
        return manageProductsService.showProducts(model);
    }

    @PostMapping(value = "addProduct")
    public String addProduct(@ModelAttribute    ProductInventoryDTO dto,
                               Model model) {

        return manageProductsService.addNewProduct(dto,model);
    }
    @PostMapping(value = "updateProduct")
    public String updateProduct(@ModelAttribute ProductInventoryUpdateDTO dto,
                             Model model) {

        return manageProductsService.updateProduct(dto,model);
    }


}
