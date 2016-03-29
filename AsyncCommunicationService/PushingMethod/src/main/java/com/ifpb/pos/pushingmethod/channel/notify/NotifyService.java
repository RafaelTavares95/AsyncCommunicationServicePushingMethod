/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ifpb.pos.pushingmethod.channel.notify;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 *
 * @author Rafael
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface NotifyService {
    @WebMethod
    @Oneway
    void notifyClient(String id);
    @WebMethod
    String listen(String id);
}
