package ru.otus.data.core.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private AddressDataSet addressDataSet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<PhoneDataSet> phoneDataSet;

    public User() {
    }

    public User(long id, String name, AddressDataSet addressDataSet, List<PhoneDataSet> phoneDataSet) {
        this.id = id;
        this.name = name;
        this.addressDataSet = addressDataSet;
        this.phoneDataSet = phoneDataSet;
        updatePhonesUserParent(this.phoneDataSet);
    }

    public User(String name, AddressDataSet addressDataSet, List<PhoneDataSet> phoneDataSet) {
        this(0, name, addressDataSet, phoneDataSet);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                ", name='" + name + '\'' +
                ", addressDataSet=" + addressDataSet +
                ", phoneDataSet=" + phoneDataSet +
                '}';
    }
}
