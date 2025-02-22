package ru.romanov.finder.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.romanov.finder.service.XlsxService;

@RestController
@RequestMapping("/api")
public class XlsxController {

    private final XlsxService xlsxService;

    public XlsxController(XlsxService xlsxService) {
        this.xlsxService = xlsxService;
    }

    @GetMapping("/xlsx")
    public Integer findMax(@RequestParam String filePath,
                           @RequestParam Integer n) {
        return xlsxService.findMax(filePath, n);
    }
}
