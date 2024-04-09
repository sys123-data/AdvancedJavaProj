package comx.service;

import comx.dto.ProductInventoryDTO;
import comx.dto.ProductInventoryUpdateDTO;
import comx.entity.Login;
import comx.entity.Product;
import comx.entity.ProductInventory;
import comx.repository.LoginRepository;
import comx.repository.ManageProductsRepository;
import comx.repository.ProductInventoryRepository;
import comx.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class ManageProductsService {
    @Autowired
    private ManageProductsRepository manageProductsRepository;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductInventoryRepository productInventoryRepository;

    @Transactional
    public String showProducts( Model model) {
        List<ProductInventoryDTO> productInventoryList = manageProductsRepository.getProductInventory();
        model.addAttribute("productInventoryList", productInventoryList);

        return "products";

    }

    @Transactional
    public String addNewProduct(ProductInventoryDTO dto, Model model) {
        if (!dto.hasNullProperties()){
            if (insertProduct(dto))
            {
                if(insertProductInventory(dto))
                    model.addAttribute("success", "New product added");

            } else
                model.addAttribute("error", "insert failed");
        }
        else
            model.addAttribute("error", "null field not allowed");

        model.addAttribute("product", new ProductInventoryDTO());
        model.addAttribute("productUpdate", new ProductInventoryUpdateDTO());

        List<ProductInventoryDTO> productInventoryList = manageProductsRepository.getProductInventory();
        model.addAttribute("productInventoryList", productInventoryList);
        return "products";

    }

    @Transactional
    public Boolean insertProduct(ProductInventoryDTO dto) {
        try{
            Product product = new Product();
            product.setProductId(dto.getProductId());
            product.setBrand(dto.getBrand());
            product.setModel(dto.getModel());
            product.setImageUrl(dto.getImageUrl());
            product.setPrice(dto.getPrice());

            productRepository.save(product);
            return true;
        }catch (Exception e){
            e.printStackTrace(); // Handle exception appropriately (e.g., logging)
            return false;
        }

    }
    @Transactional
    public Boolean insertProductInventory(ProductInventoryDTO dto) {
       try {
           ProductInventory productInventory = new ProductInventory();
           productInventory.setProductInventoryId(dto.getProductId()+"_"+dto.getSize());
           productInventory.setProduct(productRepository.findById(dto.getProductId()).orElse(null));
           productInventory.setSize(dto.getSize());
           productInventory.setQuantity(dto.getQuantity());

           productInventoryRepository.save(productInventory);
           return true;
       }catch (Exception e){
           e.printStackTrace(); // Handle exception appropriately (e.g., logging)
           return false;
       }

    }

    @Transactional
    public String updateProduct(ProductInventoryUpdateDTO dto, Model model) {
        if (!dto.hasNullProperties()){
            if (updateProductInventory(dto))
            {
                    model.addAttribute("successUpdated", "Product updated");
            } else
                model.addAttribute("error1", "update failed");
        }
        else
            model.addAttribute("error1", "null field not allowed");

        model.addAttribute("product", new ProductInventoryDTO());
        model.addAttribute("productUpdate", new ProductInventoryUpdateDTO());

        List<ProductInventoryDTO> productInventoryList = manageProductsRepository.getProductInventory();
        model.addAttribute("productInventoryList", productInventoryList);
        return "products";

    }
    @Transactional
    public Boolean updateProductInventory(ProductInventoryUpdateDTO dto) {
        // Retrieve the product inventory entry based on product ID and size

        ProductInventory productInventory = productInventoryRepository.findByProductIdAndSize(dto.getProductId(), dto.getSize());

        if (productInventory != null) {
            // Update the quantity
            productInventory.setQuantity(dto.getNewQuantity());

            // Persist the changes
            productInventoryRepository.save(productInventory);
            return true;
        } else {
            return false;
        }
    }
}
