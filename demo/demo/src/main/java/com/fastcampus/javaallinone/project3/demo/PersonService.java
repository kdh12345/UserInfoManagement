package com.fastcampus.javaallinone.project3.demo;

import com.fastcampus.javaallinone.project3.demo.domain.Block;
import com.fastcampus.javaallinone.project3.demo.domain.Person;
import com.fastcampus.javaallinone.project3.demo.repository.BlockRepository;
import com.fastcampus.javaallinone.project3.demo.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@Service
@Slf4j
public class PersonService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private BlockRepository blockRepository;

    public List<Person> getPeopleExcludeBlocks(){
        //List<Person> people=personRepository.findAll();
       // return people.stream().filter(person -> person.getBlock()==null)
        //        .collect(Collectors.toList());
        return personRepository.findByBlockIsNull();
    }
    public List<Person> getPeopleByName(String name){

        return personRepository.findByName(name);
    }
    @Transactional(readOnly = true)
    public Person getPerson(Long id){
        Person person=personRepository.findById(id).orElse(null);
        log.info("person: {}",person);
        return person;
    }
    @Transactional
    public void put(Person person){
        personRepository.save(person);
    }
    @Transactional
    public void modify(Long id,Person person){
        Person personAtDb = personRepository.findById(id).orElseThrow(()-> new RuntimeException("아이디가 존재하지 않습니다."));
        personAtDb.setName(person.getName());
        personAtDb.setPhoneNumber(person.getPhoneNumber());
        personAtDb.setJob(person.getJob());
        personAtDb.setBirthday(person.getBirthday());
        personAtDb.setName(person.getName());
        personAtDb.setAddress(person.getAddress());
        personAtDb.setPhoneNumber(person.getPhoneNumber());
        personAtDb.setBloodType(person.getBloodType());
        personAtDb.setHobby(person.getHobby());
        personAtDb.setAge(person.getAge());
        personRepository.save(personAtDb);

    }

}
