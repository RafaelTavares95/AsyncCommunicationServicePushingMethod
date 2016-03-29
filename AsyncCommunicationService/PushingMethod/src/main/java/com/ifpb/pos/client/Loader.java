/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ifpb.pos.client;

import com.ifpb.pos.pushingmethod.channel.notify.NotifyService;
import com.ifpb.pos.pushingmethod.channel.process.ProcessService;
import com.ifpb.pos.pushingmethod.channel.notify.RegisterService;
import com.ifpb.pos.pushingmethod.channel.response.ResponseService;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
 *
 * @author Rafael
 */
public class Loader {    
    public static void main(String[] args) throws MalformedURLException, InterruptedException {        
        String token = notifyRegister("c3po");
        sendMessage(token, "ae carai ");
        System.out.println(getResponse(token));
        /**
         * se registra no notificador (fica com o canal aberto)(s)c-n
         * 
         * envia a mensagem para o provedor (a)c-p
         * 
         * provedor executa tarefa e diz para o notificador que concluiu(a)p-n
         * 
         * notificador avisa ao cliente no canal aberto (s)n-c
         * 
         * o cliente vai buscar a informação (s) c-p
         * 
         */
    }
    
    private static String notifyRegister(String id) throws MalformedURLException{
        URL url=new URL("http://localhost:8009/register?wsdl");
        QName qName=new QName("http://notify.channel.pushingmethod.pos.ifpb.com/", 
                "RegisterServiceImplService");
        Service service=Service.create(url,qName);
        RegisterService register=service.getPort(RegisterService.class);
        
        return register.registerMyself(id);
    }
    
    private static String listenChannel(String id) throws MalformedURLException{
        URL url=new URL("http://localhost:8009/notify?wsdl");
        QName qName=new QName("http://notify.channel.pushingmethod.pos.ifpb.com/", 
                "NotifyServiceImplService");
        Service service=Service.create(url,qName);
        NotifyService register=service.getPort(NotifyService.class);
        
        return register.listen(id);
    }
    
    private static void sendMessage(String token, String msg) throws MalformedURLException{
        URL url=new URL("http://localhost:8008/process?wsdl");
        QName qName=new QName("http://process.channel.pushingmethod.pos.ifpb.com/", 
                "ProcessServiceImplService");
        Service service=Service.create(url,qName);
        ProcessService process = service.getPort(ProcessService.class);
        process.processCall(token, msg);
    }
    
    private static String getResponse(String token) throws MalformedURLException, InterruptedException{
        URL url=new URL("http://localhost:8008/response?wsdl");
        QName qName=new QName("http://response.channel.pushingmethod.pos.ifpb.com/", 
                    "ResponseServiceImplService");
        Service service=Service.create(url,qName);
        ResponseService response = service.getPort(ResponseService.class);
        while (listenChannel(token).equals("")) {
        }
        return response.getResponse(token);
    }
}
