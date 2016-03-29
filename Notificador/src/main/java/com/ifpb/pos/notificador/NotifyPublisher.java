/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ifpb.pos.notificador;

import javax.xml.ws.Endpoint;

/**
 *
 * @author Rafael
 */
public class NotifyPublisher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8009/notify", new NotifyServiceImpl());
        Endpoint.publish("http://localhost:8009/register", new RegisterUserImpl());
    }
    
}
