package com.ty.lms.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@SecurityRequirement(name = "javainuseapi")
@RequestMapping(path = "/api/v1/admins")
public class AdminController {

}
