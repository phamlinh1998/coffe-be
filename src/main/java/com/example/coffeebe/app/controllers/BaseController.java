package com.example.coffeebe.app.controllers;

import com.example.coffeebe.app.dtos.request.DTO;
import com.example.coffeebe.app.dtos.request.FilterDto;
import com.example.coffeebe.app.dtos.responses.CustomPage;
import com.example.coffeebe.domain.services.BaseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public abstract class BaseController<O, ID, P1, FD extends FilterDto<O>> {
    final Class<P1> responseClass;
    final Class<FD> filterDto;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    BaseService<O, ID> service;

    protected BaseController(Class<P1> responseClass, Class<FD> fpClass) {
        this.responseClass = responseClass;
        this.filterDto = fpClass;
    }

    @GetMapping("/all")
    CustomPage<P1> findAll(Pageable pageable) {
        CustomPage<O> page = service.findAll(pageable);
        CustomPage<P1> customPage = new CustomPage<>();
        customPage.setData(page.getData().stream().map(ele -> modelMapper.map(ele, responseClass)).collect(Collectors.toList()));
        customPage.setMetadata(new CustomPage.Metadata(page.getMetadata().getPage(), page.getMetadata().getSize(),
                page.getMetadata().getTotal(), page.getMetadata().getTotalPage()));
        return customPage;
    }

    @GetMapping("/{id}")
    P1 getById(HttpServletRequest request, @PathVariable ID id) {
        O ob = service.findById(request, id);
        return modelMapper.map(ob, responseClass);
    }

    @PostMapping()
    P1 create(HttpServletRequest request, @Valid @RequestBody DTO dto) {
        O ob = service.create(request, dto);
        return modelMapper.map(ob, responseClass);
    }

    @PatchMapping("/{id}")
    P1 update(HttpServletRequest request, @PathVariable ID id, @Valid @RequestBody DTO dto) {
        O ob = service.update(request, id, dto);
        return modelMapper.map(ob, responseClass);
    }

    @DeleteMapping("/{id}")
    boolean delete(HttpServletRequest request, @PathVariable ID id) {
        return service.delete(request, id);
    }

    @GetMapping("filter")
    CustomPage<P1> getAllByFilter(FD dto, Pageable pageable) {
        Page<O> oPage = service.findAllByFilter(dto, pageable);
        CustomPage<P1> p1CustomPage = new CustomPage<>();
        p1CustomPage.setData(oPage.getContent().stream().map(ele -> modelMapper.map(ele, responseClass)).collect(Collectors.toList()));
        p1CustomPage.setMetadata(new CustomPage.Metadata(oPage));
        return p1CustomPage;
    }
}
