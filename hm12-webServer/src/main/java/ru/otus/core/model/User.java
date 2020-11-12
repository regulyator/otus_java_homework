package ru.otus.core.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    private AddressDataSet addressDataSet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<PhoneDataSet> phoneDataSet;

    public User() {
    }

    public User(long id, String username, String password, AddressDataSet addressDataSet, List<PhoneDataSet> phoneDataSet) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.addressDataSet = addressDataSet;
        this.phoneDataSet = phoneDataSet;
        updatePhonesUserParent(this.phoneDataSet);
    }

    public User(String username, String password, AddressDataSet addressDataSet, List<PhoneDataSet> phoneDataSet) {
        this(0, username, password, addressDataSet, phoneDataSet);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AddressDataSet getAddressDataSet() {
        return addressDataSet;
    }

    public void setAddressDataSet(AddressDataSet addressDataSet) {
        this.addressDataSet = addressDataSet;
    }

    public List<PhoneDataSet> getPhoneDataSet() {
        return phoneDataSet;
    }

    public void setPhoneDataSet(List<PhoneDataSet> phoneDataSet) {
        this.phoneDataSet = phoneDataSet;
        updatePhonesUserParent(this.phoneDataSet);
    }

    protected void updatePhonesUserParent(List<PhoneDataSet> phoneDataSet) {
        phoneDataSet.forEach(x -> x.setUser(this));
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + username + '\'' +
                ", addressDataSet=" + addressDataSet +
                ", phoneDataSet=" + phoneDataSet +
                '}';
    }
}
