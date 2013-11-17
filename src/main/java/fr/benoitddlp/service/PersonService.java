package fr.benoitddlp.service;

import fr.benoitddlp.entity.Person;

import java.util.List;

public interface PersonService {
    public boolean save(Person person);
    public List<Person> getAll();
    public Person getById(int id);
    public boolean delete(Person person);
    public boolean update(Person person);
    public Person findPerson(Person person);
}
