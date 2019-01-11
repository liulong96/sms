/**
 * SDKService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package sms.send;

import javax.xml.rpc.Service;

public interface SDKService extends Service {
    public String getSDKServiceAddress();

    public SDKClient getSDKService() throws javax.xml.rpc.ServiceException;

    public SDKClient getSDKService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
