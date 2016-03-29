/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ifpb.pos.notificador;

import javax.jws.Oneway;
import javax.jws.WebService;

/**
 *
 * @author Rafael
 */
@WebService(endpointInterface = "com.ifpb.pos.notificador.NotifyService", serviceName = "NotifyService",
        targetNamespace = "http://notificador.pos.ifpb.com/", portName = "NotifyServicePort" )
public class NotifyServiceImpl implements NotifyService{    
    
    @Override
    @Oneway
    public void sendResponse(String userId) {
        RepositoryNotifications.getInstance().addNote(userId);
        RepositoryNotifications.getInstance().setHave(true);
        System.out.println("Recebendo do provider o aviso - " + userId);
    }
    
}
