package org.camunda.jboss.plugin.classloader;

import org.camunda.bpm.engine.impl.interceptor.Command;
import org.camunda.bpm.engine.impl.interceptor.CommandInterceptor;

public class AcquireJobsInterceptor extends CommandInterceptor {

	public <T> T execute(Command<T> command) {

		if (command instanceof org.camunda.bpm.engine.impl.cmd.AcquireJobsCmd) {
			ClassLoader oldClassLoader = Thread.currentThread().getContextClassLoader();
			try {
				Thread.currentThread().setContextClassLoader(org.camunda.bpm.engine.ProcessEngine.class.getClassLoader());
				return this.next.execute(command);
			}
			finally {
				Thread.currentThread().setContextClassLoader(oldClassLoader);
			} 
		} 
		return (T)this.next.execute(command);
	}

}
