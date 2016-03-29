/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ifpb.pos.notificator;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;



/**
 *
 * @author Rafael
 */
@WebService(targetNamespace = "http://notificador.pos.ifpb.com/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface NotifyService {
    @WebMethod
    @Oneway
    public void sendResponse(String userId);
}
