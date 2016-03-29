/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ifpb.pos.pushingmethod.channel.process;

import com.ifpb.pos.notificator.NotifyService;
import com.ifpb.pos.pushingmethod.Repository;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
 *
 * @author Rafael
 */
public class ProcessServiceBackground {
    private static final long TEMPO = (300 * 60);
    
    public void process() throws InterruptedException{
        Timer timer = new Timer();
        TimerTask tarefa = new TimerTask() {
            public void run() {
                try {
                    if(!Repository.getInstance().getFilaDeRequisicoes().isEmpty()){
                        String poll = (String) Repository.getInstance().getFilaDeRequisicoes().poll();
                        //Só pra simular o processamento
                        String[] split = poll.split(":");
                        String id= split[0];
                        String msg= split[1];
                        System.out.println("[Retirando da fila o cliente com o TokenId: " + id.toUpperCase() + "]");
                        System.out.println("[Processando tarefa do cliente com o TokenId: " + id.toUpperCase() + "]");
                        Thread.sleep(100 * 60);
                        Long time = System.currentTimeMillis();
                        String ts = time.toString();
                        //
                        Repository.getInstance().getResponseRepository().put(id, msg+ts);
                        System.out.println("[Colocando na lista de respostas a mensagem do "
                                + "cliente com o TokenId: " + id.toUpperCase() + "]");
                        notifyClient(id);
                        System.out.println("[Registrando para notitificação o "
                                + "cliente com o TokenId: " + id.toUpperCase() + "]");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        timer.scheduleAtFixedRate(tarefa, 0, TEMPO);
        
        
    }
    public static void notifyClient(String id) throws MalformedURLException{
        URL url=new URL("http://localhost:8009/notify?wsdl");
        QName qName=new QName("http://notificador.pos.ifpb.com/", 
                "NotifyService");
        Service service=Service.create(url,qName);
        NotifyService notify = service.getPort(NotifyService.class);
        notify.sendResponse(id);
    }
}
