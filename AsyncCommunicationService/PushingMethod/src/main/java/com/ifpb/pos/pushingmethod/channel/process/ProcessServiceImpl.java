/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ifpb.pos.pushingmethod.channel.process;

import com.ifpb.pos.pushingmethod.Repository;
import javax.jws.WebService;

/**
 *
 * @author Rafael
 */
@WebService(endpointInterface = "com.ifpb.pos.pushingmethod.channel.process.ProcessService")
public class ProcessServiceImpl implements ProcessService{
    
    @Override
    public void processCall(String id, String message) {
        //Essa parte de registrar o cliente aqui é para aproveirar a estrutura, 
        //apenas para simular já que nessa nova visão o cliente já vai ter o seu id
        Repository.getInstance().getClientesRegistrados().add(id);
        //
        if(Repository.getInstance().getClientesRegistrados().contains(id)){
            Repository.getInstance().getFilaDeRequisicoes().offer(id+ ":" + message);
            System.out.println("[Colocando na fila a requisição do cliente com TokenId: "+ id.toUpperCase() + "]");
        }
    }
    
}
