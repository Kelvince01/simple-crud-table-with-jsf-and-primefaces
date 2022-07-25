/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.timizatechnologies.customers.control;

import com.timizatechnologies.customers.entity.Customer;

/**
 *
 * @author kelvi
 */
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;

@Stateless
public class CustomerManager {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Customer> loadAllCustomers() {
        return this.entityManager.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
    }

    public void delete(Customer customer) {
        if (entityManager.contains(customer)) {
            entityManager.remove(customer);
        } else {
            Customer managedCustomer = entityManager.find(Customer.class, customer.getId());
            if (managedCustomer != null) {
                entityManager.remove(managedCustomer);
            }
        }
    }

    public void addNewCustomer(Customer customer) {

        Customer newCustomer = new Customer();
        newCustomer.setDayOfBirth(customer.getDayOfBirth());
        newCustomer.setEmail(customer.getEmail());
        newCustomer.setFirstName(customer.getFirstName());
        newCustomer.setLastName(customer.getLastName());
        newCustomer.setCustomerId(UUID.randomUUID().toString().substring(0, 8));

        this.entityManager.persist(newCustomer);
    }

    public void update(List<Customer> customers) {
        customers.forEach(entityManager::merge);
    }
}