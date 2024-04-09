package comx.controller;

import comx.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;

@Controller
@RequestMapping("admin")
public class FilterOrdersController {
    @Autowired
    OrderService orderService;




    @GetMapping(value = "filterOrders")
    public String filterOrders(@RequestParam() String category,
                                 @RequestParam() String startTime,
                                 @RequestParam() String endTime,
                                 Model model) {

        return orderService.getFilteredOrders(startTime,endTime,category, model);
    }


}
