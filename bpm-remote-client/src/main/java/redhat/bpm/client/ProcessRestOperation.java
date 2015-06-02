/*
 * Copyright (C) Red Hat, Inc.
 * http://www.redhat.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package redhat.bpm.client;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.services.client.api.RemoteRuntimeEngineFactory;



public class ProcessRestOperation {
  
  private  String deploymentId;
  private  String processId;
  private  String baseUrl;
  private  String username;
  private  String password;

  private RuntimeEngine runtimeEngine;

  public  ProcessRestOperation() {
    this.deploymentId = "redhat.poc:media:1.1";
    this.processId = "media.test";
    this.baseUrl = "http://localhost:8080/business-central";
    this.username = "jey";
    this.password = "password$";
  }

  private synchronized void initRuntimeEngine() {
    if (runtimeEngine == null) {
      try {
    	  
    	  	this.runtimeEngine = RemoteRuntimeEngineFactory.newRestBuilder().addDeploymentId(deploymentId).addUrl(URI.create(baseUrl).toURL())
    			              .addUserName(username).addPassword(password).addTimeout(5000).build();
    	  
        //this.runtimeEngine = runtimeEngineFactory.newRuntimeEngine();
      } catch (MalformedURLException e) {
        throw new IllegalArgumentException(e);
      }
    }
  }

  public String getDeploymentId() {
    return deploymentId;
  }

  public String getProcessId() {
    return processId;
  }

  public String getBaseUrl() {
    return baseUrl;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String startProcess() {
    Map<String, Object> parameters = new HashMap<String, Object>();
    
    
    parameters.put("processinput", null);//your input for process variable
    
    
    initRuntimeEngine();
    KieSession kieSession = runtimeEngine.getKieSession();
    
    ProcessInstance processInstance = kieSession.startProcess(processId, parameters);
    
   String processInstanceId = Long.toString(processInstance.getId());
  System.out.println("process instand id " + processInstanceId);
    
    //kieSession.signalEvent("jobCompletion", "notComplete",7);
   
    
    //processInstance.signalEvent("jobCompletion", "notcdfd");
    // ProcessInstance instance = kieSession.getProcessInstance(3);
    //instance.signalEvent("jobCompletion", "notcdfd");
    //signalEvent("jobCompletion", "notcdfd");
   
    
    return processInstanceId;
  }

  public void starthboProcess(){
	  
  }
  public void cancelOrder(String orderRequestId) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public static void main(String [] ar){

	  ProcessRestOperation rest = new ProcessRestOperation();
	  rest.startProcess();
	  
 }
}
