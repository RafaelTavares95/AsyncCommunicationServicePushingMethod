/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ifpb.pos.notificador;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Rafael
 */
public class RepositoryNotifications {
    private Set<String> notifications;
    private boolean have;
    public static RepositoryNotifications instance;

    private RepositoryNotifications() {
        notifications = new HashSet<>();
        have = false;
    }
    
    public static RepositoryNotifications getInstance(){
        if (instance == null) {
            instance = new RepositoryNotifications();
	}
        return instance;
    }
    
    public void addNote(String id){
        notifications.add(id);
    }
    
    public void removeNote(String id){
        notifications.remove(id);
    }
    
    public boolean containsNoteToId(String id){
        return have;
    }

    public void setHave(boolean have) {
        this.have = have;
    }
}
