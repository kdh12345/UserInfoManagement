package com.fastcampus.javaallinone.project3.demo.repository;

import com.fastcampus.javaallinone.project3.demo.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;
    @Test
    void crud(){
        Person person=new Person();
        person.setName("martin");
        person.setAge(12);
        personRepository.save(person);
        //System.out.println(personRepository.findAll());
        List<Person> people=personRepository.findAll();
        assertThat(people.size()).isEqualTo(1);
        assertThat(people.get(0).getName()).isEqualTo("martin");
        assertThat(people.get(0).getAge()).isEqualTo(12);
    }
    @Test
    void findByBloodType(){
        givenPerson("martin",10,"A");
        givenPerson("david",9,"B");
        givenPerson("dennis",8,"O");
        givenPerson("sophid",7,"AB");

        List<Person> result=personRepository.findByBloodType("A");
        result.forEach(System.out::println);
    }

    @Test
    void findByBirthdayBetween(){
        givenPerson("martin",10,"A",LocalDate.of(1991,8,15));
        givenPerson("david",9,"B",LocalDate.of(1992,7,10));
        givenPerson("dennis",8,"O",LocalDate.of(1993,1,5));
        givenPerson("sophid",7,"AB",LocalDate.of(1994,6,30));
        givenPerson("benny",6,"A",LocalDate.of(1995,8,30));
        List<Person> result=personRepository.findByBirthdayBetween(LocalDate.of(1991,8,1),LocalDate.of(1995,8,31));
        result.forEach(System.out::println);
    }
    private void givenPerson(String name, int age, String bloodType){
        givenPerson(name,age,bloodType,null);
    }
   private void givenPerson(String name, int age, String bloodType,LocalDate birthday){
        Person person=new Person(name,age,bloodType);
        person.setBirthday(birthday);

    }
    @Test
    void constructor(){

    }
}