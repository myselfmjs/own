package com.pluto.own.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Angular 框架
 * @author ：pluto
 * @date ：Created in 2019/8/5 11:54
 */
@Controller
@RequestMapping("/angular")
public class AngularController {

    private String prefix = "html/angular";

    @RequestMapping("/index")
    public String index(){
        return prefix + "/index";
    }

    @RequestMapping("/tableData")
    @ResponseBody
    public String tableData(){
        String str ="{\"records\":[ {\"Name\":\"Alfreds Futterkiste\",\"City\":\"Berlin\",\"Country\":\"Germany\"}, " +
                "{\"Name\":\"Ana Trujillo Emparedados y helados\",\"City\":\"México D.F.\",\"Country\":\"Mexico\"}, " +
                "{\"Name\":\"Antonio Moreno Taquería\",\"City\":\"México D.F.\",\"Country\":\"Mexico\"}, " +
                "{\"Name\":\"Around the Horn\",\"City\":\"London\",\"Country\":\"UK\"}, " +
                "{\"Name\":\"B's Beverages\",\"City\":\"London\",\"Country\":\"UK\"}, " +
                "{\"Name\":\"Berglunds snabbköp\",\"City\":\"Luleå\",\"Country\":\"Sweden\"}," +
                "{\"Name\":\"Blauer See Delikatessen\",\"City\":\"Mannheim\",\"Country\":\"Germany\"}," +
                " {\"Name\":\"Blondel père et fils\",\"City\":\"Strasbourg\",\"Country\":\"France\"}, " +
                "{\"Name\":\"Bólido Comidas preparadas\",\"City\":\"Madrid\",\"Country\":\"Spain\"}," +
                " {\"Name\":\"Bon app'\",\"City\":\"Marseille\",\"Country\":\"France\"}, " +
                "{\"Name\":\"Bottom-Dollar Marketse\",\"City\":\"Tsawassen\",\"Country\":\"Canada\"}, " +
                "{\"Name\":\"Cactus Comidas para llevar\",\"City\":\"Buenos Aires\",\"Country\":\"Argentina\"}," +
                " {\"Name\":\"Centro comercial Moctezuma\",\"City\":\"México D.F.\",\"Country\":\"Mexico\"}," +
                " {\"Name\":\"Chop-suey Chinese\",\"City\":\"Bern\",\"Country\":\"Switzerland\"}, " +
                "{\"Name\":\"Comércio Mineiro\",\"City\":\"São Paulo\",\"Country\":\"Brazil\"} ] }";
        return str;
    }
}
