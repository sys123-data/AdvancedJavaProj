package comx.service;

import comx.entity.Order;
import comx.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrdersRepository ordersRepository;
    @Transactional
    public String getFilteredOrders(String startTime, String endTime, String size, Model model) {
        Integer sizex;
        try {
            sizex = Integer.valueOf(size);
        }catch (NumberFormatException e){
            model.addAttribute("error", "NumberFormatException");
            return "orders";
        }

        Date endDate;
        Date startDate;
        Date currentDate;
        try{
            startDate = Date.valueOf(LocalDate.parse(startTime, DateTimeFormatter.ISO_LOCAL_DATE));
            endDate = Date.valueOf(LocalDate.parse(endTime, DateTimeFormatter.ISO_LOCAL_DATE));
             currentDate = Date.valueOf(LocalDate.now());
        }catch (Exception e )
        {
            model.addAttribute("error", "Exception Time");
            return "orders";
        }


        if (sizex>46|| sizex<33){
            model.addAttribute("error", "size>46|| size<33");

            return "orders";

        }
        else if (endDate.compareTo(currentDate)>0){

            model.addAttribute("error", "End time cannot be greater than today's date.");
            return "orders";

        }
        else if(startTime != null && !startTime.matches("\\d{4}-\\d{2}-\\d{2}") ||
                endTime != null && !endTime.matches("\\d{4}-\\d{2}-\\d{2}")){

                model.addAttribute("error", "Invalid date format. Please use yyyy-mm-dd format.");
            return "orders";
        }
        else if (startTime != null && endTime != null && startDate.compareTo(endDate)>0){
            model.addAttribute("error", "Start time cannot be greater than end time.");
            return "orders";

        }
        else{



            LocalDate endDateLocalDate = LocalDate.parse(endTime, DateTimeFormatter.ISO_LOCAL_DATE);
            endDateLocalDate = endDateLocalDate.plusDays(1); // Add one day
            endDate = Date.valueOf(endDateLocalDate);
            List<Order> filteredOrders = ordersRepository.findByPurchaseTimeBetweenAndProdInvIdEndingWith(startDate, endDate, size);


            if(filteredOrders.size()>0){
                model.addAttribute("orders", filteredOrders);


            }else {
                model.addAttribute("error", "Filter did not found orders");

            }

        }
        return "orders";


    }
}
