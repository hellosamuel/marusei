package com.github.smdj.marusei.controller;


import com.github.smdj.marusei.security.AccountDetails;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/articles")
public interface ArticleController {
    @GetMapping("/create")
//    @PreAuthorize("hasRole('ROLE_USER')")
    String createForm();

    @PostMapping("/preview")
    String preview(AccountDetails accountDetails);

    @PostMapping()
    String create();

    @GetMapping("/{id:\\d+}")
    String detail(@PathVariable("id") long id);

    @GetMapping("/{id:\\d+}/edit")
    String editForm(@PathVariable("id") long id);

    @PatchMapping("/{id:\\d+}")
    String edit(@PathVariable("id") long id);

    @DeleteMapping("{id:\\d+}")
    String delete(@PathVariable("id") long id);
}
