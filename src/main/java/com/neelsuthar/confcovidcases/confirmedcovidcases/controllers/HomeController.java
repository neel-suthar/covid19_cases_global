package com.neelsuthar.confcovidcases.confirmedcovidcases.controllers;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

import com.neelsuthar.confcovidcases.confirmedcovidcases.models.CovidStats;
import com.neelsuthar.confcovidcases.confirmedcovidcases.services.CovidDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @Autowired
  CovidDataService covidDataService;

  @GetMapping("/")
  public String home(Model model) {
    List<CovidStats> covidStats = covidDataService.getCurrentStats();
    int currentTotalCases = covidStats.stream().mapToInt(covidStat -> covidStat.getCurrentCasesInt()).sum();

    int difTotalFromPrevDay = covidStats.stream().mapToInt(covidStat -> covidStat.getDifFromPrevDayInt()).sum();

    model.addAttribute("difTotalFromPrevDay", CovidStats.formatNumber(difTotalFromPrevDay));
    model.addAttribute("currentTotalCases", CovidStats.formatNumber(currentTotalCases));
    model.addAttribute("covidStats", covidDataService.getCurrentStats());
    return "home";
  }
}
