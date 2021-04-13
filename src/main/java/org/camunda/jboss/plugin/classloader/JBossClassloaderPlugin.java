package org.camunda.jboss.plugin.classloader;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.impl.cfg.AbstractProcessEnginePlugin;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.interceptor.CommandInterceptor;

public class JBossClassloaderPlugin extends AbstractProcessEnginePlugin
{
	public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
		postInit(processEngineConfiguration);

		addCommandInterceptor(processEngineConfiguration);
	}


	protected void addCommandInterceptor(ProcessEngineConfigurationImpl processEngineConfiguration) {
		List<CommandInterceptor> commandInterceptorList = processEngineConfiguration.getCustomPreCommandInterceptorsTxRequired();

		if (commandInterceptorList == null) {
			commandInterceptorList = new ArrayList<CommandInterceptor>();
			processEngineConfiguration.setCustomPreCommandInterceptorsTxRequired(commandInterceptorList);
		} 

		commandInterceptorList.add(new AcquireJobsInterceptor());
	}
}
