package org.launchcode.spaday.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;


@Controller
public class SpaDayController {

    public boolean checkSkinType(String skinType, String facialType) {
        if (skinType.equals("Oily")) {
            if (facialType.equals("Dead Sea Salt Microdermabrasion") || facialType.equals("Volcanic Clay Mud Rejuvination")) {
                return true;
            }
            else {
                return false;
            }
        }
        else if (skinType.equals("Combination")) {
            if (facialType.equals("Dead Sea Salt Microdermabrasion") || facialType.equals("Volcanic Clay Mud Rejuvination") || facialType.equals("Summer Citrus Enzyme Exfoliation")) {
                return true;
            }
            else {
                return false;
            }
        }
        else if (skinType.equals("Normal")) {
            return true;
        }
        else if (skinType.equals("Dry")) {
            if (facialType.equals("Volcanic Clay Mud Rejuvination") || facialType.equals("Manuka Honey Hydrofacial")) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return true;
        }
    }

    @GetMapping(value="")
    @ResponseBody
    public String customerForm () {
        String html = "<form method = 'post'>" +
                "Name: <br>" +
                "<input type = 'text' name = 'name'>" +
                "<br>Skin type: <br>" +
                "<select name = 'skintype'>" +
                "<option value = 'Oily'>Oily</option>" +
                "<option value = 'Combination'>Combination</option>" +
                "<option value = 'Normal'>Normal</option>" +
                "<option value = 'Dry'>Dry</option>" +
                "</select><br>" +
                "Manicure or Pedicure? <br>" +
                "<select name = 'manipedi'>" +
                "<option value = 'Manicure'>Manicure</option>" +
                "<option value = 'Pedicure'>Pedicure</option>" +
                "<option value = 'Both'>Both</option>" +
                "</select><br>" +
                "<input type = 'submit' value = 'Submit'>" +
                "</form>";
        return html;
    }

    @PostMapping(value="")
    public String spaMenu(@RequestParam String name, @RequestParam String skintype, @RequestParam String manipedi, Model model) {

        model.addAttribute("name", name);
        model.addAttribute("skintype",skintype);
        model.addAttribute("manipedi",manipedi);

        ArrayList<String> facials = new ArrayList<String>();
        facials.add("Dead Sea Salt Microdermabrasion");
        facials.add("Manuka Honey Hydrofacial");
        facials.add("Volcanic Clay Mud Rejuvination");
        facials.add("Summer Citrus Enzyme Exfoliation");

        ArrayList<String> appropriateFacials = new ArrayList<String>();
        for (int i = 0; i < facials.size(); i ++) {
            if (checkSkinType(skintype,facials.get(i))) {
                appropriateFacials.add(facials.get(i));
            }
        }

        model.addAttribute("appropriateFacials", appropriateFacials);

        return "menu";
    }
}
