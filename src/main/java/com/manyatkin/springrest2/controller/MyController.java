package com.manyatkin.springrest2.controller;

import com.manyatkin.springrest2.model.Item;
import com.manyatkin.springrest2.model.MyDataBase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class MyController {

    @RequestMapping("/item")
    public Item item (@RequestParam(value = "item", required = false, defaultValue = "vc") String vendoreCode) throws SQLException {
        Item item = MyDataBase.getInstance().findItemByVendorCode(vendoreCode);
        if (item != null) {
            return item;
        }
        return new Item ();
    }
}
