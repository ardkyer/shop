package com.baeksoo.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;
    private final ItemService itemService;

    @GetMapping("/list")
    String list(Model model) {
        List<Item> result = itemRepository.findAll();
        model.addAttribute("items", result);

        return "list.html";
    }

    @GetMapping("/write")
    String write() {
        return "write.html";
    }

    @PostMapping("/add")
    String addPost(String title, Integer price) {
        itemService.saveItem(title, price);
        System.out.println(title);
        System.out.println(price);
        return "redirect:/list";
    }

    @GetMapping("/detail/{id}")
    String detail(@PathVariable Integer id, Model model){
        Optional<Item> result = itemRepository.findById(id);
        if (result.isPresent()){
            System.out.println(result.get());
            model.addAttribute("data", result.get());
        }
        return "detail.html";
    }

    @GetMapping("/edit/{id}")
    String edit(Model model, @PathVariable Integer id){

        Optional<Item> result = itemRepository.findById(id);
        if (result.isPresent()){
            model.addAttribute("data", result.get());
            return "edit.html";
        }else{
            return "redirect:/list";
        }
    }

    @PostMapping("/edit")
    String editItem(String title, Integer price, Integer id){

        Item item = new Item();
        item.setId(id);
        item.setTitle(title);
        item.setPrice(price);
        itemRepository.save(item);

        return "redirect:/list";
    }

    @PostMapping("/test1")
    String test1(@RequestBody Map<String, Object> body){
        System.out.println(body.get("name"));
        return "redirect:/list";
    }

    @DeleteMapping("/item")
    ResponseEntity<String> deleteItem(@RequestParam Integer id) {
        itemRepository.deleteById(id);
        return ResponseEntity.status(200).body("삭제완료");
    }

    @GetMapping("/test2")
    String hasing(){
        var result = new BCryptPasswordEncoder().encode("zz");
        System.out.println(result);
        return "redirect:/list";
    }


}
