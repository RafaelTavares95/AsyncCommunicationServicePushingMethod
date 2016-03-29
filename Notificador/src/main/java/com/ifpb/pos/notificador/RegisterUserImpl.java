/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ifpb.pos.notificador;

import javax.jws.WebService;

/**
 *
 * @author Rafael
 */
@WebService(endpointInterface = "com.ifpb.pos.notificador.RegisterUser", serviceName = "RegisterUser",
        targetNamespace = "http://notificador.pos.ifpb.com/", portName = "RegisterUserPort")
public class RegisterUserImpl implements RegisterUser{

    @Override
    public boolean registerListener(String userId) {
        System.out.println("Registrando Usuario com id " + userId);
        while (!RepositoryNotifications.getInstance().containsNoteToId(userId)) 
        RepositoryNotifications.getInstance().removeNote(userId);
        System.out.println("Enviando Notificação para cliente "+userId);
        return true;    
    }
    
}
