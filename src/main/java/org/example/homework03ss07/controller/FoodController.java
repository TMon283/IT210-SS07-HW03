package org.example.homework03ss07.controller;

import org.example.homework03ss07.model.Food;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FoodController {

    public static List<Food> foodList = new ArrayList<>();

    @PostMapping("/addFood")
    public String addFood(@ModelAttribute Food food,
                          @ModelAttribute("image") MultipartFile file) throws IOException {

        if (file.isEmpty()) {
            throw new RuntimeException("Bạn phải đính kèm ảnh!");
        }

        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null ||
                !(originalFilename.endsWith(".jpg") || originalFilename.endsWith(".png") || originalFilename.endsWith(".jpeg"))) {
            throw new RuntimeException("Sai định dạng file! Chỉ chấp nhận JPG, PNG, JPEG.");
        }

        if (food.getPrice() < 0) {
            throw new RuntimeException("Giá tiền phải >= 0!");
        }

        String uploadDir = "C:/RikkeiFood_Temp/";
        File destination = new File(uploadDir + originalFilename);
        file.transferTo(destination);

        food.setImagePath(destination.getAbsolutePath());

        foodList.add(food);

        System.out.println("Đã thêm món: " + food);
        System.out.println("Tổng số món hiện có: " + foodList.size());

        return "success";
    }

    @GetMapping("/addFood")
    public String showForm() {
        return "food-form";
    }
}