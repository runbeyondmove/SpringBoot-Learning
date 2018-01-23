package com.didispace.web;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author 程序猿DD
 * @version 1.0.0
 * @blog http://blog.didispace.com
 *
 */
@RestController
public class HelloController {

    @ApiIgnore
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index() {
        return "Hello World";
    }

    @ApiOperation(value = "测试返回Json数据",notes="测试ResponseBody注解和ApiImplicitParam注解是否有冲突")
    @ApiImplicitParam(name = "page",value = "测试值",dataType = "String",required = true,paramType = "path")
    @RequestMapping(value = "/testResponseBody/{page}", method = RequestMethod.GET)
    @ResponseBody
    public Object testResponseBody(@PathVariable String page) {
        Map map = new HashMap<String,Object>();
        map.put("1","test1");
        map.put("2","test2");
        return map;
    }

}