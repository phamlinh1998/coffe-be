package com.example.coffeebe.domain.services.impl.author;

import com.example.coffeebe.domain.entities.author.Role;
import com.example.coffeebe.domain.entities.enums.RoleType;

import com.example.coffeebe.domain.services.impl.BaseAbtractService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService extends BaseAbtractService {

    public Role findByRoleType(String roleType) throws Exception {
        return roleRepository.findByName(roleType).orElseThrow(() -> new Exception("Role not exist"));
    }

    public void initRole(){
        List<Role> roles = new ArrayList();
        roles.add(Role.builder().name(RoleType.USER).build());
        roles.add(Role.builder().name(RoleType.ADMIN).build());
        roleRepository.saveAll(roles);
    }
}
