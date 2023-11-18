package com.lostnfound.modal_classes;

public class UserRegistration {
  public String fname,lname, email, phone,password, cpassword , role;

    public UserRegistration() {
    }

    public UserRegistration(String fname, String lname, String email, String phone, String password, String cpassword, String role) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.cpassword = cpassword;
        this.role = role;
    }
}
