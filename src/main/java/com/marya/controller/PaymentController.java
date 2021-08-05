package com.marya.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marya.service.PaymentService;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ManagedBean
@RequestScoped
@Component
public class PaymentController {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void pay() throws JsonProcessingException {
        String cart = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("cart");
        List<Long> productIdList = new ArrayList<>();
        if (cart.length() >  0 && !cart.contains(","))
            productIdList.add(Long.valueOf(cart));
        else
            productIdList.addAll(Arrays.asList(cart.split(",")).stream().map(Long::valueOf).collect(Collectors.toList()));
        paymentService.pay(productIdList);
    }
}
