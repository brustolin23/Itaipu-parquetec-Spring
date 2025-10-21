package com.exercicios.estudos.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exercicios.estudos.models.User;
import com.exercicios.estudos.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    public User findById(Long id) {
        Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException("Usuário não encontrado! Id:"+id+", Tipo: "+User.class.getName()));
    }

    @Transactional
    public User create(User obj){
        obj.setId(null);//garante que um id não seja inserido de propósito para alterar um usuário já existente
        obj = this.userRepository.save(obj);
        return obj;
    }

    @Transactional
    public User update(User obj){//atualiza apenas a senha passada pelo usuário
        User newObj = findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        return this.userRepository.save(newObj);
    }

    public void delete(Long id){//não apaga usuários que possuem tarefas
        findById(id);
        try {
            this.userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir o usuário pois há entidades relacionadas a ele.");
        }
    }

}
