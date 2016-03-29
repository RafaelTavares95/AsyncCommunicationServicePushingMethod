/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ifpb.pos.pushingmethod.channel.notify;

import com.ifpb.pos.pushingmethod.Repository;
import javax.jws.WebService;

/**
 *
 * @author Rafael
 */
@WebService(endpointInterface = "com.ifpb.pos.pushingmethod.channel.notify.NotifyService")
public class NotifyServiceImpl implements NotifyService{

    @Override
    public void notifyClient(String id) {
        Repository.getInstance().getClientesForNote().add(id);
        System.out.println("[Adicionando a lista para ser notificado o cliente com TokenId: " + id.toUpperCase() + "]");
    }

    @Override
    public String listen(String id) {
        if(Repository.getInstance().getClientesForNote().contains(id)){
            System.out.println("[Notificando o cliente com TokenId: " + id.toUpperCase() + "]");
            return "tem resposta";
        }
        return "";
    }
    
}
