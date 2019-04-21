package org.third.common;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Email {
    private String id = "Email_01";

    @Override
    public boolean equals(Object obj) {
        Email that = (Email) obj;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        int result = 2;
        result = 31 * result + id.hashCode();
        return result;
    }
}

class Address {
    private String id = "Address_01";

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        Address that = (Address) obj;
        return id.equals(that.id);
    }
}

class User {
    Email email;
    Address address;

    public User(Email e1, Address a1) {
        this.email = e1;
        this.address = a1;
    }

    @Override
    public int hashCode() {
        return email.hashCode() + address.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        User that = (User) obj;
        return email.equals(that.email) && address.equals(that.address);
    }
}

public class HashcodeTest {
    public static void main(String[] args) {
        Email e1 = new Email();
        Address a1 = new Address();
        User u1 = new User(e1, a1);

        /////////
        Email e2 = new Email();
        Address a2 = new Address();
        User u2 = new User(e2, a2);

        Set<User> users = new HashSet<User>();
        users.add(u1);
        users.add(u2);

        System.out.println(users);
        System.out.println(users.contains(u1));
        System.out.println(users.contains(u2));

        Map<Email, Object> emails = new HashMap<Email, Object>();
        emails.put(e1, "23");
        emails.put(e2, "23");
        System.out.println(emails);
    }

}
