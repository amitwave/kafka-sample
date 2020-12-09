package com.wave.kafka.model;

/**
 * Created by amit on 18/01/2020.
 */
public class User {

    private Integer id;
    private String name;
    private Long dob;
    private Preference preference;

    public User() {
    }

    public User(Integer id, String name, Long dob, Preference preference) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.preference = preference;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDob() {
        return dob;
    }

    public void setDob(Long dob) {
        this.dob = dob;
    }

    public Preference getPreference() {
        return preference;
    }

    public void setPreference(Preference preference) {
        this.preference = preference;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", preference=" + preference +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!id.equals(user.id)) return false;
        if (!name.equals(user.name)) return false;
        if (!dob.equals(user.dob)) return false;
        return preference == user.preference;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + dob.hashCode();
        result = 31 * result + preference.hashCode();
        return result;
    }
}
