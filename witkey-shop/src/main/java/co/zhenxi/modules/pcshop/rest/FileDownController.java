package co.zhenxi.modules.pcshop.rest;

import co.zhenxi.annotation.AnonymousAccess;
import co.zhenxi.tools.rest.LocalStorageController;
import co.zhenxi.tools.service.LocalStorageService;
import co.zhenxi.tools.service.dto.LocalStorageDto;
import co.zhenxi.utils.FileUtil;
import io.swagger.annotations.Api;
import jodd.http.HttpResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.resource.HttpResource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * @Author: Jia Hao Hao
 * @Date: 2020-09-22 11:02
 * @Description: FileDownController
 **/
@AllArgsConstructor
@Api(tags = "图片下载")
@Controller
@RequestMapping("/api/fileDown")
public class FileDownController {

    private final LocalStorageService localStorageService;

    @AnonymousAccess
    @GetMapping
    public void test(HttpServletRequest request, @RequestParam("localStorage")long localStorage, HttpServletResponse response){


        try {
            String localStoragea = request.getParameter("localStorage");
            System.out.println(localStorage);
            System.out.println(localStoragea);
            LocalStorageDto byId = localStorageService.findById(localStorage);
            String path = byId.getPath();
            File file = new File(path);
            FileUtil.downloadFile(request,response,file,false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
