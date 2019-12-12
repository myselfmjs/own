package com.pluto.own.Design.Strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ：pluto
 * @date ：Created in 2019/12/9 16:24
 * 创建策略工厂
 *
 */
@Service
public class ExportContextFactory {
    @Autowired
    private Map<String, ExportStrategy> contextStrategy = new ConcurrentHashMap<>();

   /* public ExportContextFactory(Map<String, ExportStrategy> contextStrategy){
        this.contextStrategy.clear();
        //contextStrategy.forEach((k, v)-> this.contextStrategy.put(k, v));
    }*/

    public ExportStrategy get(String source) {
        ExportStrategy exportStrategy = this.contextStrategy.get(source);
        if (exportStrategy == null) {
            throw new IllegalArgumentException();
        }
        return exportStrategy;
    }

}
