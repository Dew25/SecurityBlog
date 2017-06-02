/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import entyty.Group;
import java.util.List;

/**
 *
 * @author Melnikov
 */
public class Role {
    private String role;
    public Role(List<Group> groups){
        setRole(groups);
    }

    private void setRole(List<Group> groups) {
        for (int i = 0; i < groups.size(); i++) {
            Group group = groups.get(i);
            switch (group.getGroupName()) {
                case "ADMINS": 
                    role="ADMIN";
                    break;
                case "USERS": 
                    role="USERS";
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }
}
