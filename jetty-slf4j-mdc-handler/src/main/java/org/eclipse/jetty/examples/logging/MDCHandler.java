package org.eclipse.jetty.examples.logging;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.HandlerWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class MDCHandler extends HandlerWrapper
{
    private static final Logger LOG = LoggerFactory.getLogger(MDCHandler.class);
    
    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        try
        {
            LOG.info("Start");
            MDC.put("host",request.getServerName());
            super.handle(target,baseRequest,request,response);
        }
        finally
        {
            MDC.clear();
            LOG.info("Finally");
        }
    }
}
