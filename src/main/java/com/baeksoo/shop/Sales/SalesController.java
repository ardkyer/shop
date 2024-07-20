package com.baeksoo.shop.Sales;

import com.baeksoo.shop.member.CustomUser;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SalesController {

    private final SalesRepository salesRepository;

    public SalesController(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    @PostMapping("/order")
    public String postOrder(@RequestParam String title,
                            @RequestParam Integer price,
                            @RequestParam Integer count,
                            Authentication auth,
                            RedirectAttributes redirectAttributes) {
        Sales sales = new Sales();
        sales.setItemName(title);
        sales.setPrice(price);
        sales.setCount(count);
        CustomUser user = (CustomUser) auth.getPrincipal();
        sales.setMemberId(Long.valueOf(user.getId()));
        salesRepository.save(sales);

        redirectAttributes.addFlashAttribute("message", "주문이 성공적으로 처리되었습니다.");
        return "redirect:/sales-list";  // URL 변경
    }

    @GetMapping("/sales-list")  // URL 변경
    public String getSalesList(Model model) {
        model.addAttribute("salesList", salesRepository.findAll());
        return "sales-list";  // 뷰 이름 변경
    }
}