package vn.hoidanit.jobhunter.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.domain.dto.Meta;
import vn.hoidanit.jobhunter.domain.dto.ResUserDTO;
import vn.hoidanit.jobhunter.domain.dto.ResultPagingationDTO;
import vn.hoidanit.jobhunter.repository.UserRepository;
import  org.springframework.data.domain.Page;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User updateUser(User user) {
        User oldUser = userRepository.findById(user.getId()).orElse(null);
        if (oldUser == null) {
            return null;
        }
        oldUser.setName(user.getName());
        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(user.getPassword());
        return userRepository.save(oldUser);

    }

    public User getUserByUsername(String username) {
        return userRepository.findByEmail(username);
    }

    public ResultPagingationDTO getUsers(Specification<User> spec,  Pageable pageable) {
        Page<User> pageUser = userRepository.findAll(spec,pageable);
        ResultPagingationDTO result = new ResultPagingationDTO();
        Meta meta = new Meta();

        meta.setPage(1);
        // meta.setSize(users.size());
        meta.setPages(1);
        // meta.setTotal(users.size());
        result.setMeta(meta);
        // result.setResult(users);

        // List<ResUserDTO> listUser = pageUser.getContent().stream().map(item -> new ResUserDTO( item.getId(),item.getEmail(),item.getGender(),item.getName())).collect(Collectors.toList()); 
        return result;
    }

    public ResUserDTO convertTResUserDTO(User user){
        ResUserDTO res =  new ResUserDTO();
        res.setId(user.getId());
        res.setEmail(user.getEmail());
        res.setGender(user.getGender());
        res.setName(user.getName());
        return res;
    }
}
