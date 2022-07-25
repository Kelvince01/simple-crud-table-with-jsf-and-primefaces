/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.timizatechnologies.customers.boundary;

import com.timizatechnologies.customers.control.CustomerManager;
import com.timizatechnologies.customers.entity.Customer;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author kelvi
 */
@Named
@ViewScoped
public class CustomersBacking implements Serializable {

    private List<Customer> customers;

    private Customer customer = new Customer();

    @Inject
    private CustomerManager customerManager;

    @PostConstruct
    public void init() {
        this.customers = customerManager.loadAllCustomers();
    }

    public void delete(Customer customer) {
        customerManager.delete(customer);
        customers.remove(customer);
    }

    public void add() {
        customerManager.addNewCustomer(customer);
        this.customers = customerManager.loadAllCustomers();
        this.customer = new Customer();
    }

    public void update() {
        customerManager.update(customers);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Update successful"));
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public CustomerManager getCustomerManager() {
        return customerManager;
    }

    public void setCustomerManager(CustomerManager customerManager) {
        this.customerManager = customerManager;
    }
}