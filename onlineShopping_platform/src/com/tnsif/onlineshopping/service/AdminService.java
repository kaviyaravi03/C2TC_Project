package com.tnsif.onlineshopping.service;

import com.tnsif.onlineshopping.entity.Admin;
import java.util.ArrayList;
import java.util.List;

public class AdminService {
    private static List<Admin> admins = new ArrayList<>();

    public void addAdmin(Admin admin) {
        admins.add(admin);
    }

    public List<Admin> getAllAdmins() {
        return admins;
    }
}
//Test commit