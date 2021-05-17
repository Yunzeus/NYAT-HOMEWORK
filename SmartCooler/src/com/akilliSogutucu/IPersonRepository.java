package com.smartCooler;
import java.util.List;

public interface IPersonRepository {
    public User search(int id);
    public boolean userVerify(User user);
    public List<User> allUsers();
    public void save(User user);
    public void delete(int id);
    public void change(User user);
}
