package com.example.TestSecurity.controller;

import com.example.TestSecurity.converter.Choice;
import com.example.TestSecurity.model.History;
import com.example.TestSecurity.model.Valute;
import com.example.TestSecurity.repos.HistoryRepo;
import com.example.TestSecurity.repos.ValuteRepo;
import com.example.TestSecurity.service.XMLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * @author karpeykin
 * @Date 25.01.2021
 */
@Controller
public class MainController {

    @Autowired
    private ValuteRepo valuteRepo;
    @Autowired
    private HistoryRepo historyRepo;

    @GetMapping
    public String viewValute(Model model) {
        List<Valute> valutes;
        String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());

        valutes = valuteRepo.findByDate(date);

        if (valutes.isEmpty()) {
            valutes = XMLService.parseVaeCurs(date);
            valuteRepo.saveAll(valutes);
        }
        Collections.sort(valutes, Comparator.comparing(Valute::getName));
        Iterable<History> histories = historyRepo.findAll();
        model.addAttribute("valutes", valutes);
        model.addAttribute("histories", histories);
        return "choiceValute";
    }

    @PostMapping("/convert")
    public String choiceValute(@RequestParam String valuteFrom,
                               @RequestParam String valuteTo,
                               @RequestParam Integer quantity,
                               Model model) {
        List<Valute> valutes;

        String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        valutes = valuteRepo.findByDate(date);

        Valute valuteFromDb = valuteRepo.findByCharCodeAndDate(valuteFrom, date);
        Valute valuteToDb = valuteRepo.findByCharCodeAndDate(valuteTo, date);

        Double result = Choice.converted(valuteFromDb, valuteToDb, quantity);
        Collections.sort(valutes, Comparator.comparing(Valute::getName));

        model.addAttribute("valutes", valutes);
        model.addAttribute("result", result);
        model.addAttribute("quantity", quantity);

        History history = new History(valuteFrom + " (" + valuteFromDb.getName() + ")",
                valuteTo + " (" + valuteToDb.getName() + ")", quantity, result, date);
        historyRepo.save(history);

        Iterable<History> histories = historyRepo.findAll();
        model.addAttribute("histories", histories);

        return "choiceValute";
    }

    @PostMapping("/filter")
    public String viewValuteFilter(@RequestParam(defaultValue = "") String filterDate,
                                   @RequestParam String filterValuteFrom,
                                   @RequestParam String filterValuteTo,
                                   Model model) throws ParseException {
        List<Valute> valutes;
        Iterable<History> histories;

        String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        String filterdate = new SimpleDateFormat("dd/MM/yyyy")
                .format(new SimpleDateFormat("yyyy-MM-dd").parse(filterDate));

        valutes = valuteRepo.findByDate(date);
        Collections.sort(valutes, Comparator.comparing(Valute::getName));
        model.addAttribute("valutes", valutes);


        if (!filterdate.isEmpty() || !filterValuteFrom.isEmpty() || !filterValuteTo.isEmpty()){
            histories = historyRepo.findByValuteFromLikeAndValuteToLikeAndDateLike(filterValuteFrom + "%", filterValuteTo + "%", filterdate + "%");
        }
        else {
            histories = historyRepo.findAll();
        }

        model.addAttribute("histories", histories);
        return "choiceValute";
    }

}
