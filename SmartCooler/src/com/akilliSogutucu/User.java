package com.smartCooler;

public class User {

    private int id;
    private String name;
    private String password;
    private int age;

    public User(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.password = builder.password;
        this.age = builder.age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "User {" +
                " name = " + name +
                " , yaş = " + age +
                '}';
    }

    public static class Builder  {
        private int id;
        private String name;
        private String password;
        private int age;

        public Builder(String name, String password){
            this.name = name;
            this.password = password;
        }

        public Builder setId(int id){
            this.id = id;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public User build(){
            return new User(this);
        }
    }
}
