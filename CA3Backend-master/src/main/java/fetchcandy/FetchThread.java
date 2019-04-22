/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fetchcandy;

import fetchstarwars.*;
import java.io.IOException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author Jesper
 */
public class FetchThread {

    public String fetchStuff(ArrayList<URL> urls) throws InterruptedException, ProtocolException, IOException, ExecutionException {

        ExecutorService es = Executors.newFixedThreadPool(10);
//        List<URL> urls = new ArrayList<>();
//        
//        for (int i = 0; i < 5; i++) {
//            urls.add(new URL("https://swapi.co/api/people/" + (i + 1)));
//        }
        
        List<Future<String>> list = new ArrayList<>();
        for (URL s : urls) {
            Future<String> future = es.submit(new MyCallable(s));
            list.add(future);
        }
        es.shutdown();
        String json = "[";
        for (int i = 0; i < list.size() -1; i++) {
            json += list.get(i).get() + ",";
        }
            json += list.get(list.size()-1).get();
        json += "]";
       
        return json;
    }
}
