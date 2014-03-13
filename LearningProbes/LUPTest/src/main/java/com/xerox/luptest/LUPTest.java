package com.xerox.luptest;

import com.xerox.services.LUPEngine;
import com.xerox.services.ClientEngine;
import com.xerox.services.HubEngine;
import com.xerox.services.RestEngine;
import java.util.HashMap;
import java.util.Iterator;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.clerezza.rdf.core.MGraph;
import org.apache.clerezza.rdf.core.NonLiteral;
import org.apache.clerezza.rdf.core.Resource;
import org.apache.clerezza.rdf.core.Triple;
import org.apache.clerezza.rdf.core.UriRef;
import org.apache.clerezza.rdf.core.access.TcManager;

import org.apache.clerezza.rdf.core.event.GraphEvent;
import org.apache.clerezza.rdf.core.event.GraphListener;

import org.codehaus.jettison.json.JSONObject;

import org.apache.clerezza.rdf.core.event.FilterTriple;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.LoggerFactory;

/**
 * The goal of this 3.4 LUP Module is to listen to label annotations and to ask
 * for proper services on OpenXerox
 */

/**
 *
 * @author aczerny
 */
@Component(metatype = true, immediate = true)
@Service
public class LUPTest implements LUPEngine
{
    
    /**
     * Using slf4j for logging
     */
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(LUPTest.class);
    
    public String getName() {
        return "LUPTest";
    }

    public String getDescription() {
        return "LUP module which provides a set of services for the Labelling T3.4 task.";
    }

    private class ListenerTest implements GraphListener {

        public void graphChanged(List<GraphEvent> list) {
        }
    }
    
    private LUPTest.ListenerTest listenerTest;
    private FilterTriple filterTest;
    private long delayTest;
    
    @Reference
    private ClientEngine openXeroxClient;
    
    private RestEngine clientPush;
    private RestEngine clientPull;
    
    private String testedService;
    
    @Activate
    public void activate() {
        try {
            log.info("Activation of the LUP::Test");
            /**
             * 1.) NOT IMPORTANT
             */
            this.listenerTest = null;
            this.filterTest = null;
            this.delayTest = 0;
            /**
             * 2.) IMPORTANT
             */
            this.clientPush = openXeroxClient.getPush();
            this.clientPull = openXeroxClient.getPull();
            this.testedService = "";
            /**
             * 3.) TEST PHASE
             */
            log.info("[[ Testing clientPush ]]");
            log.info("  [[ For task T3.4 ]]");
            this.testedService = "/fusepool/addtext/";
            log.info("  {{ service: " + this.testedService + " }}");
            log.info(this.clientPush.doGet(this.testedService));
            this.testedService = "/fusepool/addlabels/";
            log.info("  {{ service: " + this.testedService + " }}");
            log.info(this.clientPush.doGet(this.testedService));
            log.info("[[ Testing clientPull ]]");
            log.info("  [[ For task T3.4 ]]");
            this.testedService = "/fusepool/dopredictlabels/";
            log.info("  {{ service: " + this.testedService + " }}");
            log.info(this.clientPush.doGet(this.testedService));
        } catch (Exception ex) {
            log.error("/!\\ Service: " + this.testedService + " unavailable /!\\");
        }
    }
    
    @Deactivate
    private void deactivate() {
        log.info("Deactivation of the LUP::Test");
    }
    
    public GraphListener getListener() {
        return this.listenerTest;
    }

    public FilterTriple getFilter() {
        return this.filterTest;
    }
    
    public long getDelay() {
        return this.delayTest;
    }
    
    public void updateModels(HashMap<String, String> params) {}
    
    public String predict(HashMap<String, String> params) {
        return "How did you access this method you filthy hacker ?";
    }
    
    public void save() {}
    public void load() {}
    
}
